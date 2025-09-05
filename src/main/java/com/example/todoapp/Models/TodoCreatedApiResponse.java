package com.example.todoapp.Models;

import com.example.todoapp.Enums.TodoStatus;

public class TodoCreatedApiResponse {
    private String email;
    private String firstName;
    private String lastName;
    private Long todoId;
    private String caption;
    private TodoStatus status;
    private String description;

    public TodoCreatedApiResponse() {}

    public TodoCreatedApiResponse(String email, String firstName, String lastName, long todoId, String caption, TodoStatus status, String description) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.todoId = todoId;
        this.caption = caption;
        this.status = status;
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getTodoId() {
        return this.todoId;
    }

    public void setTodoId(Long todoId) {
        this.todoId = todoId;
    }

    public String getCaption() {
        return this.caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public TodoStatus getStatus() {
        return status;
    }

    public void updateStatus(TodoStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
