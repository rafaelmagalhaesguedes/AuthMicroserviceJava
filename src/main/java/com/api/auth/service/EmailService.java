package com.api.auth.service;

import com.api.auth.entity.Person;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * The type Email service.
 */
@Service
public class EmailService {

  @Autowired
  private JavaMailSender javaMailSender;

  /**
   * Send welcome email.
   *
   * @param user the user
   */
  public void sendWelcomeEmail(Person user) {
    String to = user.getEmail();
    String subject = "Welcome to our system";
    String text = createWelcomeEmailText(user);
    sendEmail(to, subject, text);
  }

  private void sendEmail(String to, String subject, String text) {
    MimeMessage message = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);

    try {
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(text);

      javaMailSender.send(message);
    } catch (MessagingException e) {
      e.getMessage();
    }
  }

  private String createWelcomeEmailText(Person user) {
    return "Hello "
           + user.getFullName()
           + ",\n\nWelcome to our system!"
           + " Your account has been created successfully\n.";
  }
}

