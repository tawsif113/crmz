package com.wez.crm.mapper;

import com.wez.crm.dto.ContactResponseDto;
import com.wez.crm.dto.CreateContactRequestDto;
import com.wez.crm.dto.UpdateContactRequestDto;
import com.wez.crm.entity.Contact;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ContactMapper {


  ContactResponseDto toContactResponseDto(Contact contact);

  Contact toEntity(CreateContactRequestDto createContactRequestDto);

  @InheritConfiguration(name = "toEntity")
  void updateEntity(UpdateContactRequestDto updateContactRequestDto, @MappingTarget Contact contact);
}
