package com.wez.crm.entity;

import com.wez.crm.enums.NotificationChannel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "notifications")
public class Notification extends BaseEntity {

  @Enumerated(EnumType.STRING)
  @Column(name = "notification_type", nullable = false)
  private NotificationChannel notificationType;

  @Column(name = "content", nullable = false, columnDefinition = "TEXT")
  private String content;

  @Column(name = "subject")
  private String subject;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User receiver;

  @Column(name = "is_read", nullable = false)
  private Boolean read = false;

  @Column(name = "read_at")
  private LocalDateTime readAt;

  // Utility methods
  public void markAsRead() {
    this.read = true;
    this.readAt = LocalDateTime.now();
  }
  public boolean isUnread() {
    return !read;
  }

}
