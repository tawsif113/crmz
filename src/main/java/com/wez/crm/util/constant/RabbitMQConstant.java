package com.wez.crm.util.constant;

public class RabbitMQConstant{

  private RabbitMQConstant(){}

  public static final String CRM_EVENT_EXCHANGE = "crm.event.exchange";
  public static final String ROLE_EVENT_QUEUE = "role.event.queue";
  public static final String ROLE_EVENT_ROUTING_KEY = "role.event.routing.key";

  public static final String USER_EVENT_QUEUE = "user.event.queue";
  public static final String USER_EVENT_ROUTING_KEY = "user.event.routing.key";

  public static final String AUDIT_LOG_QUEUE = "audit.log.queue";

  public static final String AUDIT_LOG_ROUTING_KEY = "audit.log.routing.key";
}
