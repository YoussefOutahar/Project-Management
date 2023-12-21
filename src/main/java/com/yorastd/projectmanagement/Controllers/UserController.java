package com.yorastd.projectmanagement.Controllers;

import com.yorastd.projectmanagement.Models.AuthModels.Requests.ChangePasswordRequest;
import com.yorastd.projectmanagement.Models.AuthModels.Token.Token;
import com.yorastd.projectmanagement.Models.User.User;
import com.yorastd.projectmanagement.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PatchMapping
    public ResponseEntity<?> changePassword(
          @RequestBody ChangePasswordRequest request,
          Principal connectedUser
    ) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }
}
