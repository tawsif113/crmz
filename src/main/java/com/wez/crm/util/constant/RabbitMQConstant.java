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


  /// notification section
  public static final String NOTIFICATION_EXCHANGE = "notifications.topic";
  public static final String NOTIFICATION_DLX = "notifications.dlx";

  public static final String NOTIFICATION_EMAIL_QUEUE = "notifications.email.queue";
  public static final String NOTIFICATION_SMS_QUEUE = "notifications.sms.queue";
  public static final String NOTIFICATION_PUSH_QUEUE = "notifications.push.queue";
  public static final String NOTIFICATION_ROUTING_KEY = "notification.email";
}
