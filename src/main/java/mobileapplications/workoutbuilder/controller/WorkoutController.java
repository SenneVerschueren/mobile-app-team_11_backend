package mobileapplications.workoutbuilder.controller;

import mobileapplications.workoutbuilder.domain.Workout;
import mobileapplications.workoutbuilder.service.WorkoutService;
import mobileapplications.workoutbuilder.domain.Exercise;
import mobileapplications.workoutbuilder.domain.User;
import mobileapplications.workoutbuilder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;
    private final UserRepository userRepository;

    @Autowired
    public WorkoutController(WorkoutService workoutService, UserRepository userRepository) {
        this.workoutService = workoutService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<Workout> createWorkout(@RequestBody Workout workout, @RequestParam Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            workout.setUser(user.get());
            Workout savedWorkout = workoutService.saveWorkout(workout);
            return new ResponseEntity<>(savedWorkout, HttpStatus.CREATED);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping
    public ResponseEntity<List<Workout>> getAllWorkouts() {
        List<Workout> workouts = workoutService.getAllWorkouts();
        return new ResponseEntity<>(workouts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Workout> getWorkoutById(@PathVariable Long id) {
        Optional<Workout> workout = workoutService.getWorkoutById(id);
        return workout.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Long id) {
        workoutService.deleteWorkout(id);
        return ResponseEntity.noContent().build();
    }

}
