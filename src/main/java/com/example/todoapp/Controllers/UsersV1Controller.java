package com.example.todoapp.Controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.todoapp.Models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoapp.Models.UserApiResponse;
import com.example.todoapp.Services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UsersV1Controller {
    // Service DI
    private final UserService userService;

    public UsersV1Controller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserApiResponse>> getAllUsers() {
        List<UserApiResponse> allUsers = userService.getAllUsers().stream()
                .map(u -> new UserApiResponse(u.getUserId(), u.getEmail(), u.getFirstName(), u.getLastName()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserApiResponse> getUser(@PathVariable Long userId) {
        Optional<User> user = userService.getOneUser(userId);

        if(user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        UserApiResponse userApiResponse = new UserApiResponse(user.get().getUserId(), user.get().getEmail(), user.get().getFirstName(), user.get().getLastName());

        return ResponseEntity.ok(userApiResponse);
    }
}
