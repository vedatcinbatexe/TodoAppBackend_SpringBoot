package com.example.todoapp.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.example.todoapp.Models.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.todoapp.Services.TodoService;
import com.example.todoapp.Services.UserPrincipal;
import com.example.todoapp.Services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UsersV1Controller {
    // Service DI
    private final UserService userService;
    private final TodoService todoService;

    public UsersV1Controller(UserService userService, TodoService todoService) {
        this.userService = userService;
        this.todoService = todoService;

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

        User user = userService.getOneUserWithTodos(currentUserId);

        if(user == null) {
            return  ResponseEntity.notFound().build();
        }

        List<TodoApiResponse> todoApiResponse = user.getTodos().stream()
            .map(t -> new TodoApiResponse(t.getTodoId(), t.getCaption(), t.getDescription()))
            .toList();

        UserTodosApiResponse response = new UserTodosApiResponse(user.getUserId(), user.getEmail(), user.getFirstName(), user.getLastName(), todoApiResponse);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/create-todo")
    public ResponseEntity<TodoCreatedApiResponse> getUserCreatedTodo(@Valid @RequestBody CreateTodoApiRequest request, Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Long currentUserId = userPrincipal.getId();

        // Call "createTodo" from todoService
        TodoCreatedApiResponse response = todoService.createTodo(currentUserId, request);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/change-todo-status")
    public ResponseEntity<TodoStatusChangedApiResponse> changeTodoStatus(@Valid @RequestBody TodoStatusChangeApiRequest request, Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Long currentUserId = userPrincipal.getId();

        // Call "changeTodoStatus" from todoService
        TodoStatusChangedApiResponse response = todoService.changeTodoStatus(currentUserId, request);

        return ResponseEntity.ok(response);
    }
}
