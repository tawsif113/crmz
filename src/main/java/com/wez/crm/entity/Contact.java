package com.wez.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contacts")
@Getter
@Setter
public class Contact extends BaseEntity {

  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String companyName;
  private String jobTitle;
}
