package com.example.rabbit.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.rabbit.config.RabbitMqConfig;
import com.rabbitmq.client.Channel;

/**
 * @author honglixiang@tiduyun.com
 * @date 2021/11/8
 */
@Component
public class MqListener {

    @RabbitListener(queues = RabbitMqConfig.QUEUE)
    public void handlerA(Channel channel, Message message) throws Exception {
        final String body = new String(message.getBody());
        System.err.println("a:" + body);
    }

    @RabbitListener(queues = RabbitMqConfig.QUEUEB)
    public void handlerB(Channel channel, Message message) throws Exception {
        final String body = new String(message.getBody());
        System.err.println("b:" + body);
    }

    @RabbitListener(queues = RabbitMqConfig.QUEUEC)
    public void handlerC(Channel channel, Message message) throws Exception {
        final String body = new String(message.getBody());
        System.err.println(System.currentTimeMillis() + " - " + body);
    }

    @RabbitListener(queues = RabbitMqConfig.QUEUED)
    public void handlerD(Channel channel, Message message) throws Exception {
        final String body = new String(message.getBody());
        System.err.println("d:" + body);
    }

    @RabbitListener(queues = RabbitMqConfig.QUEUE_PRIORITY)
    public void handlerPriority(Channel channel, Message message) throws Exception {
        final String body = new String(message.getBody());
        System.err.println("priority-5:" + body);
    }

    // @RabbitListener(queues = RabbitMqConfig.QUEUE_PRIORITY_8)
    // public void handlerPriority8(Channel channel, Message message) throws Exception {
    // final String body = new String(message.getBody());
    // System.err.println("priority-8:" + body);
    // }

    @RabbitListener(queues = RabbitMqConfig.QUEUE_MAX_LENGTH)
    public void handlerMaxLength(Channel channel, Message message) throws Exception {
        final String body = new String(message.getBody());
        System.err.println(body);
    }

    @RabbitListener(queues = RabbitMqConfig.QUEUE_MAX_BYTES)
    public void handlerMaxBytes(Channel channel, Message message) throws Exception {
        final String body = new String(message.getBody());
        System.err.println(body);
    }
}
