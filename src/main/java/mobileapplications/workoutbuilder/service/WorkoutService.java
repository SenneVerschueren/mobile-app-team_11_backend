package mobileapplications.workoutbuilder.service;

import mobileapplications.workoutbuilder.domain.Exercise;
import mobileapplications.workoutbuilder.domain.User;
import mobileapplications.workoutbuilder.domain.Workout;
import mobileapplications.workoutbuilder.exception.WorkoutServiceException;
import mobileapplications.workoutbuilder.repository.ExerciseRepository;
import mobileapplications.workoutbuilder.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;
    private final UserService userService;

    public WorkoutService(WorkoutRepository workoutRepository, UserService userService, ExerciseRepository exerciseRepository) {
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
        this.userService = userService;
    }

    // Create or update a workout
    public Workout createWorkout(String workoutName, Long userId) {
        try {
            User user = userService.getUserById(userId);
            if (user == null) {
                throw new WorkoutServiceException("User not found with id: " + userId);
            }
            Workout workout = new Workout(workoutName);
            workout.setUser(user);
            return workoutRepository.save(workout);
        } catch (Exception e) {
            throw new WorkoutServiceException(e.getMessage());
        }
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

    public Workout updateWorkout(Long id, String workoutName, Integer rest, List<Long> exerciseIds) {
        Optional<Workout> optionalWorkout = getWorkoutById(id);
        if (optionalWorkout.isEmpty()) {
            throw new WorkoutServiceException("Workout not found with id: " + id);
        }
        Workout workout = optionalWorkout.get();

        // Update workout details
        workout.setName(workoutName);
        workout.setRest(rest);

        // Rearrange exercises according to the provided list of exercise IDs
        List<Exercise> exercises = new ArrayList<>();
        for (Long exerciseId : exerciseIds) {
            Optional<Exercise> optionalExercise = exerciseRepository.findById(exerciseId);
            if (optionalExercise.isEmpty()) {
                throw new WorkoutServiceException("Exercise not found with id: " + exerciseId);
            }
            Exercise exercise = optionalExercise.get();
            exercises.add(exercise);
        }

        workout.updateExercisesOrder(exercises);

        return workoutRepository.save(workout);
    }

    public Exercise addExerciseToWorkout(Long workoutId, Exercise exercise) {
        Workout workout = getWorkoutById(workoutId).orElseThrow(() -> new WorkoutServiceException("Workout not found with id: " + workoutId));
        Exercise newExercise = workout.addExercise(exercise);
        // workoutRepository.save(workout);
        return exerciseRepository.save(newExercise);
    }

    public List<Workout> getWorkoutsByUserId(Long userId) {
        return workoutRepository.findWorkoutsByUserId(userId);
    }
}
