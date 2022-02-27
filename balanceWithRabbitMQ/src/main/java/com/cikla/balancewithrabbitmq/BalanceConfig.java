package com.cikla.balancewithrabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BalanceConfig {

    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("${rabbitmq.queue.balance}")
    private String balanceQueue;

    @Value("${rabbitmq.routing-keys.internal-balance}")
    private String internalBalanceRoutingKeys;

    @Bean
    public TopicExchange internalTopicExchange(){
        return new TopicExchange(this.internalExchange);
    }

    @Bean
    public Queue balanceQueue(){
        return new Queue(this.balanceQueue);
    }

    @Bean
    public Binding internalToBalanceBinding(){
        return BindingBuilder.bind(balanceQueue())
                .to(internalTopicExchange())
                .with(this.internalBalanceRoutingKeys);
    }

    public String getBalanceQueue() {
        return balanceQueue;
    }

    public String getInternalExchange() {
        return internalExchange;
    }

    public String getInternalBalanceRoutingKeys() {
        return internalBalanceRoutingKeys;
    }

}
