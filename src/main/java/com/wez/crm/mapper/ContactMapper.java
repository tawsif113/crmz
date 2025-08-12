package com.wez.crm.mapper;

import com.wez.crm.dto.ContactResponseDto;
import com.wez.crm.dto.CreateContactRequestDto;
import com.wez.crm.dto.UpdateContactRequestDto;
import com.wez.crm.entity.Contact;
import com.wez.crm.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ContactMapper {


  ContactResponseDto toContactResponseDto(Contact contact);

  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "user", source = "user")
  @Mapping(target = "phoneNumber", source = "createContactRequestDto.phoneNumber")
  @Mapping(target = "companyName", source = "createContactRequestDto.companyName")
  @Mapping(target = "jobTitle", source = "createContactRequestDto.jobTitle")
  Contact toEntity(CreateContactRequestDto createContactRequestDto, User user);

  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "user", source = "user")
  @Mapping(target = "phoneNumber", source = "updateContactRequestDto.phoneNumber")
  @Mapping(target = "companyName", source = "updateContactRequestDto.companyName")
  @Mapping(target = "jobTitle", source = "updateContactRequestDto.jobTitle")
  Contact updateEntity(UpdateContactRequestDto updateContactRequestDto, User user);
}
