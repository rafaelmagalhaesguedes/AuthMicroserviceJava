package com.api.auth.service.exception;

/**
 * The type Person not found exception.
 */
public class PersonNotFoundException extends NotFoundException {

  /**
   * Instantiates a new Person not found exception.
   */
  public PersonNotFoundException() {
    super("Person not found.");
  }
}
