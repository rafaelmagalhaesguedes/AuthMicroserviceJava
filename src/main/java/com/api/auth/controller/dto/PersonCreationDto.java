package com.api.auth.controller.dto;

import com.api.auth.entity.Person;

/**
 * The type Person creation dto.
 */
public record PersonCreationDto(
    String fullName,
    String username,
    String email,
    String password,
    String role
) {

  /**
   * To entity person.
   *
   * @return the person
   */
  public Person toEntity() {
    return new Person(fullName, username, email, password, role);
  }
}
