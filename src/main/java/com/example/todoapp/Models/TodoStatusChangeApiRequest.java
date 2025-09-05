package com.example.todoapp.Models;

import com.example.todoapp.Enums.TodoStatus;

public class TodoStatusChangeApiRequest {
    private Long todoId;
    private TodoStatus status;

    public TodoStatusChangeApiRequest() {};

    public TodoStatusChangeApiRequest(Long todoId, TodoStatus status) {
        this.todoId = todoId;
        this.status = status;
    }

    public Long getTodoId() {
        return this.todoId;
    }

    public void setTodoId(Long todoId) {
        this.todoId = todoId;
    }

    public TodoStatus getStatus() {
        return this.status;
    }

    public void setStatus(TodoStatus status) {
        this.status = status;
    }
}
