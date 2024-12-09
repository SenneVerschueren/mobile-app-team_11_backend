package mobileapplications.workoutbuilder.service;

import mobileapplications.workoutbuilder.domain.User;
import mobileapplications.workoutbuilder.domain.Workout;
import mobileapplications.workoutbuilder.exception.UserServiceException;
import mobileapplications.workoutbuilder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create a new user
    public User createUser(String name, String email, String password) {
        User existingUser = userRepository.findByEmail(email);

        if (existingUser != null) {
            throw new IllegalArgumentException("Email is already in use");
        }

        User newUser = new User(name, email, password);
        return userRepository.save(newUser);
    }

    // Find user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Find user by email (if needed for login or unique checks)
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Delete user by ID
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false; // User not found
    }

    // Get all workouts for a user
    public List<Workout> getAllWorkoutsForUser(Long userId) {
        User user = getUserById(userId);
        if (user == null) {
            throw new UserServiceException("User not found with ID: " + userId);
        }

        return user.getWorkouts();
    }

    // Update user by ID
    public User updateUser(String email, User newInfo) {
        User user = getUserByEmail(email);

        if (user.getEmail() != newInfo.getEmail()) {
            throw new UserServiceException("Email cannot be changed");
        }

        user.updateValuesUser(newInfo.getName(), newInfo.getEmail(), newInfo.getPassword());

        return userRepository.save(user);
    }
}
