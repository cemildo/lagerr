package com.school.notification.messaging.producer;

public interface NotificationProducer {
  void produceNotification(String orderId);
}
