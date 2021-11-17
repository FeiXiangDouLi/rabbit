package com.rabbitmq.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author honglixiang@tiduyun.com
 * @date 2021/4/8
 */
@Configuration
public class RabbitMqConfig {

    public static final String QUEUE = "a";

    public static final String QUEUEB = "b";

    public static final String QUEUEC = "c";

    public static final String QUEUED = "d";

    public static final String QUEUEE = "e";

    public static final String QUEUE_F = "f";

    public static final String QUEUE_DEAD = "dead";

    public static final String ROUTING_KEY_DEAD = "dead";

    public static final String DIRECT_EXCHANGE_DEAD = "direct.dead";

    public static final String DIRECT_EXCHANGE = "amq.direct";

    public static final String FANOUT_EXCHANGE = "amq.fanout";

    public static final String HEADERS_EXCHANGE = "amq.headers";

    public static final String TOPIC_EXCHANGE = "amq.topic";

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange(HEADERS_EXCHANGE);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public DirectExchange directExchangeDead() {
        return new DirectExchange(DIRECT_EXCHANGE_DEAD);
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.nonDurable(QUEUE).build();
    }

    @Bean
    public Queue queueB() {
        return QueueBuilder.nonDurable(QUEUEB).build();
    }

    @Bean
    public Queue queueC() {
        return QueueBuilder.nonDurable(QUEUEC).build();
    }

    @Bean
    public Queue queueD() {
        return QueueBuilder.nonDurable(QUEUED).build();
    }

    @Bean
    public Queue queueE() {
        return QueueBuilder.nonDurable(QUEUEE).build();
    }

    @Bean
    public Queue queueF() {
        Map<String, Object> args = new HashMap<>();
        // 指定死信交换机，当消息过期、拒收等情况、发送到此交换机上
        args.put("x-dead-letter-exchange", DIRECT_EXCHANGE_DEAD);
        // 指定死信队列的路由KEY，通过这个KEY路由到死信队列上
        args.put("x-dead-letter-routing-key", ROUTING_KEY_DEAD);
        // 消息过期时间，单位毫秒
        args.put("x-message-ttl", 30000);
        return QueueBuilder.nonDurable(QUEUE_F).withArguments(args).build();
    }

    @Bean
    public Queue queueDead() {
        return QueueBuilder.nonDurable(QUEUE_DEAD).build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(directExchange()).with("a");
    }

    @Bean
    public Binding bindingB() {
        return BindingBuilder.bind(queueB()).to(directExchange()).with("a");
    }

    @Bean
    public Binding bindingE() {
        return BindingBuilder.bind(queueE()).to(directExchange()).with("e");
    }

    @Bean
    public Binding bindingF() {
        return BindingBuilder.bind(queueF()).to(directExchange()).with("f");
    }

    @Bean
    public Binding bindingFanoutC() {
        return BindingBuilder.bind(queueC()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingFanoutD() {
        return BindingBuilder.bind(queueD()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingHeaders() {
        return BindingBuilder.bind(queue()).to(headersExchange()).whereAll("action", "source").exist();
    }

    @Bean
    public Binding bindingHeadersB() {
        return BindingBuilder.bind(queueB()).to(headersExchange()).whereAll("action").exist();
    }

    @Bean
    public Binding bindingTopicB() {
        return BindingBuilder.bind(queueB()).to(topicExchange()).with("b.*");
    }

    @Bean
    public Binding bindingTopicC() {
        return BindingBuilder.bind(queueC()).to(topicExchange()).with("*.c");
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(queueDead()).to(directExchangeDead()).with(ROUTING_KEY_DEAD);
    }
}
