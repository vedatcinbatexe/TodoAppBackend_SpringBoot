package com.example.todoapp.Models;

public class TodoStatusChangedApiResponse {
    private Long todoId;
    private String status;
    private String message;

    public TodoStatusChangedApiResponse() {}

    public TodoStatusChangedApiResponse(Long todoId, String status, String message) {
        this.todoId = todoId;
        this.status = status;
        this.message = message;
    }

    public Long getTodoId() {
        return todoId;
    }

    public void setTodoId(Long todoId) {
        this.todoId = todoId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
