package com.school.notification.messaging.producer.imp;

import com.school.notification.messaging.producer.NotificationProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultNotificationProducer implements NotificationProducer {

  private final RabbitTemplate rabbitTemplate;

  public void produceNotification(String orderId) {
    log.info("producing notification with id: {}", orderId);
    rabbitTemplate
        .convertAndSend("notifications.exchange", "notifications.created", orderId);
  }
}
