package com.api.auth.service.exception;

/**
 * The type Duplicate email exception.
 */
public class DuplicateEmailException extends RuntimeException {

  /**
   * Instantiates a new Duplicate email exception.
   */
  public DuplicateEmailException() {
    super("Email already in use.");
  }
}
