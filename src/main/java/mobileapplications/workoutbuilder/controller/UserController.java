package mobileapplications.workoutbuilder.controller;

import mobileapplications.workoutbuilder.domain.User;
import mobileapplications.workoutbuilder.domain.Workout;
import mobileapplications.workoutbuilder.exception.AuthServiceException;
import mobileapplications.workoutbuilder.exception.UserServiceException;
import mobileapplications.workoutbuilder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:8081")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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

    // get all workouts for a user
    @GetMapping("/{id}/workouts")
    public List<Workout> getUserWorkouts(@PathVariable Long id) {
        try {
            List<Workout> workouts = userService.getAllWorkoutsForUser(id);
            return workouts;
        } catch (UserServiceException e) {
            throw new UserServiceException(e.getMessage());
        }
    }
}
