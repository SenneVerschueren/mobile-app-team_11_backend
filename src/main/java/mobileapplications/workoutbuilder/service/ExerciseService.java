package mobileapplications.workoutbuilder.service;

import mobileapplications.workoutbuilder.domain.Exercise;
import mobileapplications.workoutbuilder.domain.Workout;
import mobileapplications.workoutbuilder.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final WorkoutService workoutService;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository, WorkoutService workoutService) {
        this.exerciseRepository = exerciseRepository;
        this.workoutService = workoutService;
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public Optional<Exercise> getExerciseById(Long id) {
        return exerciseRepository.findById(id);
    }

    public Exercise createExercise(Exercise exercise, Long workoutId) {
        Optional<Workout> workoutOptional = workoutService.getWorkoutById(workoutId);
        if (workoutOptional.isPresent()) {
            Workout workout = workoutOptional.get();
            exercise.setWorkout(workout);
            workout.addExercise(exercise);
            workoutService.saveWorkout(workout);
            return workout.getExercises().get(workout.getExercises().size() - 1);
        } else {
            // handle the case where the workout is not found
            throw new IllegalArgumentException("Workout not found with id: " + workoutId);
        }
        
    }

    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }
}
