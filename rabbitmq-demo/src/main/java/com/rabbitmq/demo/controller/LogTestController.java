package com.rabbitmq.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author honglixiang@tiduyun.com
 * @date 2021/10/11
 */
@Slf4j
@RestController
public class LogTestController {

    @GetMapping("/logger/test")
    public void test(@RequestParam String name,@RequestParam Integer age){
//        log.warn("name : {}",name);
//        log.info("age : {}",age);
//        if(age < 0){
//            log.error("年龄不对！！！");
//        }
    }
}
