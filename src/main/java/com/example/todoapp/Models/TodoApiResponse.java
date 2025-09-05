package com.example.todoapp.Models;

public class TodoApiResponse {
    private Long todoId;
    private String caption;
    private String description;

    public TodoApiResponse() {}

    public TodoApiResponse(Long todoId, String caption, String description) {
        this.todoId = todoId;
        this.caption = caption;
        this.description = description;
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
