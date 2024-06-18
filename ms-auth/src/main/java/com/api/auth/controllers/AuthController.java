package com.api.auth.controllers;

import com.api.auth.controllers.dto.AuthDto;
import com.api.auth.controllers.dto.TokenDto;
import com.api.auth.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Auth controller.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  /**
   * Instantiates a new Auth controller.
   *
   * @param authenticationManager the authentication manager
   * @param tokenService          the token service
   */
  @Autowired
  public AuthController(
      AuthenticationManager authenticationManager,
      TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  /**
   * Login response entity.
   *
   * @param authDto the auth dto
   * @return the response entity
   */
  @PostMapping("/login")
  public ResponseEntity<TokenDto> login(@RequestBody AuthDto authDto) {
    try {
      UsernamePasswordAuthenticationToken usernamePassword =
          new UsernamePasswordAuthenticationToken(authDto.username(), authDto.password());

      Authentication auth = authenticationManager.authenticate(usernamePassword);

      String token = tokenService.generateToken(auth.getName());

      return ResponseEntity.ok(new TokenDto(token));
    } catch (BadCredentialsException ex) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(new TokenDto("Invalid username or password."));
    }
  }
}
