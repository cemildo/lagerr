package com.school.notification.messaging.consumer.imp;

import com.school.notification.messaging.consumer.NotificationConsumer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultNotificationConsumer implements NotificationConsumer {

  @RabbitListener(queues = "notifications.queue")
  public void consumeNotification(String orderId) {
       log.info("📩 Received order: {}", orderId);
  }
}
