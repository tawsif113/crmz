package com.wez.crm.mapper;

import com.wez.crm.dto.CreateLeadSourceRequestDto;
import com.wez.crm.dto.LeadSourceResponseDto;
import com.wez.crm.entity.LeadSource;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LeadSourceMapper {

   LeadSourceResponseDto toResponseDto(LeadSource leadSource);
   LeadSource toEntity(CreateLeadSourceRequestDto createLeadSourceRequestDto);
   void updateEntity(CreateLeadSourceRequestDto createLeadSourceRequestDto, @MappingTarget LeadSource leadSource);
}
