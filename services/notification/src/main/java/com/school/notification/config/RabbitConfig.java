package com.school.notification.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
  @Bean
  public TopicExchange ordersExchange() {
    return new TopicExchange("notifications.exchange");
  }

  @Bean
  public Queue ordersQueue() {
    return new Queue("notifications.queue", true);
  }

  @Bean
  public Binding binding(Queue ordersQueue, TopicExchange ordersExchange) {
    return BindingBuilder.bind(ordersQueue).to(ordersExchange).with("notifications.created");
  }
}
