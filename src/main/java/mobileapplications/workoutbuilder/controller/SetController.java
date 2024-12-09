package mobileapplications.workoutbuilder.controller;

import mobileapplications.workoutbuilder.domain.Set;
import mobileapplications.workoutbuilder.service.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sets")
public class SetController {

    @Autowired
    private SetService setService;

    @GetMapping
    public List<Set> getAllSets() {
        return setService.getAllSets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Set> getSetById(@PathVariable Long id) {
        Set set = setService.getSetById(id);
        if (set == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(set);
    }

    @PostMapping
    public ResponseEntity<Set> createSet(@RequestBody Set set, @RequestParam Long exerciseId) {
        Set createdSet = setService.createSet(set, exerciseId);
        return ResponseEntity.ok(createdSet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Set> updateSet(@RequestBody Set set, @PathVariable Long id) {
        Set existingSet = setService.getSetById(id);
        if (existingSet == null) {
            return ResponseEntity.notFound().build();
        }
        set.setId(id);
        Set updatedSet = setService.createSet(set, existingSet.getExercise().getId());
        return ResponseEntity.ok(updatedSet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSet(@PathVariable Long id) {
        if (setService.getSetById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        setService.deleteSet(id);
        return ResponseEntity.noContent().build();
    }
}