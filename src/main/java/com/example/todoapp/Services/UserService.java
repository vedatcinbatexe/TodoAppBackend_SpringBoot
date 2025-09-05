package com.example.todoapp.Services;

import com.example.todoapp.Models.TodoApiResponse;
import com.example.todoapp.Models.User;
import com.example.todoapp.Models.UserTodosApiResponse;
import com.example.todoapp.Repositories.TodoRepository;
import com.example.todoapp.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return userRepository.findById(userId).get();
    }

    /*
    * public List<UserTodosApiResponse> getUserTodos(Long userId) {
        User user = userRepository.findById(userId).get();

        List<TodoApiResponse> userTodos = user.getTodos().stream().map().collect();

        UserTodosApiResponse userTodosApiResponse = new UserTodosApiResponse(user.getUserId(), user.getEmail(), user.getFirstName(), user.getLastName(), userTodos);
    }*/
}
