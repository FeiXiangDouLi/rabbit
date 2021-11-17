package com.rabbitmq.demo;

import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= RabbitmqDemoApplication.class)
public class RabbitmqDemoApplicationTests {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Test
    public void contextLoads() {
//        rabbitTemplate.convertAndSend();
//        Message message = rabbitTemplate.sendAndReceive();
//        Object o = rabbitTemplate.convertSendAndReceive();

//        rabbitTemplate.convertAndSend();

        final Properties queueProperties = rabbitAdmin.getQueueProperties("resource.cloud-resource.property-changed.biz");
        System.err.println(queueProperties);
    }

}
