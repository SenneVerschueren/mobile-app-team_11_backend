package mobileapplications.workoutbuilder.service;

import mobileapplications.workoutbuilder.domain.User;
import mobileapplications.workoutbuilder.domain.Workout;
import mobileapplications.workoutbuilder.exception.WorkoutServiceException;
import mobileapplications.workoutbuilder.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutService {

    @Autowired
    private final WorkoutRepository workoutRepository;

    @Autowired
    private final UserService userService;

    public WorkoutService(WorkoutRepository workoutRepository, UserService userService) {
        this.workoutRepository = workoutRepository;
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

    public Workout updateWorkout(Long id, Workout newValuesWorkout) {
        Optional<Workout> optionalWorkout = getWorkoutById(id);
        if (!optionalWorkout.isPresent()) {
            throw new WorkoutServiceException("Workout not found with id: " + id);
        }
        Workout workout = optionalWorkout.get();

        workout.updateValuesWorkout(newValuesWorkout.getName(), newValuesWorkout.getRest());
        return workoutRepository.save(workout);
    }
}
