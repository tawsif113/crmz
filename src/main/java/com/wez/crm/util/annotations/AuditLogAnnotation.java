package com.wez.crm.util.annotations;

import com.wez.crm.enums.ActionType;
import com.wez.crm.enums.EntityName;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AuditLogAnnotation {

  EntityName entityName();

  long entityId() default -1;

  ActionType actionType();

  String details();
}
