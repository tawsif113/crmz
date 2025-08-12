package com.wez.crm.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserBasicInfo {
    private Long id;
    private String username;
    private String email;
}
