package com.api.auth.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Email validator.
 */
public class EmailValidator {

  private static final String EMAIL_REGEX =
      "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

  /**
   * Is valid email boolean.
   *
   * @param email the email
   * @return the boolean
   */
  public static boolean isValidEmail(String email) {
    Pattern pattern = Pattern.compile(EMAIL_REGEX);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    String[] emails = {
        "user@example.com",
        "user123@example.com",
        "user+123@example.com",
        "user.name@example.com",
        "user123@example.co.uk",
        "user@sub.example.com",
        "user@example",
        "user@example.c",
        "user@example.com.",
        "user@example..com"
    };

    for (String email : emails) {
      System.out.println(email + ": " + isValidEmail(email));
    }
  }
}
