package com.example.todoapp.Services;

import org.springframework.stereotype.Service;

import com.example.todoapp.Models.CreateTodoApiRequest;
import com.example.todoapp.Models.Todo;
import com.example.todoapp.Models.TodoCreatedApiResponse;
import com.example.todoapp.Models.TodoStatusChangeApiRequest;
import com.example.todoapp.Models.TodoStatusChangedApiResponse;
import com.example.todoapp.Models.User;
import com.example.todoapp.Repositories.TodoRepository;
import com.example.todoapp.Repositories.UserRepository;

import jakarta.validation.Valid;

@Service
public class TodoService {
    private TodoRepository todoRepository;
    private UserRepository userRepository;

    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    public TodoCreatedApiResponse createTodo(Long currentUserId, @Valid CreateTodoApiRequest request) {
        User user = userRepository.findById(currentUserId).orElse(null);

        Todo todo = new Todo(request.getCaption(), request.getStatus(), request.getDescription(), user);

        Todo newTodo = todoRepository.save(todo);

        return new TodoCreatedApiResponse(user.getEmail(), user.getFirstName(), user.getLastName(), newTodo.getTodoId(), newTodo.getCaption(), newTodo.getStatus(), newTodo.getDescription());
    }

    public TodoStatusChangedApiResponse changeTodoStatus(Long userId, TodoStatusChangeApiRequest request) {
        User user = userRepository.findByIdWithTodos(userId).orElse(null);
        
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        Todo existingTodo = user.getTodos().stream()
            .filter(todo -> todo.getTodoId() == request.getTodoId())
            .findFirst()
            .orElse(null);

        if (existingTodo == null) {
            throw new RuntimeException("Todo not found or does not belong to this user");
        }

        String oldStatus = existingTodo.getStatus().toString();
        
        existingTodo.setStatus(request.getStatus());
        
        Todo updatedTodo = todoRepository.save(existingTodo);
        
        String message = String.format("Todo status changed from %s to %s",
            oldStatus, request.getStatus().toString());
        
        return new TodoStatusChangedApiResponse(
            updatedTodo.getTodoId(), 
            updatedTodo.getStatus().toString(), 
            message
        );
    }
}
