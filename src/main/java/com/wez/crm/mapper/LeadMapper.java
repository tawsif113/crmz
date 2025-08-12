package com.wez.crm.mapper;

import com.wez.crm.dto.CreateLeadRequestDto;
import com.wez.crm.dto.LeadResponseDto;
import com.wez.crm.dto.UpdateLeadRequestDto;
import com.wez.crm.entity.Contact;
import com.wez.crm.entity.Lead;
import com.wez.crm.entity.LeadSource;
import com.wez.crm.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface LeadMapper {

  LeadResponseDto toResponseDto(Lead lead);

  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "contact", source = "contact")
  @Mapping(target = "source", source = "leadSource")
  @Mapping(target = "assignedSalesRep", source = "assignedSalesRep")
  @Mapping(target = "estimatedValue", source = "createLeadRequestDto.estimatedValue")
  @Mapping(target = "lastActivityDate", source = "createLeadRequestDto.lastActivityDate")
  @Mapping(target = "followUpDate", source = "createLeadRequestDto.followUpDate")
  @Mapping(target = "expectedCloseDate", source = "createLeadRequestDto.expectedCloseDate")
  Lead toEntity(CreateLeadRequestDto createLeadRequestDto, Contact contact, User assignedSalesRep, LeadSource leadSource);

  @BeanMapping(ignoreByDefault = true,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @Mapping(target = "contact", source = "contact")
  @Mapping(target = "source", source = "leadSource")
  @Mapping(target = "assignedSalesRep", source = "assignedSalesRep")
  @Mapping(target = "estimatedValue", source = "updateLeadRequestDto.estimatedValue")
  @Mapping(target = "lastActivityDate", source = "updateLeadRequestDto.lastActivityDate")
  @Mapping(target = "followUpDate", source = "updateLeadRequestDto.followUpDate")
  @Mapping(target = "expectedCloseDate", source = "updateLeadRequestDto.expectedCloseDate")
  void updateEntityFromRequest(UpdateLeadRequestDto updateLeadRequestDto, @MappingTarget Lead lead, Contact contact, User assignedSalesRep, LeadSource leadSource);
}
