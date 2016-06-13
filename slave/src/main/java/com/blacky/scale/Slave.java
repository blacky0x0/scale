package com.blacky.scale;

import com.blacky.scale.model.*;
import com.blacky.scale.repository.ProjectRepository;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@EnableAutoConfiguration
@ComponentScan
public class Slave {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private @Autowired ProjectRepository repository;

    @RequestMapping(path = "/projects/{id}/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void project(@PathVariable("id") long id, @RequestBody ProjectPartDto dto) {
        LOG.debug("Received data: id:{}; version:{}; data:{}", id, dto.getVersion(), dto.getData());
        repository.add(new Project(id, dto.getVersion(), dto.getData()));
    }

    @RequestMapping(path = "/projects", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Project> projects() {
        return repository.getAll();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Slave.class, args);
    }
}