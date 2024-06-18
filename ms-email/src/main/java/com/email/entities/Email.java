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

/**
 * The type Email.
 */
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

  /**
   * Gets email id.
   *
   * @return the email id
   */
  public UUID getEmailId() {
    return emailId;
  }

  /**
   * Sets email id.
   *
   * @param emailId the email id
   */
  public void setEmailId(UUID emailId) {
    this.emailId = emailId;
  }

  /**
   * Gets user id.
   *
   * @return the user id
   */
  public UUID getUserId() {
    return userId;
  }

  /**
   * Sets user id.
   *
   * @param userId the user id
   */
  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  /**
   * Gets email from.
   *
   * @return the email from
   */
  public String getEmailFrom() {
    return emailFrom;
  }

  /**
   * Sets email from.
   *
   * @param emailFrom the email from
   */
  public void setEmailFrom(String emailFrom) {
    this.emailFrom = emailFrom;
  }

  /**
   * Gets email to.
   *
   * @return the email to
   */
  public String getEmailTo() {
    return emailTo;
  }

  /**
   * Sets email to.
   *
   * @param emailTo the email to
   */
  public void setEmailTo(String emailTo) {
    this.emailTo = emailTo;
  }

  /**
   * Gets subject.
   *
   * @return the subject
   */
  public String getSubject() {
    return subject;
  }

  /**
   * Sets subject.
   *
   * @param subject the subject
   */
  public void setSubject(String subject) {
    this.subject = subject;
  }

  /**
   * Gets text.
   *
   * @return the text
   */
  public String getText() {
    return text;
  }

  /**
   * Sets text.
   *
   * @param text the text
   */
  public void setText(String text) {
    this.text = text;
  }

  /**
   * Gets send date email.
   *
   * @return the send date email
   */
  public LocalDateTime getSendDateEmail() {
    return sendDateEmail;
  }

  /**
   * Sets send date email.
   *
   * @param sendDateEmail the send date email
   */
  public void setSendDateEmail(LocalDateTime sendDateEmail) {
    this.sendDateEmail = sendDateEmail;
  }

  /**
   * Gets status email.
   *
   * @return the status email
   */
  public StatusEmail getStatusEmail() {
    return statusEmail;
  }

  /**
   * Sets status email.
   *
   * @param statusEmail the status email
   */
  public void setStatusEmail(StatusEmail statusEmail) {
    this.statusEmail = statusEmail;
  }
}
