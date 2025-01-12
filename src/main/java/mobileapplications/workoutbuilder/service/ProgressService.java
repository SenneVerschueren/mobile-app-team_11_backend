package mobileapplications.workoutbuilder.service;

import java.util.List;

import org.springframework.stereotype.Service;

import mobileapplications.workoutbuilder.domain.Progress;
import mobileapplications.workoutbuilder.repository.ProgressRepository;

@Service
public class ProgressService {

    private final ProgressRepository progressRepository;

    public ProgressService(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    public List<Progress> getAllProgress() {
        return progressRepository.findAll();
    }

    public List<Progress> getProgressByExerciseId(Long id) {
        return progressRepository.findByExerciseId(id);
    }

}
