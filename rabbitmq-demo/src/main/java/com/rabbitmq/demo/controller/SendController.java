package com.rabbitmq.demo.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author honglixiang@tiduyun.com
 * @date 2021/4/8
 */
@RestController
public class SendController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

//    @GetMapping("/send/{number}")
//    public ResponseVo sendA(@PathVariable Integer number){
//        ResponseVo responseVo = (ResponseVo) rabbitTemplate.convertSendAndReceive(RabbitMqConfig.DIRECT_EXCHANGE, RabbitMqConfig.ROUTING_KEY, number);
//        return responseVo;
//    }
//
//
//    @GetMapping("/sendb/{number}")
//    public ResponseVo sendB(@PathVariable Integer number){
//        ResponseVo responseVo = rabbitTemplate.convertSendAndReceiveAsType(RabbitMqConfig.DIRECT_EXCHANGE, RabbitMqConfig.ROUTING_KEY, number, ParameterizedTypeReference.forType(ResponseVo.class));
//        return responseVo;
//    }
}
