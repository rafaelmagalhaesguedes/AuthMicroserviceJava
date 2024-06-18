package com.api.auth.controller.dto;

import com.api.auth.entity.Person;
import java.util.UUID;

/**
 * DTO for person data.
 */
public record PersonDto(
    UUID id,
    String fullName,
    String username,
    String email
) {

  /**
   * Convert from Person entity.
   *
   * @param person the person entity
   * @return the person dto
   */
  public static PersonDto fromEntity(Person person) {
    return new PersonDto(
        person.getId(),
        person.getFullName(),
        person.getUsername(),
        person.getEmail()
    );
  }
}
