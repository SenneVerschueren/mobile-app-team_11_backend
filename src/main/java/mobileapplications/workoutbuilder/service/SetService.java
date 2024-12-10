package mobileapplications.workoutbuilder.service;

import mobileapplications.workoutbuilder.domain.Exercise;
import mobileapplications.workoutbuilder.domain.Set;
import mobileapplications.workoutbuilder.repository.ExerciseRepository;
import mobileapplications.workoutbuilder.repository.SetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetService {

    @Autowired
    private SetRepository setRepository;

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private ExerciseRepository exerciseRepository;

    public List<Set> getAllSets() {
        return setRepository.findAll();
    }

    public Set getSetById(Long id) {
        return setRepository.findById(id).orElse(null);
    }

    public Set createSet(Set set, Long exerciseId) {
        Exercise exercise = exerciseService.getExerciseById(exerciseId);

        exercise.addSet(set);

        exerciseRepository.save(exercise);

        return setRepository.save(set);
    }

    public Set updateSet(Long id, Set newValuesSet) {
        Set set = getSetById(id);

        set.updateValuesSet(newValuesSet.getReps(), newValuesSet.getWeight(), newValuesSet.getDuration(),
                newValuesSet.getExercise());

        return setRepository.save(set);
    }
}