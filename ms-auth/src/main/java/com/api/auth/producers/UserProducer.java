package com.api.auth.producers;

import com.api.auth.controller.dto.EmailDto;
import com.api.auth.entity.Person;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * The type User producer.
 */
@Component
public class UserProducer {

  final RabbitTemplate rabbitTemplate;

  /**
   * Instantiates a new User producer.
   *
   * @param rabbitTemplate the rabbit template
   */
  public UserProducer(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @Value(value = "${broker.queue.email.name}")
  private String routingKey;

  /**
   * Publish message email.
   *
   * @param person the person
   */
  public void publishMessageEmail(Person person) {
    System.out.println("Person ID: " + person.getId());
    System.out.println("Person Email: " + person.getEmail());
    System.out.println("Person Full Name: " + person.getFullName());

    var emailDto = new EmailDto();
    emailDto.setUserId(person.getId());
    emailDto.setEmailTo(person.getEmail());
    emailDto.setSubject("Cadastro Realizado com sucesso!");
    emailDto.setText(person.getFullName() + ", seja bem-vindo(a)");

    System.out.println("EmailDto: " + emailDto);

    rabbitTemplate.convertAndSend("", routingKey, emailDto);
  }
}
