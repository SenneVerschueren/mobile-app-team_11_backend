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

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Long id) {
        Exercise exercise = exerciseService.getExerciseById(id);
        return ResponseEntity.ok(exercise);
    }

    @PutMapping("/{id}")
    public Exercise updateExercise(@PathVariable Long id, @RequestBody Exercise exercise) {
        return exerciseService.updateExercise(id, exercise);
    }

    /*
    @DeleteMapping("/{id}")
    public String deleteExcercise(@PathVariable Long id) {
        return exerciseService.deleteExercise(id);
    }
    */

    @DeleteMapping("/workout/{workoutId}/exercise/{exerciseId}")
    public ResponseEntity<String> deleteExerciseFromWorkout(@PathVariable Long workoutId, @PathVariable Long exerciseId) {
        String response = exerciseService.deleteExerciseFromWorkout(workoutId, exerciseId);
        return ResponseEntity.ok(response);
    }
}
