package com.school.notification.messaging.consumer;

public interface NotificationConsumer {
  void consumeNotification(String orderId);
}
