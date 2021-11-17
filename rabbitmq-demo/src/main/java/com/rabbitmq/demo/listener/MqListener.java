package com.rabbitmq.demo.listener;

import java.util.UUID;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.rabbitmq.demo.config.RabbitMqConfig;
import com.rabbitmq.demo.model.ResponseVo;

/**
 * @author honglixiang@tiduyun.com
 * @date 2021/4/8
 */
@Component
public class MqListener {

    @RabbitListener(queues = RabbitMqConfig.QUEUE)
    public ResponseVo handlerA(Channel channel, Message message, Integer number) throws Exception {
        System.err.println(number);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        return new ResponseVo("OK", UUID.randomUUID().toString());
    }
}
