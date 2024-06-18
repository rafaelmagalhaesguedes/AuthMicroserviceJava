package com.email.consumers;

import com.email.dtos.EmailRecordDto;
import com.email.entities.Email;
import com.email.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * The type Email consumer.
 */
@Component
public class EmailConsumer {

  /**
   * The Email service.
   */
  final EmailService emailService;

  /**
   * Instantiates a new Email consumer.
   *
   * @param emailService the email service
   */
  public EmailConsumer(EmailService emailService) {
    this.emailService = emailService;
  }

  /**
   * Listen email queue.
   *
   * @param emailRecordDto the email record dto
   */
  @RabbitListener(queues = "${broker.queue.email.name}")
  public void listenEmailQueue(@Payload EmailRecordDto emailRecordDto) {
    var email = new Email();
    // Converts DTO to Entity
    BeanUtils.copyProperties(emailRecordDto, email);
    // Send email
    emailService.sendEmail(email);
  }
}
