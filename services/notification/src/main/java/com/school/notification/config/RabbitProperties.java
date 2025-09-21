package com.school.notification.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "app.rabbitmq")
public class RabbitProperties {

  private List<ExchangeConfig> exchanges;

  public static class ExchangeConfig {
    private String name;
    private List<QueueConfig> queues;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<QueueConfig> getQueues() { return queues; }
    public void setQueues(List<QueueConfig> queues) { this.queues = queues; }
  }

  public static class QueueConfig {
    private String name;
    private String routingKey;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRoutingKey() { return routingKey; }
    public void setRoutingKey(String routingKey) { this.routingKey = routingKey; }
  }

  public List<ExchangeConfig> getExchanges() { return exchanges; }
  public void setExchanges(List<ExchangeConfig> exchanges) { this.exchanges = exchanges; }
}
