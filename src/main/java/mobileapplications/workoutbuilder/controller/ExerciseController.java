package mobileapplications.workoutbuilder.controller;

import mobileapplications.workoutbuilder.domain.Exercise;
import mobileapplications.workoutbuilder.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {
    
    @Autowired
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public List<Exercise> getAllExercises() {
        return exerciseService.getAllExercises();
    }

    @PostMapping
    public ResponseEntity<Exercise> createExercise(@RequestBody Exercise exercise, @RequestParam Long workoutId) {
        Exercise createdExercise = exerciseService.createExercise(exercise, workoutId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExercise);
    }
}
