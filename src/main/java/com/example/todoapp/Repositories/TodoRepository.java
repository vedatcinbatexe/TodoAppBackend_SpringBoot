package com.example.todoapp.Repositories;

import com.example.todoapp.Models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
