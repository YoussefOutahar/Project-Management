package com.yorastd.projectmanagement.Controllers;

import com.yorastd.projectmanagement.Models.AuthModels.Requests.ChangePasswordRequest;
import com.yorastd.projectmanagement.Models.AuthModels.Token.Token;
import com.yorastd.projectmanagement.Models.User.User;
import com.yorastd.projectmanagement.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/{projectId}/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        try {
            User user = service.getUser(id);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = service.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping
    public ResponseEntity<?> changePassword(
          @RequestBody ChangePasswordRequest request,
          Principal connectedUser
    ) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }
}
