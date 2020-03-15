package org.woodwhales.starter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.woodwhales.starter.HelloService;

/**
 * @projectName: woodwhales-spring-boot-starter-test
 * @author: woodwhales
 * @date: 20.3.14 18:12
 * @description:
 */
@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;

    @GetMapping("/{name}")
    public String hello(@PathVariable(name = "name") String name) {
        return helloService.sayHello(name);
    }
}
