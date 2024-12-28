package mobileapplications.workoutbuilder.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import mobileapplications.workoutbuilder.enums.WorkoutType;

@Getter
@Setter
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
    private WorkoutType type = WorkoutType.WEIGHTS;

    @Column(nullable = false)
    private Number rest = 60;

    @Column(nullable = false)
    private Boolean autoIncrease = false;

    @Column(nullable = false)
    private Number autoIncreaseFactor = 1.25;

    @Column(nullable = false)
    private Number autoIncreaseWeightStep = 2.5;

    @Column(nullable = false)
    private Number autoIncreaseStartWeight = 20;

    @Column(nullable = false)
    private Number autoIncreaseMinSets = 3;

    @Column(nullable = false)
    private Number autoIncreaseMaxSets = 5;

    @Column(nullable = false)
    private Number autoIncreaseMinReps = 8;

    @Column(nullable = false)
    private Number autoIncreaseMaxReps = 12;

    @Column(nullable = false)
    private Number autoIncreaseStartDuration = 30;

    @Column(nullable = false)
    private Number autoIncreaseDurationSets = 3;

    @Column(nullable = false)
    private Number autoIncreaseCurrentSets = 3;

    @Column(nullable = false)
    private Number autoIncreaseCurrentReps = 8;

    @Column(nullable = false)
    private Number autoIncreaseCurrentDuration = 30;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Set> sets = new ArrayList<>();

    @Column(name = "order_index", nullable = false)
    private int orderIndex;

    public Exercise() {
    }

    public Exercise(String name) {
        this.name = name;
    }

    public Exercise(String name, WorkoutType type, Number rest, Boolean autoIncrease, Number autoIncreaseFactor,
            Number autoIncreaseWeightStep, Number autoIncreaseStartWeight, Number autoIncreaseMinSets,
            Number autoIncreaseMaxSets, Number autoIncreaseMinReps, Number autoIncreaseMaxReps,
            Number autoIncreaseStartDuration, Number autoIncreaseDurationSets, Number autoIncreaseCurrentSets,
            Number autoIncreaseCurrentReps, Number autoIncreaseCurrentDuration) {
        this.name = name;
        this.type = type;
        this.rest = rest;
        this.autoIncrease = autoIncrease;
        this.autoIncreaseFactor = autoIncreaseFactor;
        this.autoIncreaseWeightStep = autoIncreaseWeightStep;
        this.autoIncreaseStartWeight = autoIncreaseStartWeight;
        this.autoIncreaseMinSets = autoIncreaseMinSets;
        this.autoIncreaseMaxSets = autoIncreaseMaxSets;
        this.autoIncreaseMinReps = autoIncreaseMinReps;
        this.autoIncreaseMaxReps = autoIncreaseMaxReps;
        this.autoIncreaseStartDuration = autoIncreaseStartDuration;
        this.autoIncreaseDurationSets = autoIncreaseDurationSets;
        this.autoIncreaseCurrentSets = autoIncreaseCurrentSets;
        this.autoIncreaseCurrentReps = autoIncreaseCurrentReps;
        this.autoIncreaseCurrentDuration = autoIncreaseCurrentDuration;
        this.sets = new ArrayList<>();
        this.orderIndex = 0;
    }

    // // Getters and Setters
    // public Long getId() {
    // return id;
    // }

    // public void setId(Long id) {
    // this.id = id;
    // }

    // public String getName() {
    // return name;
    // }

    // public void setName(String name) {
    // this.name = name;
    // }

    // public WorkoutType getType() {
    // return type;
    // }

    // public void setType(WorkoutType type) {
    // this.type = type;
    // }

    // public Number getRest() {
    // return rest;
    // }

    // public void setRest(Number rest) {
    // this.rest = rest;
    // }

    // public Boolean getAutoIncrease() {
    // return autoIncrease;
    // }

    // public void setAutoIncrease(Boolean autoIncrease) {
    // this.autoIncrease = autoIncrease;
    // }

    // public Number getAutoIncreaseFactor() {
    // return autoIncreaseFactor;
    // }

    // public void setAutoIncreaseFactor(Number autoIncreaseFactor) {
    // this.autoIncreaseFactor = autoIncreaseFactor;
    // }

    // public Number getAutoIncreaseWeightStep() {
    // return autoIncreaseWeightStep;
    // }

    // public void setAutoIncreaseWeightStep(Number autoIncreaseWeightStep) {
    // this.autoIncreaseWeightStep = autoIncreaseWeightStep;
    // }

    // public Number getAutoIncreaseStartWeight() {
    // return autoIncreaseStartWeight;
    // }

    // public void setAutoIncreaseStartWeight(Number autoIncreaseStartWeight) {
    // this.autoIncreaseStartWeight = autoIncreaseStartWeight;
    // }

    // public Number getAutoIncreaseMinSets() {
    // return autoIncreaseMinSets;
    // }

    // public void setAutoIncreaseMinSets(Number autoIncreaseMinSets) {
    // this.autoIncreaseMinSets = autoIncreaseMinSets;
    // }

    // public Number getAutoIncreaseMaxSets() {
    // return autoIncreaseMaxSets;
    // }

    // public void setAutoIncreaseMaxSets(Number autoIncreaseMaxSets) {
    // this.autoIncreaseMaxSets = autoIncreaseMaxSets;
    // }

    // public Number getAutoIncreaseMin_reps() {
    // return autoIncreaseMin_reps;
    // }

    // public void setAutoIncreaseMin_reps(Number autoIncreaseMin_reps) {
    // this.autoIncreaseMin_reps = autoIncreaseMin_reps;
    // }

    // public Number getAutoIncreaseMax_reps() {
    // return autoIncreaseMax_reps;
    // }

    // public void setAutoIncreaseMax_reps(Number autoIncreaseMax_reps) {
    // this.autoIncreaseMax_reps = autoIncreaseMax_reps;
    // }

    // public Number getAutoIncreaseStartDuration() {
    // return autoIncreaseStartDuration;
    // }

    // public void setAutoIncreaseStartDuration(Number autoIncreaseStartDuration) {
    // this.autoIncreaseStartDuration = autoIncreaseStartDuration;
    // }

    // public Number getAutoIncreaseDurationSets() {
    // return autoIncreaseDurationSets;
    // }

    // public void setAutoIncreaseDurationSets(Number autoIncreaseDurationSets) {
    // this.autoIncreaseDurationSets = autoIncreaseDurationSets;
    // }

    // public Number getAutoIncreaseCurrentSets() {
    // return autoIncreaseCurrentSets;
    // }

    // public void setAutoIncreaseCurrentSets(Number autoIncreaseCurrentSets) {
    // this.autoIncreaseCurrentSets = autoIncreaseCurrentSets;
    // }

    // public Number getAutoIncreaseCurrent_reps() {
    // return autoIncreaseCurrent_reps;
    // }

    // public void setAutoIncreaseCurrent_reps(Number autoIncreaseCurrent_reps) {
    // this.autoIncreaseCurrent_reps = autoIncreaseCurrent_reps;
    // }

    // public Number getAutoIncreaseCurrentDuration() {
    // return autoIncreaseCurrentDuration;
    // }

    // public void setAutoIncreaseCurrentDuration(Number
    // autoIncreaseCurrentDuration) {
    // this.autoIncreaseCurrentDuration = autoIncreaseCurrentDuration;
    // }

    // public Workout getWorkout() {
    // return workout;
    // }

    // public void setWorkout(Workout workout) {
    // this.workout = workout;
    // }

    // public List<Set> getSets() {
    // return sets;
    // }

    // public void setSets(List<Set> sets) {
    // this.sets = sets;
    // }

    public void addSet(Set set) {
        sets.add(set);
        set.setExercise(this);
    }

    // public void updateValuesExercise(String name, WorkoutType type, Number rest,
    // Boolean autoIncrease,
    // Number autoIncreaseFactor,
    // Number autoIncreaseWeightStep, Number autoIncreaseStartWeight, Number
    // autoIncreaseMinSets,
    // Number autoIncreaseMaxSets, Number autoIncreaseMin_reps, Number
    // autoIncreaseMax_reps,
    // Number autoIncreaseStartDuration, Number autoIncreaseDurationSets, Number
    // autoIncreaseCurrentSets,
    // Number autoIncreaseCurrent_reps, Number autoIncreaseCurrentDuration,
    // List<Set> sets) {
    // this.name = name;
    // this.type = type;
    // this.rest = rest;
    // this.autoIncrease = autoIncrease;
    // this.autoIncreaseFactor = autoIncreaseFactor;
    // this.autoIncreaseWeightStep = autoIncreaseWeightStep;
    // this.autoIncreaseStartWeight = autoIncreaseStartWeight;
    // this.autoIncreaseMinSets = autoIncreaseMinSets;
    // this.autoIncreaseMaxSets = autoIncreaseMaxSets;
    // this.autoIncreaseMin_reps = autoIncreaseMin_reps;
    // this.autoIncreaseMax_reps = autoIncreaseMax_reps;
    // this.autoIncreaseStartDuration = autoIncreaseStartDuration;
    // this.autoIncreaseDurationSets = autoIncreaseDurationSets;
    // this.autoIncreaseCurrentSets = autoIncreaseCurrentSets;
    // this.autoIncreaseCurrent_reps = autoIncreaseCurrent_reps;
    // this.autoIncreaseCurrentDuration = autoIncreaseCurrentDuration;
    // this.sets = sets;
    // }

}
