package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "")
public class Initial {

    @GetMapping
    public String Initial(){

        StringBuilder sb = new StringBuilder();
        sb.append("<br><p>Initial Page</p>");

        sb.append("<br><a href='/student'>Click here for student</a>");

        return sb.toString();
    }
}
