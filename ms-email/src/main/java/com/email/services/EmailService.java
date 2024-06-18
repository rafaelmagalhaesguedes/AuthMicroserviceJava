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

@Service
public class EmailService {

  final EmailRepository emailRepository;
  final JavaMailSender javaMailSender;

  public EmailService(EmailRepository emailRepository, JavaMailSender javaMailSender) {
    this.emailRepository = emailRepository;
    this.javaMailSender = javaMailSender;
  }

  @Value(value = "${spring.mail.username}")
  private String emailFrom;

  @Transactional
  public Email sendEmail(Email email) {
    try {
      email.setSendDateEmail(LocalDateTime.now());
      email.setEmailFrom(emailFrom);

      SimpleMailMessage message = new SimpleMailMessage();
      message.setTo(email.getEmailTo());
      message.setSubject(email.getSubject());
      message.setText(email.getText());
      javaMailSender.send(message);

      email.setStatusEmail(StatusEmail.SENT);
    } catch (MailException e) {
      email.setStatusEmail(StatusEmail.ERROR);
    } finally {
      return emailRepository.save(email);
    }
  }
}
