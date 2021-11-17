package com.example.rabbit.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
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

    public static final String QUEUE_F = "f";

    public static final String QUEUE_TTL = "ttl";

    public static final String QUEUE_MAX_LENGTH = "max-length";

    public static final String QUEUE_MAX_BYTES = "max-bytes";

    public static final String QUEUE_PRIORITY = "priority";

    public static final String QUEUE_PRIORITY_8 = "priority_8";

    public static final String QUEUE_DEAD = "dead";

    public static final String ROUTING_KEY_DEAD = "dead";

    public static final String DIRECT_EXCHANGE_DEAD = "direct.dead";

    public static final String DIRECT_EXCHANGE = "amq.direct";

    public static final String FANOUT_EXCHANGE = "amq.fanout";

    public static final String HEADERS_EXCHANGE = "amq.headers";

    public static final String TOPIC_EXCHANGE = "amq.topic";

    public static final String DIRECT_EXCHANGE_DELAYED = "direct.delayed";

    public static final String DIRECT_EXCHANGE_DELAYED2 = "direct.delayed2";

    public static final String DIRECT_EXCHANGE_DELAYED3 = "direct.delayed3";

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
    public Exchange directExchangeDelayed() {
        Map<String, Object> arguments = new HashMap<>(1);
        /**
         * direct、topic、fanout、headers
         */
        arguments.put("x-delayed-type", "direct");
        return new ExchangeBuilder(DIRECT_EXCHANGE_DELAYED, "x-delayed-message").withArguments(arguments).build();
    }

    @Bean
    public Exchange directExchangeDelayed2() {
        Map<String, Object> arguments = new HashMap<>(1);
        /**
         * direct、topic、fanout、headers
         */
        arguments.put("x-delayed-type", "direct");
        return ExchangeBuilder.directExchange(DIRECT_EXCHANGE_DELAYED2).delayed().withArguments(arguments).build();
    }

    @Bean
    public Exchange directExchangeDelayed3() {
        return ExchangeBuilder.directExchange(DIRECT_EXCHANGE_DELAYED3).delayed().build();
    }

    @Bean
    public Queue queueF() {
        Map<String, Object> args = new HashMap<>();
        // 指定死信交换机，当消息过期、拒收等情况、发送到此交换机上
        args.put("x-dead-letter-exchange", DIRECT_EXCHANGE_DEAD);
        // 指定死信队列的路由KEY，通过这个KEY路由到死信队列上
        args.put("x-dead-letter-routing-key", ROUTING_KEY_DEAD);
        // 消息过期时间，单位毫秒
        args.put("x-message-ttl", 3000);
        return QueueBuilder.nonDurable(QUEUE_F).withArguments(args).build();
    }

    @Bean
    public Queue queuePriority() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-max-priority", 5);
        return QueueBuilder.nonDurable(QUEUE_PRIORITY).withArguments(args).build();
    }

    @Bean
    public Queue queueTtl() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 5000);
        return QueueBuilder.nonDurable(QUEUE_TTL).withArguments(args).build();
    }

    @Bean
    public Queue queuePriority8() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-max-priority", 8);
        return QueueBuilder.nonDurable(QUEUE_PRIORITY_8).withArguments(args).build();
    }

    @Bean
    public Queue queueMaxLength() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-max-length", 10);
        return QueueBuilder.nonDurable(QUEUE_MAX_LENGTH).withArguments(args).build();
    }

    @Bean
    public Queue queueMaxBytes() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-max-length-bytes", 1024);
        return QueueBuilder.nonDurable(QUEUE_MAX_BYTES).withArguments(args).build();
    }

    @Bean
    public Queue queueDead() {
        return QueueBuilder.nonDurable(QUEUE_DEAD).build();
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
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(directExchange()).with("a");
    }

    @Bean
    public Binding bindingB() {
        return BindingBuilder.bind(queueB()).to(directExchange()).with("a");
    }

    @Bean
    public Binding bindingF() {
        return BindingBuilder.bind(queueF()).to(directExchange()).with("f");
    }

    @Bean
    public Binding bindingDelayedC() {
        return BindingBuilder.bind(queueC()).to(directExchangeDelayed()).with("c").noargs();
    }

    @Bean
    public Binding bindingDelayedC2() {
        return BindingBuilder.bind(queueC()).to(directExchangeDelayed2()).with("c").noargs();
    }

    @Bean
    public Binding bindingDelayedC3() {
        return BindingBuilder.bind(queueC()).to(directExchangeDelayed3()).with("c").noargs();
    }

    @Bean
    public Binding bindingPriority() {
        return BindingBuilder.bind(queuePriority()).to(directExchange()).with("priority");
    }

    @Bean
    public Binding bindingTtl() {
        return BindingBuilder.bind(queueTtl()).to(directExchange()).with("ttl");
    }

    @Bean
    public Binding bindingPriority8() {
        return BindingBuilder.bind(queuePriority8()).to(directExchange()).with("priority");
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
    public Binding bindingDead() {
        return BindingBuilder.bind(queueDead()).to(directExchangeDead()).with(ROUTING_KEY_DEAD);
    }

    @Bean
    public Binding bindingMaxLength() {
        return BindingBuilder.bind(queueMaxLength()).to(directExchange()).with("max-length");
    }

    @Bean
    public Binding bindingMaxBytes() {
        return BindingBuilder.bind(queueMaxBytes()).to(directExchange()).with("max-bytes");
    }
}
