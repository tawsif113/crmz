package com.wez.crm.serviceImpl;

import com.wez.crm.dto.CreateLeadSourceRequestDto;
import com.wez.crm.dto.LeadSourceResponseDto;
import com.wez.crm.entity.LeadSource;
import com.wez.crm.exception.NotFoundException;
import com.wez.crm.mapper.LeadSourceMapper;
import com.wez.crm.repository.LeadSourceRepository;
import com.wez.crm.service.LeadSourceService;
import com.wez.crm.util.constant.ApiResponseConstant;
import com.wez.crm.util.constant.AppConstant;
import com.wez.crm.util.constant.ExceptionMessageConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeadSourceServiceImpl implements LeadSourceService {

  private final LeadSourceRepository leadSourceRepository;
  private final LeadSourceMapper leadSourceMapper;

  @Override
  public LeadSource getLeadSourceById(Long id) {
    return leadSourceRepository.findById(id).orElseThrow(()-> new NotFoundException(String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID, id,
        AppConstant.LEAD_SOURCE)));
  }

  @Override
  public LeadSourceResponseDto createLeadSource(CreateLeadSourceRequestDto createLeadSourceRequestDto) {
    return leadSourceMapper.toResponseDto(leadSourceRepository.save(leadSourceMapper.toEntity(createLeadSourceRequestDto)));
  }

  @Override
  public LeadSourceResponseDto updateLeadSource(Long id, CreateLeadSourceRequestDto createLeadSourceRequestDto) {
    LeadSource leadSource = getLeadSourceById(id);
    leadSourceMapper.updateEntity(createLeadSourceRequestDto, leadSource);
    return leadSourceMapper.toResponseDto(leadSourceRepository.save(leadSource));
  }

  @Override
  public String deleteLeadSource(Long id) {
    if(!leadSourceRepository.existsById(id)) {
      throw new NotFoundException(String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID, id, AppConstant.LEAD_SOURCE));
    }
    leadSourceRepository.deleteById(id);
    return String.format(ApiResponseConstant.DELETE_SUCCESSFUL_TEMPLATE,AppConstant.LEAD_SOURCE);
  }

  @Override
  public LeadSourceResponseDto getLeadSourceResponseDtoById(Long id) {
    return leadSourceMapper.toResponseDto(getLeadSourceById(id));
  }

  @Override
  public Boolean isLeadSourceExistsById(Long id) {
    return leadSourceRepository.existsById(id);
  }

  @Override
  public LeadSource getReferenceById(Long id) {
    return leadSourceRepository.getReferenceById(id);
  }

  @Override
  public Page<LeadSourceResponseDto> findAllLeadSourceResponseDto(Pageable pageable) {
    return leadSourceRepository.findAll(pageable).map(leadSourceMapper::toResponseDto);
  }
}
