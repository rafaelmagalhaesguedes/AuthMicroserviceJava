package com.api.auth.controller.dto;

import com.api.auth.entity.Person;

/**
 * The type Person dto.
 */
public record PersonDto(
    Long id,
    String fullName,
    String username,
    String email
) {

  /**
   * From entity person dto.
   *
   * @param person the person
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
