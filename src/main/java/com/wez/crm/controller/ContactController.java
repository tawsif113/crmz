package com.wez.crm.controller;

import static com.wez.crm.util.constant.RouteConstant.CONTACT_BASE_PATH;
import static com.wez.crm.util.constant.RouteConstant.PATH_ID;

import com.wez.crm.dto.ApiResponse;
import com.wez.crm.dto.ContactResponseDto;
import com.wez.crm.dto.CreateContactRequestDto;
import com.wez.crm.dto.PaginationDto;
import com.wez.crm.dto.UpdateContactRequestDto;
import com.wez.crm.service.ContactService;
import com.wez.crm.util.constant.ApiResponseConstant;
import com.wez.crm.util.constant.AppConstant;
import com.wez.crm.util.helper.ApiResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(CONTACT_BASE_PATH)
public class ContactController {

  private final ContactService contactService;

  @PostMapping
  public ResponseEntity<ApiResponse<ContactResponseDto>> createContact(@Valid @RequestBody CreateContactRequestDto createContactRequestDto) {
    return ApiResponseUtil.success(contactService.createContact(createContactRequestDto),
        String.format(ApiResponseConstant.CREATE_SUCCESSFUL_TEMPLATE, AppConstant.CONTACT));
  }

  @GetMapping(PATH_ID)
  public ResponseEntity<ApiResponse<ContactResponseDto>> getContactById(@PathVariable Long id) {
    return ApiResponseUtil.success(contactService.getContactResponseDtoById(id),
        String.format(ApiResponseConstant.FETCH_SUCCESSFUL_TEMPLATE, AppConstant.CONTACT));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<Page<ContactResponseDto>>> getAllContacts(PaginationDto paginationDto) {
    return ApiResponseUtil.success(contactService.findAllContactResponseDto(paginationDto.toPageable()),
        String.format(ApiResponseConstant.FETCH_SUCCESSFUL_TEMPLATE, AppConstant.CONTACT));
  }

  @PatchMapping(PATH_ID)
  public ResponseEntity<ApiResponse<ContactResponseDto>> updateContact(@PathVariable Long id,
      @RequestBody UpdateContactRequestDto updateContactRequestDto) {
    return ApiResponseUtil.success(contactService.updateContact(id, updateContactRequestDto),
        String.format(ApiResponseConstant.UPDATE_SUCCESSFUL_TEMPLATE, AppConstant.CONTACT));
  }

  @DeleteMapping(PATH_ID)
  public ResponseEntity<ApiResponse<String>> deleteContact(@PathVariable Long id) {
    return ApiResponseUtil.success(contactService.deleteContact(id),
        String.format(ApiResponseConstant.DELETE_SUCCESSFUL_TEMPLATE, AppConstant.CONTACT));
  }
}
