package com.wez.crm.serviceImpl;

import com.wez.crm.dto.ContactResponseDto;
import com.wez.crm.dto.CreateContactRequestDto;
import com.wez.crm.dto.UpdateContactRequestDto;
import com.wez.crm.entity.Contact;
import com.wez.crm.entity.User;
import com.wez.crm.exception.NotFoundException;
import com.wez.crm.mapper.ContactMapper;
import com.wez.crm.repository.ContactRepository;
import com.wez.crm.service.ContactService;
import com.wez.crm.service.UserService;
import com.wez.crm.util.constant.ApiResponseConstant;
import com.wez.crm.util.constant.AppConstant;
import com.wez.crm.util.constant.ExceptionMessageConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

  private final ContactRepository contactRepository;
  private final ContactMapper contactMapper;

  @Override
  public Contact getContactById(Long id) {
    return contactRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID, id, AppConstant.CONTACT)));
  }

  @Override
  public ContactResponseDto createContact(CreateContactRequestDto createContactRequestDto) {
    Contact contact = prepareContactEntity(createContactRequestDto);
    return contactMapper.toContactResponseDto(contactRepository.save(contact));
  }

  @Override
  public ContactResponseDto updateContact(Long id, UpdateContactRequestDto updateContactRequestDto) {
    Contact existingContact = getContactById(id);
    contactMapper.updateEntity(updateContactRequestDto,existingContact);
    return contactMapper.toContactResponseDto(contactRepository.save(existingContact));
  }

  @Override
  public String deleteContact(Long id) {
    if (!contactRepository.existsById(id)) {
      throw new NotFoundException(String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID, id, AppConstant.CONTACT));
    }
    contactRepository.deleteById(id);
    return String.format(ApiResponseConstant.DELETE_SUCCESSFUL_TEMPLATE, AppConstant.CONTACT);
  }

  @Override
  public ContactResponseDto getContactResponseDtoById(Long id) {
    return contactRepository.findContactResponseDtoById(id)
        .orElseThrow(() -> new NotFoundException(String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID, id, AppConstant.CONTACT)));
  }

  @Override
  public Boolean isContactExistsById(Long id) {
    return contactRepository.existsById(id);
  }

  @Override
  public Contact getReferenceById(Long id) {
    return contactRepository.getReferenceById(id);
  }

  @Override
  public Page<ContactResponseDto> findAllContactResponseDto(Pageable pageable) {
    return contactRepository.findAllContactResponseDto(pageable);
  }

  private Contact prepareContactEntity(CreateContactRequestDto createContactRequestDto) {
    return contactMapper.toEntity(createContactRequestDto);
  }
}
