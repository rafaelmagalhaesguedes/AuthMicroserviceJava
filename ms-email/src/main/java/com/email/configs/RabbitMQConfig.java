package com.email.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Rabbit mq config.
 */
@Configuration
public class RabbitMQConfig {

  @Value("${broker.queue.email.name}")
  private String queue;

  /**
   * Queue queue.
   *
   * @return the queue
   */
  @Bean
  public Queue queue() {
    return new Queue(queue, true);
  }

  /**
   * Message converter jackson 2 json message converter.
   *
   * @return the jackson 2 json message converter
   */
  @Bean
  public Jackson2JsonMessageConverter messageConverter() {
    ObjectMapper objectMapper = new ObjectMapper();
    return new Jackson2JsonMessageConverter(objectMapper);
  }
}
