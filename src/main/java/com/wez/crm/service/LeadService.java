package com.wez.crm.service;

import com.wez.crm.dto.CreateLeadRequestDto;
import com.wez.crm.dto.LeadResponseDto;
import com.wez.crm.dto.UpdateLeadRequestDto;
import com.wez.crm.entity.Lead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LeadService {

  Lead getLeadById(Long id);

  LeadResponseDto getLeadResponseDtoById(Long id);

  LeadResponseDto createLead(CreateLeadRequestDto createLeadRequestDto);

  LeadResponseDto updateLead(Long id, UpdateLeadRequestDto updateLeadRequestDto);

  String deleteLead(Long id);

  Boolean isLeadExistsById(Long id);

  Lead getReferenceById(Long id);

  Page<LeadResponseDto> findAllLeadResponseDto(Pageable pageable);
}
