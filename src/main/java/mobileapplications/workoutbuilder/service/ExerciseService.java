package mobileapplications.workoutbuilder.service;

import mobileapplications.workoutbuilder.domain.Exercise;
import mobileapplications.workoutbuilder.domain.Workout;
import mobileapplications.workoutbuilder.exception.ExerciseServiceException;
import mobileapplications.workoutbuilder.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

    @Autowired
    private final ExerciseRepository exerciseRepository;

    @Autowired
    private final WorkoutService workoutService;

    
    public ExerciseService(ExerciseRepository exerciseRepository, WorkoutService workoutService) {
        this.exerciseRepository = exerciseRepository;
        this.workoutService = workoutService;
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public Exercise getExerciseById(Long id) {
        Exercise exercise = exerciseRepository.findById(id).orElse(null);
        if (exercise != null) {
            return exercise;
        } else {
            throw new ExerciseServiceException("Exercise not found with id: " + id);
        }
    }

    public Exercise createExercise(Exercise exercise, Long workoutId) {
        Workout workout = workoutService.getWorkoutById(workoutId).orElse(null);
        if (workout != null) {
            exercise.setWorkout(workout);
            return exerciseRepository.save(exercise);
        } else {
            // handle the case where the workout is not found
            throw new ExerciseServiceException("Workout not found with id: " + workoutId);
        }
        
    }

    public Exercise createExerciseByName(String exerciseName) {
        Exercise exercise = new Exercise(exerciseName);
        exercise.setName(exerciseName);
        return exerciseRepository.save(exercise);
    }

    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }
}
