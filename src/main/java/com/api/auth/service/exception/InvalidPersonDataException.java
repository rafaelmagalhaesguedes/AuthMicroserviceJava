package com.api.auth.service.exception;

public class InvalidPersonDataException extends NotFoundException {
  public InvalidPersonDataException(String message) {
    super(message);
  }
}
