package com.example.zadaniePraca;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {


    @RequestMapping("/person/add")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
