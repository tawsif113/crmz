package com.wez.crm.service;

import com.wez.crm.dto.ContactResponseDto;
import com.wez.crm.dto.CreateContactRequestDto;
import com.wez.crm.dto.UpdateContactRequestDto;
import com.wez.crm.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContactService {

  Contact getContactById(Long id);

  ContactResponseDto createContact(CreateContactRequestDto createContactRequestDto);

  ContactResponseDto updateContact(Long id, UpdateContactRequestDto createContactRequestDto);

  String deleteContact(Long id);

  ContactResponseDto getContactResponseDtoById(Long id);

  Boolean isContactExistsById(Long id);

  Contact getReferenceById(Long id);

  Page<ContactResponseDto> findAllContactResponseDto(Pageable pageable);
}
