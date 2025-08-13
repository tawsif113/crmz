package com.wez.crm.config;

import static com.wez.crm.util.constant.RabbitMQConstant.AUDIT_LOG_QUEUE;
import static com.wez.crm.util.constant.RabbitMQConstant.AUDIT_LOG_ROUTING_KEY;
import static com.wez.crm.util.constant.RabbitMQConstant.CRM_EVENT_EXCHANGE;
import static com.wez.crm.util.constant.RabbitMQConstant.ROLE_EVENT_QUEUE;
import static com.wez.crm.util.constant.RabbitMQConstant.USER_EVENT_QUEUE;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
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
}
