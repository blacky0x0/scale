package com.blacky.scale;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class Slave {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Slave!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Slave.class, args);
    }
}