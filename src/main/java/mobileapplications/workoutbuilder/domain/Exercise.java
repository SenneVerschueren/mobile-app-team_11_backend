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
    private int rest = 60;

    @Column(nullable = false)
    private Boolean autoIncrease = true;

    @Column(nullable = false)
    private double autoIncreaseFactor = 1.25;

    @Column(nullable = false)
    private double autoIncreaseWeightStep = 2.5;

    @Column(nullable = false)
    private double autoIncreaseStartWeight = 20;

    @Column(nullable = false)
    private int autoIncreaseMinSets = 3;

    @Column(nullable = false)
    private int autoIncreaseMaxSets = 5;

    @Column(nullable = false)
    private int autoIncreaseMinReps = 8;

    @Column(nullable = false)
    private int autoIncreaseMaxReps = 12;

    @Column(nullable = false)
    private int autoIncreaseStartDuration = 30;

    @Column(nullable = false)
    private int autoIncreaseDurationSets = 3;

    @Column(nullable = false)
    private int autoIncreaseCurrentSets = 3;

    @Column(nullable = false)
    private int autoIncreaseCurrentReps = 8;

    @Column(nullable = false)
    private int autoIncreaseCurrentDuration = 30;

    @Column(nullable = false)
    private double autoIncreaseCurrentWeight = 20;

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

    public Exercise(String name, WorkoutType type, String goal) {
        this.name = name;
        this.type = type;
        if (type != WorkoutType.DURATION){
            switch (goal) {
                case "POWER":
                    this.rest = 240;
                    this.autoIncreaseFactor = 1.05;
                    this.autoIncreaseWeightStep = 2.5;
                    this.autoIncreaseStartWeight = 40;
                    this.autoIncreaseMinSets = 3;
                    this.autoIncreaseMaxSets = 4;
                    this.autoIncreaseMinReps = 2;
                    this.autoIncreaseMaxReps = 8;
                    this.autoIncreaseCurrentSets = 3;
                    this.autoIncreaseCurrentReps = 2;
                    this.autoIncreaseCurrentWeight = 40;
                    break;
                case "MUSCLE":
                    this.rest = 180;
                    this.autoIncreaseFactor = 1.1;
                    this.autoIncreaseWeightStep = 2.5;
                    this.autoIncreaseStartWeight = 20;
                    this.autoIncreaseMinSets = 3;
                    this.autoIncreaseMaxSets = 4;
                    this.autoIncreaseMinReps = 8;
                    this.autoIncreaseMaxReps = 15;
                    this.autoIncreaseCurrentSets = 3;
                    this.autoIncreaseCurrentReps = 8;
                    this.autoIncreaseCurrentWeight = 20;
                    break;
                case "ENDURANCE":
                    this.rest = 90;
                    this.autoIncreaseFactor = 1.15;
                    this.autoIncreaseWeightStep = 2.5;
                    this.autoIncreaseStartWeight = 10;
                    this.autoIncreaseMinSets = 3;
                    this.autoIncreaseMaxSets = 4;
                    this.autoIncreaseMinReps = 12;
                    this.autoIncreaseMaxReps = 20;
                    this.autoIncreaseCurrentSets = 3;
                    this.autoIncreaseCurrentReps = 12;
                    this.autoIncreaseCurrentWeight = 10;
                    break;
                default:
                    break;
            } 
        }
        
    }

    public Exercise(String name, WorkoutType type, int rest, Boolean autoIncrease, int autoIncreaseFactor,
            double autoIncreaseWeightStep, double autoIncreaseStartWeight, int autoIncreaseMinSets,
            int autoIncreaseMaxSets, int autoIncreaseMinReps, int autoIncreaseMaxReps,
            int autoIncreaseStartDuration, int autoIncreaseDurationSets, int autoIncreaseCurrentSets,
            int autoIncreaseCurrentReps, int autoIncreaseCurrentDuration) {
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

    // public int getRest() {
    // return rest;
    // }

    // public void setRest(int rest) {
    // this.rest = rest;
    // }

    // public Boolean getAutoIncrease() {
    // return autoIncrease;
    // }

    // public void setAutoIncrease(Boolean autoIncrease) {
    // this.autoIncrease = autoIncrease;
    // }

    // public int getAutoIncreaseFactor() {
    // return autoIncreaseFactor;
    // }

    // public void setAutoIncreaseFactor(int autoIncreaseFactor) {
    // this.autoIncreaseFactor = autoIncreaseFactor;
    // }

    // public int getAutoIncreaseWeightStep() {
    // return autoIncreaseWeightStep;
    // }

    // public void setAutoIncreaseWeightStep(int autoIncreaseWeightStep) {
    // this.autoIncreaseWeightStep = autoIncreaseWeightStep;
    // }

    // public int getAutoIncreaseStartWeight() {
    // return autoIncreaseStartWeight;
    // }

    // public void setAutoIncreaseStartWeight(int autoIncreaseStartWeight) {
    // this.autoIncreaseStartWeight = autoIncreaseStartWeight;
    // }

    // public int getAutoIncreaseMinSets() {
    // return autoIncreaseMinSets;
    // }

    // public void setAutoIncreaseMinSets(int autoIncreaseMinSets) {
    // this.autoIncreaseMinSets = autoIncreaseMinSets;
    // }

    // public int getAutoIncreaseMaxSets() {
    // return autoIncreaseMaxSets;
    // }

    // public void setAutoIncreaseMaxSets(int autoIncreaseMaxSets) {
    // this.autoIncreaseMaxSets = autoIncreaseMaxSets;
    // }

    // public int getAutoIncreaseMin_reps() {
    // return autoIncreaseMin_reps;
    // }

    // public void setAutoIncreaseMin_reps(int autoIncreaseMin_reps) {
    // this.autoIncreaseMin_reps = autoIncreaseMin_reps;
    // }

    // public int getAutoIncreaseMax_reps() {
    // return autoIncreaseMax_reps;
    // }

    // public void setAutoIncreaseMax_reps(int autoIncreaseMax_reps) {
    // this.autoIncreaseMax_reps = autoIncreaseMax_reps;
    // }

    // public int getAutoIncreaseStartDuration() {
    // return autoIncreaseStartDuration;
    // }

    // public void setAutoIncreaseStartDuration(int autoIncreaseStartDuration) {
    // this.autoIncreaseStartDuration = autoIncreaseStartDuration;
    // }

    // public int getAutoIncreaseDurationSets() {
    // return autoIncreaseDurationSets;
    // }

    // public void setAutoIncreaseDurationSets(int autoIncreaseDurationSets) {
    // this.autoIncreaseDurationSets = autoIncreaseDurationSets;
    // }

    // public int getAutoIncreaseCurrentSets() {
    // return autoIncreaseCurrentSets;
    // }

    // public void setAutoIncreaseCurrentSets(int autoIncreaseCurrentSets) {
    // this.autoIncreaseCurrentSets = autoIncreaseCurrentSets;
    // }

    // public int getAutoIncreaseCurrent_reps() {
    // return autoIncreaseCurrent_reps;
    // }

    // public void setAutoIncreaseCurrent_reps(int autoIncreaseCurrent_reps) {
    // this.autoIncreaseCurrent_reps = autoIncreaseCurrent_reps;
    // }

    // public int getAutoIncreaseCurrentDuration() {
    // return autoIncreaseCurrentDuration;
    // }

    // public void setAutoIncreaseCurrentDuration(int
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

    public void setSets(List<Set> sets) {
        this.sets = sets != null ? sets : new ArrayList<>();
    }

    public Set addSet(Set set) {
        sets.add(set);
        set.setExercise(this);
        return set;
    }

    // public void updateValuesExercise(String name, WorkoutType type, int rest,
    // Boolean autoIncrease,
    // int autoIncreaseFactor,
    // int autoIncreaseWeightStep, int autoIncreaseStartWeight, int
    // autoIncreaseMinSets,
    // int autoIncreaseMaxSets, int autoIncreaseMin_reps, int
    // autoIncreaseMax_reps,
    // int autoIncreaseStartDuration, int autoIncreaseDurationSets, int
    // autoIncreaseCurrentSets,
    // int autoIncreaseCurrent_reps, int autoIncreaseCurrentDuration,
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
