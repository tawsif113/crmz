package com.wez.crm.async.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventProducer {

  private final RabbitTemplate rabbitTemplate;

  public <T> void produce(String exchange, String routingKey, T message) {
    rabbitTemplate.convertAndSend(
        exchange,
        routingKey,
        message
    );
  }
}