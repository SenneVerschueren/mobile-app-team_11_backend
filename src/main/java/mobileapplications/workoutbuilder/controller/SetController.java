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
}