package mobileapplications.workoutbuilder.controller;

import mobileapplications.workoutbuilder.domain.User;
import mobileapplications.workoutbuilder.domain.Workout;
import mobileapplications.workoutbuilder.exception.UserServiceException;
import mobileapplications.workoutbuilder.service.AuthService;
import mobileapplications.workoutbuilder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final AuthService authService;

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get user by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return user;
    }

    // Get all workouts for a user
    @GetMapping("/{id}/workouts")
    public List<Workout> getUserWorkouts(@PathVariable Long id) {
        try {
            List<Workout> workouts = userService.getAllWorkoutsForUser(id);
            return workouts;
        } catch (UserServiceException e) {
            throw new UserServiceException(e.getMessage());
        }
    }

    // Update a user
    @PutMapping("/{email}")
    public User updateUser(@PathVariable String email, @RequestBody User user) {
        return userService.updateUser(email, user);
    }

    @PutMapping("/{id}/password")
    public User updatePassword(@PathVariable Long id, @RequestBody UpdatePasswordRequest passwordRequest) {
        return authService.updatePassword(id, passwordRequest.getCurrentPassword(), passwordRequest.getNewPassword());
    }
}

class UpdatePasswordRequest {
    private String currentPassword;
    private String newPassword;

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
