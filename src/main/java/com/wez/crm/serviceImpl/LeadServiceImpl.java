package com.wez.crm.serviceImpl;

import com.wez.crm.async.producer.EventProducer;
import com.wez.crm.dto.CreateLeadRequestDto;
import com.wez.crm.dto.LeadResponseDto;
import com.wez.crm.dto.UpdateLeadRequestDto;
import com.wez.crm.entity.Contact;
import com.wez.crm.entity.Lead;
import com.wez.crm.entity.LeadSource;
import com.wez.crm.entity.User;
import com.wez.crm.exception.NotFoundException;
import com.wez.crm.mapper.LeadMapper;
import com.wez.crm.repository.LeadRepository;
import com.wez.crm.service.ContactService;
import com.wez.crm.service.LeadService;
import com.wez.crm.service.LeadSourceService;
import com.wez.crm.service.UserService;
import com.wez.crm.util.constant.ApiResponseConstant;
import com.wez.crm.util.constant.AppConstant;
import com.wez.crm.util.constant.ExceptionMessageConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.wez.crm.util.constant.RabbitMQConstant.CRM_EVENT_EXCHANGE;
import static com.wez.crm.util.constant.RabbitMQConstant.NOTIFICATION_EMAIL_ROUTING_KEY;

@Service
@RequiredArgsConstructor
public class LeadServiceImpl implements LeadService {

  private final LeadRepository leadRepository;
  private final LeadMapper leadMapper;
  private final ContactService contactService;
  private final UserService userService;
  private final LeadSourceService leadSourceService;
  private final EventProducer eventProducer;

  @Override
  public Lead getLeadById(Long id) {
    return leadRepository.findById(id)
        .orElseThrow(()-> new NotFoundException(String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID, id, AppConstant.LEAD)));
  }

  @Override
  public LeadResponseDto getLeadResponseDtoById(Long id) {
    return leadRepository.findLeadResponseDtoById(id)
        .orElseThrow(() -> new NotFoundException(String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID, id, AppConstant.LEAD)));
  }

  @Override
  public LeadResponseDto createLead(CreateLeadRequestDto createLeadRequestDto) {
//    validateLeadRequestDto(createLeadRequestDto);
    Lead lead = prepareEntityFromRequest(createLeadRequestDto,-1L);
    leadRepository.save(lead);

    eventProducer.produce(CRM_EVENT_EXCHANGE, NOTIFICATION_EMAIL_ROUTING_KEY, "New lead created: " + lead.getId());

    return leadMapper.toResponseDto(lead);
  }

  @Override
  public LeadResponseDto updateLead(Long id, UpdateLeadRequestDto updateLeadRequestDto) {
//    validateLeadRequestDto(updateLeadRequestDto);
    Lead lead = prepareEntityFromRequest(updateLeadRequestDto,id);
    return leadMapper.toResponseDto(leadRepository.save(lead));
  }

  @Override
  public String deleteLead(Long id) {
    if (!leadRepository.existsById(id)) {
      throw new NotFoundException(String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID, id, AppConstant.LEAD));
    }
    leadRepository.deleteById(id);
    return String.format(ApiResponseConstant.DELETE_SUCCESSFUL_TEMPLATE, AppConstant.LEAD);
  }

  @Override
  public Boolean isLeadExistsById(Long id) {
    return leadRepository.existsById(id);
  }

  @Override
  public Lead getReferenceById(Long id) {
    return leadRepository.getReferenceById(id);
  }

  @Override
  public Page<LeadResponseDto> findAllLeadResponseDto(Pageable pageable) {
    return null;
  }

//  private void validateLeadRequestDto(Object object) {
//
//    switch (object) {
//      case CreateLeadRequestDto createLeadRequestDto ->{
//        if(!contactService.isContactExistsById(createLeadRequestDto.getContactId())) {
//          throw new NotFoundException(String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID, createLeadRequestDto.getContactId(), AppConstant.CONTACT));
//        }
//        if(!leadSourceService.isLeadSourceExistsById(createLeadRequestDto.getSourceId())) {
//          throw new NotFoundException(String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID, createLeadRequestDto.getSourceId(), AppConstant.LEAD_SOURCE));
//        }
//        if(createLeadRequestDto.getAssignedSalesRepId() != null && !userService.isUserExistsById(createLeadRequestDto.getAssignedSalesRepId())) {
//          throw new NotFoundException(String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID, createLeadRequestDto.getAssignedSalesRepId(), AppConstant.USER));
//        }
//      }
//      case UpdateLeadRequestDto updateLeadRequestDto ->{
//        if(updateLeadRequestDto.getContactId() != null && !contactService.isContactExistsById(updateLeadRequestDto.getContactId())) {
//          throw new NotFoundException(String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID, updateLeadRequestDto.getContactId(), AppConstant.CONTACT));
//        }
//        if(updateLeadRequestDto.getSourceId() != null && !leadSourceService.isLeadSourceExistsById(updateLeadRequestDto.getSourceId())) {
//          throw new NotFoundException(String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID, updateLeadRequestDto.getSourceId(), AppConstant.LEAD_SOURCE));
//        }
//        if(updateLeadRequestDto.getAssignedSalesRepId() != null && !userService.isUserExistsById(updateLeadRequestDto.getAssignedSalesRepId())) {
//          throw new NotFoundException(String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID, updateLeadRequestDto.getAssignedSalesRepId(), AppConstant.USER));
//        }
//      }
//      default -> throw new IllegalArgumentException(String.format(ExceptionMessageConstant.ILLEGAL_OBJECT,object.getClass().getName()));
//    }
//
//  }

  private Lead prepareEntityFromRequest(Object object, Long id) {
    switch (object){
      case CreateLeadRequestDto createLeadRequestDto -> {
        Contact contact = contactService.getReferenceById(createLeadRequestDto.getContactId());
        LeadSource leadSource = leadSourceService.getReferenceById(createLeadRequestDto.getSourceId());
        User assignedSalesRep = userService.getReferenceById(createLeadRequestDto.getAssignedSalesRepId());
        return leadMapper.toEntity(createLeadRequestDto, contact, assignedSalesRep, leadSource);
      }
      case UpdateLeadRequestDto updateLeadRequestDto -> {
        Lead lead = getLeadById(id);
        leadMapper.updateEntityFromRequest(
            updateLeadRequestDto,
            lead,
            (updateLeadRequestDto.getContactId() != null) ? contactService.getReferenceById(updateLeadRequestDto.getContactId()) : null,
            (updateLeadRequestDto.getAssignedSalesRepId() != null) ? userService.getReferenceById(updateLeadRequestDto.getAssignedSalesRepId()) : null,
            (updateLeadRequestDto.getSourceId() != null) ? leadSourceService.getReferenceById(updateLeadRequestDto.getSourceId()) : null
        );
        return lead;
      }
      default -> throw new IllegalArgumentException(String.format(ExceptionMessageConstant.ILLEGAL_OBJECT, object.getClass().getName()));
    }
  }
}
