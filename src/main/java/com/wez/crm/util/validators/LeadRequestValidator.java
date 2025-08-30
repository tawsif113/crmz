package com.wez.crm.util.validators;

import com.wez.crm.dto.CreateLeadRequestDto;
import com.wez.crm.dto.UpdateLeadRequestDto;
import com.wez.crm.service.ContactService;
import com.wez.crm.service.LeadSourceService;
import com.wez.crm.service.UserService;
import com.wez.crm.util.annotations.ValidLeadRequest;
import com.wez.crm.util.constant.AppConstant;
import com.wez.crm.util.constant.ExceptionMessageConstant;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LeadRequestValidator implements ConstraintValidator<ValidLeadRequest, Object> {

    private final ContactService contactService;
    private final LeadSourceService leadSourceService;
    private final UserService userService;

    @Override
    public boolean isValid(Object dto, ConstraintValidatorContext context) {
        if (dto == null) return true;

        context.disableDefaultConstraintViolation();
        boolean valid = true;

        if (dto instanceof CreateLeadRequestDto create) {
            if (create.getContactId() == null) {
                addViolation(context, "contactId", "contactId is required");
                valid = false;
            } else if (!contactService.isContactExistsById(create.getContactId())) {
                addViolation(context, "contactId",
                        String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID,AppConstant.CONTACT, create.getContactId()));
                valid = false;
            }

            if (create.getSourceId() == null) {
                addViolation(context, "sourceId", "sourceId is required");
                valid = false;
            } else if (!leadSourceService.isLeadSourceExistsById(create.getSourceId())) {
                addViolation(context, "sourceId",
                        String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID,AppConstant.LEAD_SOURCE, create.getSourceId()));
                valid = false;
            }

            if (create.getAssignedSalesRepId() != null &&
                    !userService.isUserExistsById(create.getAssignedSalesRepId())) {
                addViolation(context, "assignedSalesRepId",
                        String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID,AppConstant.USER, create.getAssignedSalesRepId()));
                valid = false;
            }
        } else if (dto instanceof UpdateLeadRequestDto update) {
            if (update.getContactId() != null &&
                    !contactService.isContactExistsById(update.getContactId())) {
                addViolation(context, "contactId",
                        String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID,AppConstant.CONTACT, update.getContactId()));
                valid = false;
            }

            if (update.getSourceId() != null &&
                    !leadSourceService.isLeadSourceExistsById(update.getSourceId())) {
                addViolation(context, "sourceId",
                        String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID,AppConstant.LEAD_SOURCE, update.getSourceId()));
                valid = false;
            }

            if (update.getAssignedSalesRepId() != null &&
                    !userService.isUserExistsById(update.getAssignedSalesRepId())) {
                addViolation(context, "assignedSalesRepId",
                        String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID,AppConstant.USER, update.getAssignedSalesRepId()));
                valid = false;
            }
        } else {
            return true;
        }

        return valid;
    }

    private void addViolation(ConstraintValidatorContext context, String property, String message) {
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(property)
                .addConstraintViolation();
    }
}
