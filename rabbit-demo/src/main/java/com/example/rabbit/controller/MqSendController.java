package com.example.rabbit.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rabbit.config.RabbitMqConfig;

/**
 * @author honglixiang@tiduyun.com
 * @date 2021/11/8
 */
@RestController
public class MqSendController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/mq/direct")
    public void send() {
        rabbitTemplate.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE, "a", "direct");
    }

    @GetMapping("/mq/fanout")
    public void send2() {
        rabbitTemplate.convertAndSend(RabbitMqConfig.FANOUT_EXCHANGE, "abc", "fanout");
    }

    @GetMapping("/mq/topic")
    public void send3() {
        rabbitTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE, "a.b", "topic-a.b");
        rabbitTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE, "b.b", "topic-b.b");
        rabbitTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE, "c.c", "topic-c.c");
        rabbitTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE, "b.c", "topic-b.c");
    }

    @GetMapping("/mq/headers")
    public void send4() {
        rabbitTemplate.convertAndSend(RabbitMqConfig.HEADERS_EXCHANGE, "aaa", "header-action,source", message -> {
            MessageProperties messageProperties = message.getMessageProperties();
            messageProperties.setHeader("action", "action");
            messageProperties.setHeader("source", "source");
            return message;
        });

        rabbitTemplate.convertAndSend(RabbitMqConfig.HEADERS_EXCHANGE, "bbb", "header-action", message -> {
            MessageProperties messageProperties = message.getMessageProperties();
            messageProperties.setHeader("action", "action");
            return message;
        });
    }

    @GetMapping("/mq/delay")
    public void send5() {
        // 通过消息过期以及死信交换机实现
        rabbitTemplate.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE, "f", "direct-delay-f");
    }

    @GetMapping("/mq/delay2")
    public void send7() {
        for (int i = 5; i > 0; i--) {
            int finalI = i;
            rabbitTemplate.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE_DELAYED, "c", "direct-delay-" + i, message -> {
                message.getMessageProperties().setDelay(finalI * 3000);
                return message;
            });
        }
    }

    @GetMapping("/mq/delay3")
    public void send10() {
        for (int i = 5; i > 0; i--) {
            int finalI = i;
            rabbitTemplate.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE_DELAYED2, "c", "direct-delay2-" + i,
                message -> {
                    message.getMessageProperties().setDelay(finalI * 3000);
                    return message;
                });
        }
    }

    @GetMapping("/mq/delay4")
    public void send11() {
        for (int i = 5; i > 0; i--) {
            int finalI = i;
            rabbitTemplate.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE_DELAYED3, "c", "direct-delay3-" + i,
                message -> {
                    message.getMessageProperties().setDelay(finalI * 3000);
                    return message;
                });
        }
    }

    @GetMapping("/mq/expiration")
    public void send6() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE, "b", "direct-expiration-" + i, message -> {
                message.getMessageProperties().setExpiration("5000");
                return message;
            });
            rabbitTemplate.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE, "b", "direct-expiration-0-" + i);
        }
    }

    @GetMapping("/mq/priority")
    public void send8() {
        for (int i = -1; i <= 10; i++) {
            int finalI = i;
            rabbitTemplate.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE, "priority",
                "direct-Priority-priority" + finalI, message -> {
                    message.getMessageProperties().setPriority(finalI);
                    return message;
                });
            // 中途发一个不设置优先级的消息，看排序情况
            if (i == 5) {
                rabbitTemplate.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE, "priority", "direct-Priority-no");
            }
        }
    }

    @GetMapping("/mq/ttl")
    public void send9() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            TimeUnit.SECONDS.sleep(1);
            rabbitTemplate.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE, "ttl", "direct-ttl" + finalI, message -> {
                message.getMessageProperties().setPriority(finalI);
                return message;
            });
        }
    }

    @GetMapping("/mq/maxlength")
    public void send13() throws InterruptedException {
        for (int i = 0; i < 15; i++) {
            int finalI = i;
            rabbitTemplate.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE, "max-length", "max-length-message-" + finalI);
        }

        for (int i = 0; i < 70; i++) {
            int finalI = i;
            rabbitTemplate.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE, "max-bytes",
                "max-length-bytes-message-" + finalI);
        }

    }
}
