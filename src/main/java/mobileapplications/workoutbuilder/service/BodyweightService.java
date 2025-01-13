package mobileapplications.workoutbuilder.service;

import mobileapplications.workoutbuilder.domain.Bodyweight;
import mobileapplications.workoutbuilder.repository.BodyweightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BodyweightService {

    private final BodyweightRepository bodyweightRepository;


    public BodyweightService(BodyweightRepository bodyweightRepository) {
        this.bodyweightRepository = bodyweightRepository;
    }

    public List<Bodyweight> getAllBodyweight() {
        return bodyweightRepository.findAll();
    }

    public List<Bodyweight> getBodyweightByUserId(Long id) {
        return bodyweightRepository.findByUserId(id);
    }
}
