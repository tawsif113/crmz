package com.wez.crm.util.constant;

public class RabbitMQConstant{

  private RabbitMQConstant(){}

  public static final String CRM_EVENT_EXCHANGE = "crm.event.exchange";

  public static final String AUDIT_LOG_QUEUE = "audit.log.queue";
  public static final String AUDIT_LOG_ROUTING_KEY = "audit.log.routing.key";

  public static final String NOTIFICATION_QUEUE = "notification.queue";
  public static final String NOTIFICATION_EMAIL_ROUTING_KEY = "notification.create.email.routing.key";
  public static final String NOTIFICATION_PUSH_ROUTING_KEY = "notification.create.push.routing.key";
}
