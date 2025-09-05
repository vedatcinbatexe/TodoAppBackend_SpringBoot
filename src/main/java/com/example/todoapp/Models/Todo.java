package com.example.todoapp.Models;

import com.example.todoapp.Enums.TodoStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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

    public Todo(String caption, TodoStatus status, String description, User user) {
        this.caption = caption;
        this.status = status;
        this.description = description;
        this.user = user;
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

    public void setStatus(TodoStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
