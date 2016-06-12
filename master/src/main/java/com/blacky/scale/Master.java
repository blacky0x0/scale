package com.blacky.scale;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class Master {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Master!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Master.class, args);
    }
}