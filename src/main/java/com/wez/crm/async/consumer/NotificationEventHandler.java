package com.wez.crm.async.consumer;

import static com.wez.crm.util.constant.RabbitMQConstant.NOTIFICATION_QUEUE;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationEventHandler {

  @RabbitListener(queues = NOTIFICATION_QUEUE, containerFactory = "rabbitListenerContainerFactory")
  public void handle(String message){
    log.info("Received message from notification queue: {}", message);
  }

}
