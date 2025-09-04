package com.example.todoapp.Models;

public class HealthResponse {
    private String data;

    // Default constructor (required by Jackson)
    public HealthResponse() {
    }

    // Constructor with parameter
    public HealthResponse(String data) {
        this.data = data;
    }

    // Getter
    public String getData() {
        return data;
    }

    // Setter
    public void setData(String data) {
        this.data = data;
    }
}
