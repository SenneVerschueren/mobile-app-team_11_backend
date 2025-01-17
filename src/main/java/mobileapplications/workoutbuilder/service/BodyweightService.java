package mobileapplications.workoutbuilder.service;

import mobileapplications.workoutbuilder.domain.Bodyweight;
import mobileapplications.workoutbuilder.domain.User;
import mobileapplications.workoutbuilder.repository.BodyweightRepository;
import mobileapplications.workoutbuilder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BodyweightService {

    private final BodyweightRepository bodyweightRepository;
    private final UserRepository userRepository;


    public BodyweightService(BodyweightRepository bodyweightRepository, UserRepository userRepository) {
        this.bodyweightRepository = bodyweightRepository;
        this.userRepository = userRepository;
    }

    public List<Bodyweight> getAllBodyweight() {
        return bodyweightRepository.findAll();
    }

    public List<Bodyweight> getBodyweightByUserId(Long id) {
        return bodyweightRepository.findByUserId(id);
    }

    public Bodyweight addBodyweight(Long userId, Bodyweight bodyweight) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        bodyweight.setUser(user);
        return bodyweightRepository.save(bodyweight);
    }
}
