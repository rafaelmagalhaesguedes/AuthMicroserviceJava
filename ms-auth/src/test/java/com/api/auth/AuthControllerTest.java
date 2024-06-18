package com.api.auth;

import com.api.auth.controllers.dto.AuthDto;
import com.api.auth.services.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AuthenticationManager authenticationManager;

  @MockBean
  private TokenService tokenService;

  @BeforeEach
  public void setUp() {
    GrantedAuthority authority =
        new SimpleGrantedAuthority("ROLE_USER");

    User userDetails =
        new User("testuser", "password", Collections.singleton(authority));

    Authentication authentication =
        new UsernamePasswordAuthenticationToken(userDetails, null, Collections.singleton(authority));

    when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
        .thenReturn(authentication);

    when(tokenService.generateToken("testuser"))
        .thenReturn("mocked-token");
  }

  @Test
  public void testLoginSuccess() throws Exception {
    AuthDto authDto = new AuthDto("testuser", "password");

    ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(authDto)));

    result.andExpect(MockMvcResultMatchers.status().isOk());

    result.andExpect(MockMvcResultMatchers
        .jsonPath("$.token").value("mocked-token"));
  }

  @Test
  public void testLoginInvalidCredentials() throws Exception {
    AuthDto authDto = new AuthDto("testuser", "wrongpassword");

    when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
        .thenThrow(BadCredentialsException.class);

    ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(authDto)));

    result.andExpect(MockMvcResultMatchers.status().isUnauthorized());
  }
}
