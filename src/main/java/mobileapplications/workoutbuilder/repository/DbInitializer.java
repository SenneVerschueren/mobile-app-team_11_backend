package mobileapplications.workoutbuilder.repository;

import jakarta.annotation.PostConstruct;
import mobileapplications.workoutbuilder.domain.User;
import mobileapplications.workoutbuilder.domain.Workout;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DbInitializer {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final WorkoutRepository workoutRepository;

    public DbInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder, WorkoutRepository workoutRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.workoutRepository = workoutRepository;
    }

    @PostConstruct
    public void init() {
        userRepository.deleteAll();
        workoutRepository.deleteAll();

        User user1 = new User("test", "test@test.com", passwordEncoder.encode("test1234"));
        userRepository.save(user1);

        User user2 = new User("admin", "amdin@test.com", passwordEncoder.encode("admin1234"));
        userRepository.save(user2);

        Workout workoutUser1 = new Workout("Push");
        workoutUser1.setUser(user1);
        workoutRepository.save(workoutUser1);

        Workout workoutUser2 = new Workout("Pull");
        workoutUser2.setUser(user2);
        workoutRepository.save(workoutUser2);



    }



}
