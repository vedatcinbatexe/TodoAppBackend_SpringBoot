package com.example.todoapp.Controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.todoapp.Models.TodoApiResponse;
import com.example.todoapp.Models.User;
import com.example.todoapp.Models.UserTodosApiResponse;
import com.example.todoapp.Services.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
        User user = userService.getOneUser(userId);

        UserApiResponse userApiResponse = new UserApiResponse(user.getUserId(), user.getEmail(), user.getFirstName(), user.getLastName());

        return ResponseEntity.ok(userApiResponse);
    }

    @GetMapping("/get-todos")
    public ResponseEntity<UserTodosApiResponse> getUserTodos(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Long currentUserId = userPrincipal.getId();

        User user = userService.getOneUser(currentUserId);

        if(user == null) {
            return  ResponseEntity.notFound().build();
        }

        List<TodoApiResponse> todoApiResponse = user.getTodos().stream().map(t -> new TodoApiResponse(t.getTodoId(), t.getCaption(), t.getDescription())).toList();

        UserTodosApiResponse response = new UserTodosApiResponse(user.getUserId(), user.getEmail(), user.getFirstName(), user.getLastName(), todoApiResponse);

        return ResponseEntity.ok(response);
    }
}
