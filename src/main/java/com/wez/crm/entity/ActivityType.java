package com.wez.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "activity_types")
public class ActivityType extends BaseEntity{
    private String name;
    private String description;
    private Boolean isActive = true;
}
