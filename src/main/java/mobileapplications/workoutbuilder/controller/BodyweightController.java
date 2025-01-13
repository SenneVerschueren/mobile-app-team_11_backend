package mobileapplications.workoutbuilder.controller;

import mobileapplications.workoutbuilder.domain.Bodyweight;
import mobileapplications.workoutbuilder.service.BodyweightService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/bodyweight")
public class BodyweightController {

    private final BodyweightService bodyweightService;


    public BodyweightController(BodyweightService bodyweightService) {
        this.bodyweightService = bodyweightService;
    }

    @GetMapping
    public List<Bodyweight> getAllProgress() {
        return bodyweightService.getAllBodyweight();
    }

    @GetMapping("/{id}")
    public List<Bodyweight> getBodyweightByUserId(@PathVariable Long id) {
        return bodyweightService.getBodyweightByUserId(id);
    }
}
