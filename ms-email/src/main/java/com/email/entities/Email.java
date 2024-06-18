package com.email.entities;

import com.email.enums.StatusEmail;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "emails")
public class Email {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID emailId;
  private UUID userId;
  private String emailFrom;
  private String emailTo;
  private String subject;

  @Column(columnDefinition = "TEXT")
  private String text;
  private LocalDateTime sendDateEmail;
  private StatusEmail statusEmail;

  public UUID getEmailId() {
    return emailId;
  }

  public void setEmailId(UUID emailId) {
    this.emailId = emailId;
  }

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public String getEmailFrom() {
    return emailFrom;
  }

  public void setEmailFrom(String emailFrom) {
    this.emailFrom = emailFrom;
  }

  public String getEmailTo() {
    return emailTo;
  }

  public void setEmailTo(String emailTo) {
    this.emailTo = emailTo;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public LocalDateTime getSendDateEmail() {
    return sendDateEmail;
  }

  public void setSendDateEmail(LocalDateTime sendDateEmail) {
    this.sendDateEmail = sendDateEmail;
  }

  public StatusEmail getStatusEmail() {
    return statusEmail;
  }

  public void setStatusEmail(StatusEmail statusEmail) {
    this.statusEmail = statusEmail;
  }
}
