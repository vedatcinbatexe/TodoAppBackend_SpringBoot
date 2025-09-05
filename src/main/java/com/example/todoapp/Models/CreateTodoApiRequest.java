package com.example.todoapp.Models;

import com.example.todoapp.Enums.TodoStatus;

public class CreateTodoApiRequest {
    private String caption;
    private String description;
    private TodoStatus status;

    public CreateTodoApiRequest() {}

    public CreateTodoApiRequest(String caption, String description, TodoStatus status) {
        this.caption = caption;
        this.description = description;
        this.status = status;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TodoStatus getStatus() {
        return status;
    }

    public void updateStatus(TodoStatus status) {
        this.status = status;
    }
}
