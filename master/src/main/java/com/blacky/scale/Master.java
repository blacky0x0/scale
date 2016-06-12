package com.blacky.scale;

import com.blacky.scale.model.*;
import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import static com.google.common.collect.Lists.newArrayList;

@Controller
@EnableAutoConfiguration
public class Master {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @RequestMapping(path = "/projects/{id}/data/{textData}/", method = RequestMethod.POST)
    @ResponseBody
    public void project(@PathVariable("id") int id, @PathVariable("textData") String textData) {
        LOG.info("id:{}, textData:{}", id, textData);
        LOG.info("sending metadata to slaves ...");
    }

    @RequestMapping(path = "/projects", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Project> projects() {
        Project p1 = new Project(10L, 1L, "some data");
        Project p2 = new Project(11L, 2L, "some data");

        return newArrayList(p1, p2);
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