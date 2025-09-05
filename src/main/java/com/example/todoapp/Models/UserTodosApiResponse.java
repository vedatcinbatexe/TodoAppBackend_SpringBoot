package com.example.todoapp.Models;

import java.util.List;

public class UserTodosApiResponse {
    private Long userId;
    private String email;
    private String firstName;
    private String lastName;
    private List<TodoApiResponse> todos;

    public UserTodosApiResponse() {}

    public UserTodosApiResponse(Long userId, String email, String firstName, String lastName, List<TodoApiResponse> todos) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.todos = todos;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<TodoApiResponse> getTodos() {
        return this.todos;
    }

    public void setTodos(List<TodoApiResponse> todos) {
        this.todos = todos;
    }
}
