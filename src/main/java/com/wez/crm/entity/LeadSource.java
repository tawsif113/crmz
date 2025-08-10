package com.wez.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "lead_sources")
public class LeadSource extends BaseEntity{
    private String name;
}
