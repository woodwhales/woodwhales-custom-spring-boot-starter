package org.woodwhales.starter.controller;

import org.springframework.web.bind.annotation.*;
import org.woodwhales.starter.log.MyLog;

import java.util.Random;

/**
 * @projectName: woodwhales-spring-boot-starter-test
 * @author: woodwhales
 * @date: 20.3.14 18:55
 * @description:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @MyLog(desc = "查询用户")
    @GetMapping("/list")
    public String list() {
        try {
            Thread.sleep(new Random().nextInt(2));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @MyLog(desc = "添加用户")
    @PostMapping("/add/")
    public String add(@RequestBody UserRequestBody user) {
        try {
            Thread.sleep(new Random().nextInt(2));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @MyLog(desc = "删除用户")
    @PostMapping("/delete/{userId}")
    public String delete(@PathVariable(name = "userId") String userId) {
        try {
            Thread.sleep(new Random().nextInt(2));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
