package mobileapplications.workoutbuilder.repository;

import jakarta.annotation.PostConstruct;
import mobileapplications.workoutbuilder.domain.User;

public class DbInitializer {
    private UserRepository userRepository;

    public DbInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        userRepository.deleteAll();
        User user = new User("John", "john.doe@test.com", "t");
        userRepository.save(user);
    }
}
