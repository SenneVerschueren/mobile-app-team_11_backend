package mobileapplications.workoutbuilder.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import mobileapplications.workoutbuilder.enums.WorkoutType;

@Entity
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workout_id", nullable = false) // Foreign key
    @JsonBackReference
    private Workout workout;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private WorkoutType type;

    @Column(nullable = false)
    private Number rest;

    @Column(nullable = false)
    private Boolean autoIncrease;

    @Column(nullable = false)
    private Number autoIncreaseFactor;

    @Column(nullable = false)
    private Number autoIncreaseWeightStep;

    @Column(nullable = false)
    private Number autoIncreaseStartWeight;

    @Column(nullable = false)
    private Number autoIncreaseMinSets;

    @Column(nullable = false)
    private Number autoIncreaseMaxSets;

    @Column(nullable = false)
    private Number autoIncreaseMin_reps;

    @Column(nullable = false)
    private Number autoIncreaseMax_reps;

    @Column(nullable = false)
    private Number autoIncreaseStartDuration;

    @Column(nullable = false)
    private Number autoIncreaseDurationSets;

    @Column(nullable = false)
    private Number autoIncreaseCurrentSets;

    @Column(nullable = false)
    private Number autoIncreaseCurrent_reps;

    @Column(nullable = false)
    private Number autoIncreaseCurrentDuration;

    @OneToMany(mappedBy = "exercise")
    private List<Set> sets;

    // Constructors
    public Exercise() {
    }

    public Exercise(String name, WorkoutType type, Number rest, Boolean autoIncrease, Number autoIncreaseFactor,
            Number autoIncreaseWeightStep, Number autoIncreaseStartWeight, Number autoIncreaseMinSets,
            Number autoIncreaseMaxSets, Number autoIncreaseMin_reps, Number autoIncreaseMax_reps,
            Number autoIncreaseStartDuration, Number autoIncreaseDurationSets, Number autoIncreaseCurrentSets,
            Number autoIncreaseCurrent_reps, Number autoIncreaseCurrentDuration) {
        this.name = name;
        this.type = type;
        this.rest = rest;
        this.autoIncrease = autoIncrease;
        this.autoIncreaseFactor = autoIncreaseFactor;
        this.autoIncreaseWeightStep = autoIncreaseWeightStep;
        this.autoIncreaseStartWeight = autoIncreaseStartWeight;
        this.autoIncreaseMinSets = autoIncreaseMinSets;
        this.autoIncreaseMaxSets = autoIncreaseMaxSets;
        this.autoIncreaseMin_reps = autoIncreaseMin_reps;
        this.autoIncreaseMax_reps = autoIncreaseMax_reps;
        this.autoIncreaseStartDuration = autoIncreaseStartDuration;
        this.autoIncreaseDurationSets = autoIncreaseDurationSets;
        this.autoIncreaseCurrentSets = autoIncreaseCurrentSets;
        this.autoIncreaseCurrent_reps = autoIncreaseCurrent_reps;
        this.autoIncreaseCurrentDuration = autoIncreaseCurrentDuration;
        this.sets = new ArrayList<>();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WorkoutType getType() {
        return type;
    }

    public void setType(WorkoutType type) {
        this.type = type;
    }

    public Number getRest() {
        return rest;
    }

    public void setRest(Number rest) {
        this.rest = rest;
    }

    public Boolean getAutoIncrease() {
        return autoIncrease;
    }

    public void setAutoIncrease(Boolean autoIncrease) {
        this.autoIncrease = autoIncrease;
    }

    public Number getAutoIncreaseFactor() {
        return autoIncreaseFactor;
    }

    public void setAutoIncreaseFactor(Number autoIncreaseFactor) {
        this.autoIncreaseFactor = autoIncreaseFactor;
    }

    public Number getAutoIncreaseWeightStep() {
        return autoIncreaseWeightStep;
    }

    public void setAutoIncreaseWeightStep(Number autoIncreaseWeightStep) {
        this.autoIncreaseWeightStep = autoIncreaseWeightStep;
    }

    public Number getAutoIncreaseStartWeight() {
        return autoIncreaseStartWeight;
    }

    public void setAutoIncreaseStartWeight(Number autoIncreaseStartWeight) {
        this.autoIncreaseStartWeight = autoIncreaseStartWeight;
    }

    public Number getAutoIncreaseMinSets() {
        return autoIncreaseMinSets;
    }

    public void setAutoIncreaseMinSets(Number autoIncreaseMinSets) {
        this.autoIncreaseMinSets = autoIncreaseMinSets;
    }

    public Number getAutoIncreaseMaxSets() {
        return autoIncreaseMaxSets;
    }

    public void setAutoIncreaseMaxSets(Number autoIncreaseMaxSets) {
        this.autoIncreaseMaxSets = autoIncreaseMaxSets;
    }

    public Number getAutoIncreaseMin_reps() {
        return autoIncreaseMin_reps;
    }

    public void setAutoIncreaseMin_reps(Number autoIncreaseMin_reps) {
        this.autoIncreaseMin_reps = autoIncreaseMin_reps;
    }

    public Number getAutoIncreaseMax_reps() {
        return autoIncreaseMax_reps;
    }

    public void setAutoIncreaseMax_reps(Number autoIncreaseMax_reps) {
        this.autoIncreaseMax_reps = autoIncreaseMax_reps;
    }

    public Number getAutoIncreaseStartDuration() {
        return autoIncreaseStartDuration;
    }

    public void setAutoIncreaseStartDuration(Number autoIncreaseStartDuration) {
        this.autoIncreaseStartDuration = autoIncreaseStartDuration;
    }

    public Number getAutoIncreaseDurationSets() {
        return autoIncreaseDurationSets;
    }

    public void setAutoIncreaseDurationSets(Number autoIncreaseDurationSets) {
        this.autoIncreaseDurationSets = autoIncreaseDurationSets;
    }

    public Number getAutoIncreaseCurrentSets() {
        return autoIncreaseCurrentSets;
    }

    public void setAutoIncreaseCurrentSets(Number autoIncreaseCurrentSets) {
        this.autoIncreaseCurrentSets = autoIncreaseCurrentSets;
    }

    public Number getAutoIncreaseCurrent_reps() {
        return autoIncreaseCurrent_reps;
    }

    public void setAutoIncreaseCurrent_reps(Number autoIncreaseCurrent_reps) {
        this.autoIncreaseCurrent_reps = autoIncreaseCurrent_reps;
    }

    public Number getAutoIncreaseCurrentDuration() {
        return autoIncreaseCurrentDuration;
    }

    public void setAutoIncreaseCurrentDuration(Number autoIncreaseCurrentDuration) {
        this.autoIncreaseCurrentDuration = autoIncreaseCurrentDuration;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public List<Set> getSets() {
        return sets;
    }

    public void setSets(List<Set> sets) {
        this.sets = sets;
    }

    public void addSet(Set set) {
        sets.add(set);
        set.setExercise(this);
    }

    public void updateValuesExercise(String name, WorkoutType type, Number rest, Boolean autoIncrease,
            Number autoIncreaseFactor,
            Number autoIncreaseWeightStep, Number autoIncreaseStartWeight, Number autoIncreaseMinSets,
            Number autoIncreaseMaxSets, Number autoIncreaseMin_reps, Number autoIncreaseMax_reps,
            Number autoIncreaseStartDuration, Number autoIncreaseDurationSets, Number autoIncreaseCurrentSets,
            Number autoIncreaseCurrent_reps, Number autoIncreaseCurrentDuration) {
        this.name = name;
        this.type = type;
        this.rest = rest;
        this.autoIncrease = autoIncrease;
        this.autoIncreaseFactor = autoIncreaseFactor;
        this.autoIncreaseWeightStep = autoIncreaseWeightStep;
        this.autoIncreaseStartWeight = autoIncreaseStartWeight;
        this.autoIncreaseMinSets = autoIncreaseMinSets;
        this.autoIncreaseMaxSets = autoIncreaseMaxSets;
        this.autoIncreaseMin_reps = autoIncreaseMin_reps;
        this.autoIncreaseMax_reps = autoIncreaseMax_reps;
        this.autoIncreaseStartDuration = autoIncreaseStartDuration;
        this.autoIncreaseDurationSets = autoIncreaseDurationSets;
        this.autoIncreaseCurrentSets = autoIncreaseCurrentSets;
        this.autoIncreaseCurrent_reps = autoIncreaseCurrent_reps;
        this.autoIncreaseCurrentDuration = autoIncreaseCurrentDuration;
    }

}
