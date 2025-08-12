package com.wez.crm.entity;

import com.wez.crm.enums.ActivityStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "activities")
public class Activity extends BaseEntity{

  @ManyToOne
  @JoinColumn(name = "contact_id")
  private Contact contact;

  @ManyToOne
  @JoinColumn(name = "lead_id")
  private Lead lead;

  @ManyToOne
  @JoinColumn(name = "activity_type_id")
  private ActivityType type;

  @Column(nullable = false)
  private String subject;

  private String description;

  @Column(nullable = false)
  private LocalDateTime activityDateTime;

  @ManyToOne(optional = false)
  @JoinColumn(name = "assigned_user_id", nullable = false)
  private User assignedUser;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ActivityStatus status;
}
