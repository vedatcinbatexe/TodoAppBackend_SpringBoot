package com.example.todoapp.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todoapp.Models.User;
import com.example.todoapp.Repositories.TodoRepository;
import com.example.todoapp.Repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    public UserService(UserRepository userRepository, TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getOneUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User getOneUserWithTodos(Long userId) {
        return userRepository.findByIdWithTodos(userId).orElse(null);
    }

    /*
    * public List<UserTodosApiResponse> getUserTodos(Long userId) {
        User user = userRepository.findById(userId).get();

        List<TodoApiResponse> userTodos = user.getTodos().stream().map().collect();

        UserTodosApiResponse userTodosApiResponse = new UserTodosApiResponse(user.getUserId(), user.getEmail(), user.getFirstName(), user.getLastName(), userTodos);
    }*/
}
