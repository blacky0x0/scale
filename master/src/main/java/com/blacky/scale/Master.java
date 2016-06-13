package com.blacky.scale;

import com.blacky.scale.model.*;
import com.blacky.scale.repository.ProjectRepository;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.*;

import static com.google.common.collect.Lists.newArrayList;

@Controller
@EnableAutoConfiguration
@ComponentScan
public class Master {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    public static final long DEFAULT_VERSION = 1L;
    private @Autowired ProjectRepository repository;

    @RequestMapping(path = "/projects/{id}/data/{textData}/", method = RequestMethod.POST)
    @ResponseBody
    public void project(@PathVariable("id") long id, @PathVariable("textData") String textData) {
        LOG.info("id:{}, textData:{}", id, textData);
        repository.add(new Project(id, DEFAULT_VERSION, textData));

        LOG.info("Sending metadata to slaves ...");
        try {
            RestTemplate restTemplate = new RestTemplate();
            final String slaveUrl = "http://localhost:8081/projects/{id}/";
            ProjectPartDto part = new ProjectPartDto(DEFAULT_VERSION, textData);

            Map<String, String> params = new HashMap<>();
            params.put("id", String.valueOf(id));

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(newArrayList(MediaType.APPLICATION_JSON));
            HttpEntity<ProjectPartDto> body = new HttpEntity<>(part, headers);

            ResponseEntity<String> result = restTemplate.exchange(slaveUrl, HttpMethod.POST, body, String.class, params);

            LOG.info("Get answer from a slave with status code: {}", result.getStatusCode());
        } catch (RestClientException  ex) {
            LOG.error("Can't send data! I'll try again later");
        }
    }

    @RequestMapping(path = "/projects", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Project> projects() {
        repository.add(new Project(10L, 1L, "some data"));
        repository.add(new Project(11L, 2L, "some data"));

        return repository.getAll();
    }

    @RequestMapping(path = "/failed", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<FailedProjectTransfer> failed() {
        int slavePort = 8081;
        FailedProjectTransfer fpt = new FailedProjectTransfer(slavePort, 10, new Project(10L, 1L, "some data"));

        return newArrayList(fpt);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Master.class, args);
    }
}