package com.api.auth.controllers.dto;

import com.api.auth.entities.Person;
import com.api.auth.securities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO for creating a new person.
 */
public record PersonCreationDto(
    @NotBlank(message = "Full name is required")
    String fullName,

    @NotBlank(message = "Username is required")
    String username,

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    String email,

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    String password,

    @NotNull(message = "Role is required")
    Role role
) {

  /**
   * Convert to Person entity.
   *
   * @return the person entity
   */
  public Person toEntity() {
    return new Person(fullName, username, email, password, role);
  }
}
