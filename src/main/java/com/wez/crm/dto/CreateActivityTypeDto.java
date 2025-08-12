package com.wez.crm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateActivityTypeDto {
    private String name;
    private String description;
    private Boolean isActive;
}
