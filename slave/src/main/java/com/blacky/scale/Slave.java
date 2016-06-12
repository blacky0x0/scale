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
public class Slave {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @RequestMapping(path = "/projects", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Project> projects() {
        LOG.info("make a query for data to master server ...");
        // POST, http://localhost:8080/projects/{id}/

        Project p1 = new Project(10L, 1L, "some data");
        Project p2 = new Project(11L, 2L, "some data");

        return newArrayList(p1, p2);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Slave.class, args);
    }
}