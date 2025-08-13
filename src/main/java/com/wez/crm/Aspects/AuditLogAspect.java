package com.wez.crm.Aspects;

import static com.wez.crm.util.constant.RabbitMQConstant.AUDIT_LOG_ROUTING_KEY;
import static com.wez.crm.util.constant.RabbitMQConstant.CRM_EVENT_EXCHANGE;

import com.wez.crm.async.producer.EventProducer;
import com.wez.crm.dto.AuditLogCreateRequestDto;
import com.wez.crm.util.annotations.AuditLogAnnotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditLogAspect {

  private final EventProducer eventProducer;

  @AfterReturning(pointcut = "@annotation(auditLogAnnotation)", returning = "returnObject")
  public void sendAuditLog(AuditLogAnnotation auditLogAnnotation, Object returnObject) {
    Long entityId = auditLogAnnotation.entityId() == -1 ? null : auditLogAnnotation.entityId();

    if (entityId == null) {
      entityId = extractEntityId(returnObject).orElse(null);
    }

    AuditLogCreateRequestDto auditLogCreateRequestDto = AuditLogCreateRequestDto.builder()
        .entityName(auditLogAnnotation.entityName())
        .entityId(entityId)
        .actionType(auditLogAnnotation.actionType())
        .details(auditLogAnnotation.details())
        .build();

    eventProducer.produce(CRM_EVENT_EXCHANGE, AUDIT_LOG_ROUTING_KEY,
        auditLogCreateRequestDto);
  }

  private Optional<Long> extractEntityId(Object returnedObject) {
    if (returnedObject == null) {
      return Optional.empty();
    }

    try {
      Method getIdMethod = returnedObject.getClass().getMethod("getId");
      Object idValue = getIdMethod.invoke(returnedObject);

      if (idValue instanceof Long) {
        return Optional.of((Long) idValue);
      } else if (idValue instanceof String) {
        return Optional.of(Long.parseLong((String) idValue));
      } else {
        return Optional.empty();
      }
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException |
             NumberFormatException e) {
      return Optional.empty();
    }
  }
}
