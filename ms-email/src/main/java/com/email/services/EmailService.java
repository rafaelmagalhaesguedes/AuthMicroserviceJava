package com.email.services;

import com.email.entities.Email;
import com.email.enums.StatusEmail;
import com.email.repositories.EmailRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * The type Email service.
 */
@Service
public class EmailService {

  /**
   * The Email repository.
   */
  final EmailRepository emailRepository;
  /**
   * The Java mail sender.
   */
  final JavaMailSender javaMailSender;

  /**
   * Instantiates a new Email service.
   *
   * @param emailRepository the email repository
   * @param javaMailSender  the java mail sender
   */
  public EmailService(EmailRepository emailRepository, JavaMailSender javaMailSender) {
    this.emailRepository = emailRepository;
    this.javaMailSender = javaMailSender;
  }

  @Value(value = "${spring.mail.username}")
  private String emailFrom;

  /**
   * Send email email.
   *
   * @param email the email
   */
  @Transactional
  public void sendEmail(Email email) {
    email.setSendDateEmail(LocalDateTime.now());
    email.setEmailFrom(emailFrom);

    try {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setTo(email.getEmailTo());
      message.setSubject(email.getSubject());
      message.setText(email.getText());
      javaMailSender.send(message);

      email.setStatusEmail(StatusEmail.SENT);
    } catch (MailException e) {
      email.setStatusEmail(StatusEmail.ERROR);
    }

    emailRepository.save(email);
  }
}
