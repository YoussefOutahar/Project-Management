package com.yorastd.projectmanagement.Controllers;

import com.yorastd.projectmanagement.Models.AuthModels.Requests.AuthenticationRequest;
import com.yorastd.projectmanagement.Models.AuthModels.Requests.AuthenticationResponse;
import com.yorastd.projectmanagement.Models.User.User;
import com.yorastd.projectmanagement.Services.Auth.AuthenticationService;
import com.yorastd.projectmanagement.Models.AuthModels.Requests.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }

  @PostMapping("/me")
  public ResponseEntity<User> me(
      String access_token
  ) {
    return ResponseEntity.ok(service.me(access_token));
  }
}
