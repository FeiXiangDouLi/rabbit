package com.rabbitmq.demo.controller;

import com.rabbitmq.demo.model.User;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author honglixiang@tiduyun.com
 * @date 2021/4/9
 */
@Slf4j
@RestController
public class CurlController {

    @GetMapping("/curl/get/{id}")
    public void get(@PathVariable Integer id, @RequestParam(required = false) String name, @RequestParam(required = false) Integer age){
        System.err.println(id + "-" + name + "-" + age);
//        log.info(""+ id);
//        log.info("id:{}", id);
    }

    @PostMapping("/curl/post")
    public void post(@RequestBody User user){
        System.err.println(user);
    }

    @PostMapping("/curl/post2")
    public void post2(){
        System.err.println("============================");
    }
}
