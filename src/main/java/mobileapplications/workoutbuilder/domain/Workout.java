package mobileapplications.workoutbuilder.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "workouts")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int rest;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Exercise> exercises = new ArrayList<>();

    public Workout() {
        this.exercises = new ArrayList<>();
    }

    public Workout(String name) {
        this.name = name;
        this.rest = 60;
        this.exercises = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRest() {
        return rest;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }

    public List<Exercise> getExercises() {
        exercises.sort((e1, e2) -> e1.getOrderIndex() - e2.getOrderIndex());
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises != null ? exercises : new ArrayList<>();
    }

    public void addExercise(Exercise exercise) {
        exercise.setOrderIndex(exercises.size());
        exercises.add(exercise);
        exercise.setWorkout(this);
    }

    public void updateExercisesOrder(List<Exercise> orderedExercises) {
        for (int i = 0; i < orderedExercises.size(); i++) {
            orderedExercises.get(i).setOrderIndex(i);
        }
        this.exercises = orderedExercises;
    }

    public void updateValuesWorkout(String name, int rest, List<Exercise> exercises) {
        this.name = name;
        this.rest = rest;
        this.exercises = exercises != null ? exercises : new ArrayList<>();
    }
}