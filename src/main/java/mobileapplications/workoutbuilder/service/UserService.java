package mobileapplications.workoutbuilder.service;

import mobileapplications.workoutbuilder.domain.User;
import mobileapplications.workoutbuilder.domain.Workout;
import mobileapplications.workoutbuilder.exception.UserServiceException;
import mobileapplications.workoutbuilder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String name, String email, String password) {
        User existingUser = userRepository.findByEmail(email);

        if (existingUser != null) {
            throw new IllegalArgumentException("Email is already in use");
        }

        User newUser = new User(name, email, password);
        return userRepository.save(newUser);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Workout> getAllWorkoutsForUser(Long userId) {
        User user = getUserById(userId);
        if (user == null) {
            throw new UserServiceException("User not found with ID: " + userId);
        }

        return user.getWorkouts();
    }

    public User updateUser(String email, User newValuesUser) {
        User user = getUserByEmail(email);

        if (!user.getEmail().equals(newValuesUser.getEmail())) {
            throw new UserServiceException("Email cannot be changed");
        }

        user.updateValuesUser(newValuesUser.getName(), newValuesUser.getEmail(), newValuesUser.getPassword());

        return userRepository.save(user);
    }

    public void completedWorkout(Long userId) {
        User user = getUserById(userId);
        if (user == null) {
            throw new UserServiceException("User not found with ID: " + userId);
        }
        user.setStreakProgress(user.getStreakProgress() + 1);
        userRepository.save(user);
    }

    @Scheduled(cron = "0 0 0 * * SUN")
    public void validateStreaks() {
        List<User> users = userRepository.findAll();
        System.out.println("Validating streaks for all users, usercount: " + users.size());
        for (User user : users) {
            System.out.println("Validating streak for user: " + user.getName());
            if (user.getStreakGoal() == 0) {
                System.out.println("Streak goal is 0, skipping validation");
            } else if (user.getStreakGoal() <= user.getStreakProgress()) {
                System.out.println("Streak goal reached, resetting progress");
                user.setStreakProgress(0);
                user.setStreak(user.getStreak() + 1);
            } else {
                System.out.println("Streak goal not reached, resetting progress and streak");
                user.setStreakProgress(0);
                user.setStreak(0);
            }
            userRepository.save(user);
        }
    }

    public void updateStreakGoal(Long userId, Integer streakGoal) {
        User user = getUserById(userId);
        if (user == null) {
            throw new UserServiceException("User not found with ID: " + userId);
        } else if (streakGoal < 0) {
            throw new UserServiceException("Streak goal must be at least 0");
        } else if (streakGoal > 7) {
            throw new UserServiceException("Streak goal must be at most 7");
        }
        user.setStreakGoal(streakGoal);
        userRepository.save(user);
    }
}
