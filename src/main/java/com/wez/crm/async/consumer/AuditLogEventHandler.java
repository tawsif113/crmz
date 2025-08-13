package com.wez.crm.async.consumer;

import static com.wez.crm.util.constant.RabbitMQConstant.AUDIT_LOG_QUEUE;

import com.wez.crm.dto.AuditLogCreateRequestDto;
import com.wez.crm.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuditLogEventHandler {

  private final AuditLogService auditLogService;

  @RabbitListener(queues = AUDIT_LOG_QUEUE, containerFactory = "rabbitListenerContainerFactory")
  public void handle(AuditLogCreateRequestDto auditLogCreateRequestDto) {
    auditLogService.create(auditLogCreateRequestDto);
  }
}