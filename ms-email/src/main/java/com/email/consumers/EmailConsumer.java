package com.email.consumers;

import com.email.dtos.EmailRecordDto;
import com.email.entities.Email;
import com.email.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

  final EmailService emailService;

  public EmailConsumer(EmailService emailService) {
    this.emailService = emailService;
  }

  @RabbitListener(queues = "${broker.queue.email.name}")
  public void listenEmailQueue(@Payload EmailRecordDto emailRecordDto) {
    // Converte em model(entity)
    var email = new Email();

    // Converte o DTO em Entidade
    BeanUtils.copyProperties(emailRecordDto, email);

    // Enviar email
    emailService.sendEmail(email);
  }
}
