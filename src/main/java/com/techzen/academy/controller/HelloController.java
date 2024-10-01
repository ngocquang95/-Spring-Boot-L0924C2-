package com.techzen.academy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    // truyền thêm tham số => Hello Hung
    @RequestMapping("/hello")
    public String sayHello(@RequestParam(defaultValue = "") String name,
                           @RequestParam(defaultValue = "Đà Nẵng") String address) {
        return "Hello " + name + ", " + address + "!";
    }
}
