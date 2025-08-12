package com.wez.crm.entity;

import com.wez.crm.enums.LeadStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "leads")
public class Lead extends BaseEntity {

  @ManyToOne(optional = false)
  @JoinColumn(name = "contact_id", nullable = false)
  private Contact contact;

  @ManyToOne
  @JoinColumn(name = "source_id", nullable = false)
  private LeadSource source;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private LeadStatus status= LeadStatus.NEW;

  @Column(precision = 15, scale = 2)
  private BigDecimal estimatedValue;

  @ManyToOne
  @JoinColumn(name = "assigned_sales_rep_id")
  private User assignedSalesRep;

  private LocalDateTime lastActivityDate;
  private LocalDate followUpDate;
  private LocalDate expectedCloseDate;
}
