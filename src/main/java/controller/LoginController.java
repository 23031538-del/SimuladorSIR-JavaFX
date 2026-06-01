package controller;

import model.User;
import service.UserService;

import java.util.Optional;

public class LoginController {
    private final UserService userService = new UserService();

    public Optional<User> login(String username, String password) {
        try {
            return userService.authenticate(username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<User> register(String username, String email, String password) {
        try {
            return Optional.of(userService.register(username, email, password));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}