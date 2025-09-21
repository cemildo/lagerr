package com.school.notification.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class RabbitConfig {

  private final RabbitProperties rabbitProperties;

  public RabbitConfig(RabbitProperties rabbitProperties) {
    this.rabbitProperties = rabbitProperties;
  }

  @Bean
  public List<TopicExchange> exchanges() {
    return rabbitProperties.getExchanges().stream()
        .map(e -> new TopicExchange(e.getName()))
        .collect(Collectors.toList());
  }

  @Bean
  public List<Queue> queues() {
    return rabbitProperties.getExchanges().stream()
        .flatMap(e -> e.getQueues().stream())
        .map(q -> new Queue(q.getName(), true))
        .collect(Collectors.toList());
  }

  @Bean
  public List<Binding> bindings(List<TopicExchange> exchanges, List<Queue> queues) {
    return rabbitProperties.getExchanges().stream()
        .flatMap(exchangeCfg ->
            exchangeCfg.getQueues().stream()
                .map(queueCfg -> {
                  var queue = queues.stream()
                      .filter(q -> q.getName().equals(queueCfg.getName()))
                      .findFirst()
                      .orElseThrow();

                  var exchange = exchanges.stream()
                      .filter(e -> e.getName().equals(exchangeCfg.getName()))
                      .findFirst()
                      .orElseThrow();

                  return BindingBuilder.bind(queue)
                      .to(exchange)
                      .with(queueCfg.getRoutingKey());
                })
        )
        .collect(Collectors.toList());
  }
}
