package com.example.todoapp.Models;

import com.example.todoapp.Enums.TodoStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "Todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long todoId;
    private String caption;
    private TodoStatus status;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    public Todo() {}

    public Todo(long todoId, String caption, TodoStatus status, String description) {
        this.todoId = todoId;
        this.caption = caption;
        this.status = status;
        this.description = description;
    }

    public long getTodoId() {
        return this.todoId;
    }

    public void setTodoId(long todoId) {
        this.todoId = todoId;
    }

    public String getCaption() {
        return this.caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public TodoStatus getStatus() {
        return this.status;
    }

    public void updateStatus(TodoStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
