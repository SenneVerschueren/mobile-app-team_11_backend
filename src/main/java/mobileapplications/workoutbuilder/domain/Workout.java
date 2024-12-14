package mobileapplications.workoutbuilder.domain;

import jakarta.persistence.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "workouts")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Foreign key
    @JsonBackReference
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int rest;

    // One workout can have multiple exercises
    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Exercise> exercises;

    public Workout() {
    }

    public Workout(String name) {
        this.name = name;
        this.rest = 60; // Default rest time
    }

    // Getters and setters
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
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
        exercise.setWorkout(this);
    }

    public void updateValuesWorkout(String name, int rest, List<Exercise> exercises) {
        this.name = name;
        this.rest = rest;
        this.exercises = exercises;

    }
}
