package com.api.auth.utils;

import com.api.auth.entity.Person;
import com.api.auth.service.exception.InvalidPersonDataException;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

/**
 * The type Person validator.
 */
@Component
public class PersonValidator {

  /**
   * Validate person data.
   *
   * @param person the person
   * @throws InvalidPersonDataException the invalid person data exception
   */
  public void validatePersonData(Person person) throws InvalidPersonDataException {
    validateFullName(person.getFullName());
    validateUsername(person.getUsername());
    validateEmail(person.getEmail());
    validatePassword(person.getPassword());
    validateRole(person.getRole());
  }

  private void validateFullName(String fullName) throws InvalidPersonDataException {
    if (StringUtils.isBlank(fullName)) {
      throw new InvalidPersonDataException("Full name is required");
    }
  }

  private void validateUsername(String username) throws InvalidPersonDataException {
    if (StringUtils.isBlank(username)) {
      throw new InvalidPersonDataException("Username is required");
    }
  }

  private void validateEmail(String email) throws InvalidPersonDataException {
    if (StringUtils.isBlank(email)) {
      throw new InvalidPersonDataException("Email is required");
    }
    if (!EmailValidator.isValidEmail(email)) {
      throw new InvalidPersonDataException("Invalid email format");
    }
  }

  private void validatePassword(String password) throws InvalidPersonDataException {
    if (StringUtils.isBlank(password)) {
      throw new InvalidPersonDataException("Password is required");
    }
    if (password.length() < 8) {
      throw new InvalidPersonDataException("Password must be at least 8 characters long");
    }
  }

  private void validateRole(String role) throws InvalidPersonDataException {
    if (StringUtils.isBlank(role)) {
      throw new InvalidPersonDataException("Role is required");
    }
  }
}

