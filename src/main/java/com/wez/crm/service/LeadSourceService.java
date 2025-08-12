package com.wez.crm.service;

import com.wez.crm.dto.CreateLeadSourceRequestDto;
import com.wez.crm.dto.LeadSourceResponseDto;
import com.wez.crm.entity.LeadSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LeadSourceService {

  LeadSource getLeadSourceById(Long id);

  LeadSourceResponseDto createLeadSource(CreateLeadSourceRequestDto createLeadSourceRequestDto);

  LeadSourceResponseDto updateLeadSource(Long id,  CreateLeadSourceRequestDto createLeadSourceRequestDto);

  String deleteLeadSource(Long id);

  LeadSourceResponseDto getLeadSourceResponseDtoById(Long id);

  Boolean isLeadSourceExistsById(Long id);

  LeadSource getReferenceById(Long id);

  Page<LeadSourceResponseDto> findAllLeadSourceResponseDto(Pageable pageable);
}
