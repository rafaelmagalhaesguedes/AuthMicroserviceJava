package com.api.auth.services.exception;

public class InvalidPersonDataException extends NotFoundException {
  public InvalidPersonDataException(String message) {
    super(message);
  }
}
