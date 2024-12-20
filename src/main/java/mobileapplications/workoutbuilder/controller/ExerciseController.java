package mobileapplications.workoutbuilder.controller;

import mobileapplications.workoutbuilder.domain.Exercise;
import mobileapplications.workoutbuilder.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        // if only name is provided, create an exercise with the name
        if (exercise.getName() != null && exercise.getType() == null) {
            Exercise createdExercise = exerciseService.createExerciseByName(exercise.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdExercise);
        } 
        Exercise createdExercise = exerciseService.createExercise(exercise, workoutId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExercise);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Long id) {
        Exercise exercise = exerciseService.getExerciseById(id);
        return ResponseEntity.ok(exercise);
    }

    @PutMapping("/{id}")
    public Exercise updateExercise(@PathVariable Long id, @RequestBody Exercise exercise) {
        return exerciseService.updateExercise(id, exercise);
    }

    @DeleteMapping("/{id}")
    public String deleteExcercise(@PathVariable Long id) {
        return exerciseService.deleteExercise(id);
    }
}
