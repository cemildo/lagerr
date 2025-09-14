package com.school.notification.service.imp;

import com.school.notification.messaging.producer.NotificationProducer;
import com.school.notification.service.NotificationDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DefaultNotificationDomainService implements NotificationDomainService {

  private final NotificationProducer notificationProducer;

  public void publishNotification(String orderId) {
    notificationProducer.produceNotification(orderId);
  }
}
