package mobileapplications.workoutbuilder.controller;

import mobileapplications.workoutbuilder.domain.User;
import mobileapplications.workoutbuilder.domain.Workout;
import mobileapplications.workoutbuilder.exception.UserServiceException;
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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return user;
    }

    @GetMapping("/{id}/workouts")
    public List<Workout> getUserWorkouts(@PathVariable Long id) {
        try {
            List<Workout> workouts = userService.getAllWorkoutsForUser(id);
            return workouts;
        } catch (UserServiceException e) {
            throw new UserServiceException(e.getMessage());
        }
    }

    @PutMapping("/{email}")
    public User updateUser(@PathVariable String email, @RequestBody User user) {
        return userService.updateUser(email, user);
    }

    @PutMapping("/{userId}/streakGoal/{streakGoal}")
    public Integer updateStreakGoal(@PathVariable Long userId, @PathVariable Integer streakGoal) {
        userService.updateStreakGoal(userId, streakGoal);
        return userService.getUserById(userId).getStreakGoal();
    }

    @PutMapping("/{userId}/streakProgress")
    public Integer updateStreakProgress(@PathVariable Long userId) {
        userService.completedWorkout(userId);
        return userService.getUserById(userId).getStreakProgress();
    }
    
}
