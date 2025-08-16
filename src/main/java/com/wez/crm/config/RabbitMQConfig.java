package com.wez.crm.config;

import static com.wez.crm.util.constant.RabbitMQConstant.AUDIT_LOG_QUEUE;
import static com.wez.crm.util.constant.RabbitMQConstant.AUDIT_LOG_ROUTING_KEY;
import static com.wez.crm.util.constant.RabbitMQConstant.CRM_EVENT_EXCHANGE;
import static com.wez.crm.util.constant.RabbitMQConstant.NOTIFICATION_DLX;
import static com.wez.crm.util.constant.RabbitMQConstant.NOTIFICATION_EMAIL_QUEUE;
import static com.wez.crm.util.constant.RabbitMQConstant.NOTIFICATION_EXCHANGE;
import static com.wez.crm.util.constant.RabbitMQConstant.NOTIFICATION_PUSH_QUEUE;
import static com.wez.crm.util.constant.RabbitMQConstant.NOTIFICATION_ROUTING_KEY;
import static com.wez.crm.util.constant.RabbitMQConstant.NOTIFICATION_SMS_QUEUE;
import static com.wez.crm.util.constant.RabbitMQConstant.ROLE_EVENT_QUEUE;
import static com.wez.crm.util.constant.RabbitMQConstant.USER_EVENT_QUEUE;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  @Bean
  public Jackson2JsonMessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(messageConverter);
    rabbitTemplate.setMandatory(true);
    return rabbitTemplate;
  }

  @Bean
  public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory,
      Jackson2JsonMessageConverter messageConverter) {
    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setMessageConverter(messageConverter);
    return factory;
  }

  @Bean
  public TopicExchange crmSystemExchange() {
    return new TopicExchange(CRM_EVENT_EXCHANGE);
  }

  @Bean
  public TopicExchange notificationsExchange() {
    return new TopicExchange(NOTIFICATION_EXCHANGE);
  }

  @Bean
  public DirectExchange dlxExchange() {
    return new DirectExchange(NOTIFICATION_DLX);
  }

  @Bean
  public Queue roleEventQueue() {
    return new Queue(ROLE_EVENT_QUEUE);
  }

  @Bean
  public Queue userEventQueue() {
    return new Queue(USER_EVENT_QUEUE);
  }

  @Bean
  public Queue auditLogQueue() {
    return new Queue(AUDIT_LOG_QUEUE);
  }

  @Bean
  public Queue emailQueue() {
    return QueueBuilder.durable(NOTIFICATION_EMAIL_QUEUE)
        .withArgument("x-dead-letter-exchange", NOTIFICATION_DLX)
        .withArgument("x-dead-letter-routing-key", "failed.email")
        .build();
  }

  @Bean
  public Queue smsQueue() {
    return QueueBuilder.durable(NOTIFICATION_SMS_QUEUE)
        .withArgument("x-dead-letter-exchange", NOTIFICATION_DLX)
        .withArgument("x-dead-letter-routing-key", "failed.sms")
        .build();
  }

  @Bean
  public Queue pushQueue() {
    return QueueBuilder.durable(NOTIFICATION_PUSH_QUEUE)
        .withArgument("x-dead-letter-exchange", NOTIFICATION_DLX)
        .withArgument("x-dead-letter-routing-key", "failed.push")
        .build();
  }

  @Bean
  public Binding auditLogBinding() {
    return BindingBuilder.bind(auditLogQueue())
        .to(crmSystemExchange())
        .with(AUDIT_LOG_ROUTING_KEY);
  }

  @Bean
  public Binding roleBinding() {
    return BindingBuilder.bind(roleEventQueue())
        .to(crmSystemExchange())
        .with(ROLE_EVENT_QUEUE);
  }

  @Bean
  public Binding emailBinding() {
    return BindingBuilder
        .bind(emailQueue()).to(notificationsExchange())
        .with(NOTIFICATION_ROUTING_KEY);
  }
}
