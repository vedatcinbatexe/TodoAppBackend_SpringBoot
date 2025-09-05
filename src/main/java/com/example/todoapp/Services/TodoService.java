package com.example.todoapp.Services;

import com.example.todoapp.Models.CreateTodoApiRequest;
import com.example.todoapp.Models.Todo;
import com.example.todoapp.Models.TodoCreatedApiResponse;
import com.example.todoapp.Models.User;
import com.example.todoapp.Repositories.TodoRepository;
import com.example.todoapp.Repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
