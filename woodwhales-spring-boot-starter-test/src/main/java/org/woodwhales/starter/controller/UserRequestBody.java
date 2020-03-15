package org.woodwhales.starter.controller;

/**
 * @projectName: woodwhales-spring-boot-starter-test
 * @author: woodwhales
 * @date: 20.3.14 19:15
 * @description:
 */
public class UserRequestBody {

    private String name;

    public UserRequestBody() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRequestBody(String name) {
        this.name = name;
    }
}
