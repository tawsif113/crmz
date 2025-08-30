package com.wez.crm.util.annotations;

import com.wez.crm.util.validators.LeadRequestValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LeadRequestValidator.class)
@Documented
public @interface ValidLeadRequest {
    String message() default "Invalid Lead Request";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}