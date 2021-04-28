package icu.appeal.memberserver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RequestMapping("/")
@RestController
public class ApiController {

    @Value("${message}")
    private String message;

    @GetMapping
    public String test(){
        return message;
    }

}
