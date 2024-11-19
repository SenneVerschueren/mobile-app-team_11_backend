package mobileapplications.workoutbuilder.service;

import mobileapplications.workoutbuilder.domain.Workout;
import mobileapplications.workoutbuilder.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    // Create or update a workout
    public Workout saveWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }

    // Get all workouts
    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAll();
    }

    // Get a workout by id
    public Optional<Workout> getWorkoutById(Long id) {
        return workoutRepository.findById(id);
    }

    // Delete a workout by id
    public void deleteWorkout(Long id) {
        workoutRepository.deleteById(id);
    }
}
