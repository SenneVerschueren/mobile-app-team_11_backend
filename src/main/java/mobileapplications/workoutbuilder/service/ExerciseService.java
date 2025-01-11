package mobileapplications.workoutbuilder.service;

import mobileapplications.workoutbuilder.domain.Exercise;
import mobileapplications.workoutbuilder.domain.Set;
import mobileapplications.workoutbuilder.domain.Workout;
import mobileapplications.workoutbuilder.enums.WorkoutType;
import mobileapplications.workoutbuilder.exception.ExerciseServiceException;
import mobileapplications.workoutbuilder.repository.ExerciseRepository;
import mobileapplications.workoutbuilder.repository.SetRepository;

import mobileapplications.workoutbuilder.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

    @Autowired
    private final ExerciseRepository exerciseRepository;

    @Autowired
    private final WorkoutService workoutService;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private SetRepository setRepositroy;

    public ExerciseService(ExerciseRepository exerciseRepository, WorkoutService workoutService,
            WorkoutRepository workoutRepository) {
        this.exerciseRepository = exerciseRepository;
        this.workoutService = workoutService;
        this.workoutRepository = workoutRepository;
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public Exercise getExerciseById(Long id) {
        Exercise exercise = exerciseRepository.findById(id).orElse(null);
        if (exercise != null) {
            return exercise;
        } else {
            throw new ExerciseServiceException("Exercise not found with id: " + id);
        }
    }

    public List<Exercise> getExercisesByWorkoutId(Long workoutId) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found"));
        return workout.getExercises();
    }

    public Exercise createExercise(Exercise exercise, Long workoutId) {
        Workout workout = workoutService.getWorkoutById(workoutId).orElse(null);
        if (workout != null) {
            exercise.setWorkout(workout);
            return exerciseRepository.save(exercise);
        } else {
            // handle the case where the workout is not found
            throw new ExerciseServiceException("Workout not found with id: " + workoutId);
        }

    }

    public Exercise createExerciseByName(String exerciseName) {
        Exercise exercise = new Exercise(exerciseName);
        exercise.setName(exerciseName);
        return exerciseRepository.save(exercise);
    }

    /*
     * public String deleteExercise(Long id) {
     * String exerciseName = getExerciseById(id).getName();
     * exerciseRepository.deleteById(id);
     * return exerciseName + " is successfully deleted!";
     * }
     */

    public String deleteExerciseFromWorkout(Long workoutId, Long exerciseId) {
        Workout workout = workoutService.getWorkoutById(workoutId)
                .orElseThrow(() -> new ExerciseServiceException("Workout not found with id: " + workoutId));

        Exercise exercise = getExerciseById(exerciseId);
        if (!exercise.getWorkout().getId().equals(workoutId)) {
            throw new ExerciseServiceException("Exercise does not belong to the specified workout");
        }

        workout.getExercises().remove(exercise);
        exerciseRepository.deleteById(exerciseId);
        return "Exercise successfully deleted from workout";
    }

    public Exercise updateExercise(Long id, Exercise newValuesExercise) {
        Exercise exercise = getExerciseById(id);

        // Update exercise values
        exercise.setName(newValuesExercise.getName());
        exercise.setType(newValuesExercise.getType());
        exercise.setRest(newValuesExercise.getRest());
        exercise.setAutoIncrease(newValuesExercise.getAutoIncrease());
        exercise.setAutoIncreaseFactor(newValuesExercise.getAutoIncreaseFactor());
        exercise.setAutoIncreaseWeightStep(newValuesExercise.getAutoIncreaseWeightStep());
        exercise.setAutoIncreaseStartWeight(newValuesExercise.getAutoIncreaseStartWeight());
        exercise.setAutoIncreaseMinSets(newValuesExercise.getAutoIncreaseMinSets());
        exercise.setAutoIncreaseMaxSets(newValuesExercise.getAutoIncreaseMaxSets());
        exercise.setAutoIncreaseMinReps(newValuesExercise.getAutoIncreaseMinReps());
        exercise.setAutoIncreaseMaxReps(newValuesExercise.getAutoIncreaseMaxReps());
        exercise.setAutoIncreaseStartDuration(newValuesExercise.getAutoIncreaseStartDuration());
        exercise.setAutoIncreaseDurationSets(newValuesExercise.getAutoIncreaseDurationSets());
        exercise.setAutoIncreaseCurrentSets(newValuesExercise.getAutoIncreaseCurrentSets());
        exercise.setAutoIncreaseCurrentReps(newValuesExercise.getAutoIncreaseCurrentReps());
        exercise.setAutoIncreaseCurrentWeight(newValuesExercise.getAutoIncreaseCurrentWeight());
        exercise.setAutoIncreaseCurrentDuration(newValuesExercise.getAutoIncreaseCurrentDuration());

        // Update sets
        List<Set> existingSets = exercise.getSets();
        List<Set> newSets = newValuesExercise.getSets();

        for (Set newSet : newSets) {
            boolean updated = false;
            for (Set existingSet : existingSets) {
                if (existingSet.getId().equals(newSet.getId())) {
                    existingSet.updateValuesSet(newSet.getReps(), newSet.getWeight(), newSet.getDuration(), exercise);
                    updated = true;
                    break;
                }
            }
            if (!updated) {
                newSet.setExercise(exercise);
                existingSets.add(newSet);
            }
        }

        // Remove sets that are not in the new list
        existingSets.removeIf(
                existingSet -> newSets.stream().noneMatch(newSet -> newSet.getId().equals(existingSet.getId())));

        return exerciseRepository.save(exercise);
    }

    public Exercise autoIncrease(Long id) {
        Exercise exercise = getExerciseById(id);
        if (exercise.getAutoIncrease()) {
            int currentSets = exercise.getAutoIncreaseCurrentSets();
            int currentReps = exercise.getAutoIncreaseCurrentReps();
            int currentDuration = exercise.getAutoIncreaseCurrentDuration();
            int minSets = exercise.getAutoIncreaseMinSets();
            int maxSets = exercise.getAutoIncreaseMaxSets();
            int minReps = exercise.getAutoIncreaseMinReps();
            int maxReps = exercise.getAutoIncreaseMaxReps();
            double currentWeight = exercise.getAutoIncreaseCurrentWeight();
            double weightStep = exercise.getAutoIncreaseWeightStep();
            double factor = exercise.getAutoIncreaseFactor();

            if (exercise.getType().equals(WorkoutType.DURATION)) {
                currentSets = addRepsAndSets(currentSets, factor);
                if (currentSets >= maxSets) {
                    currentSets = minSets;
                    currentDuration = addDuration(currentDuration, factor);
                }

            } else {
                currentReps = addRepsAndSets(currentReps, factor);

                if (currentReps >= maxReps) {
                    currentReps = minReps;
                    currentSets = addRepsAndSets(currentSets, factor);

                    if (exercise.getType().equals(WorkoutType.WEIGHTS)) {
                        if (currentSets >= maxSets) {
                            currentSets = minSets;
                            currentWeight = addWeight(currentWeight, factor, weightStep);
                        }
                    }
                    if (exercise.getType().equals(WorkoutType.BODYWEIGHT)) {
                        if (currentSets >= maxSets) {
                            currentSets = maxSets;
                        }
                    }
                }
                exercise.setAutoIncreaseCurrentSets(currentSets);
                exercise.setAutoIncreaseCurrentReps(currentReps);
                exercise.setAutoIncreaseCurrentDuration(currentDuration);
                exercise.setAutoIncreaseCurrentWeight(currentWeight);
                exercise.setAutoIncreaseWeightStep(weightStep);
                exercise.setAutoIncreaseFactor(factor);
            }
        }
        return exerciseRepository.save(exercise);
    }

    public int addRepsAndSets(int value, double multiplier) {
        return Math.max(value + 1, (int) Math.round(value * multiplier));
    }

    public int addDuration(int value, double multiplier) {
        return Math.max(value + 5, (int) Math.round(value * multiplier));
    }

    public double addWeight(double value, double multiplier, double weightStep) {
        double newWeight = value * multiplier;

        return Math.ceil(newWeight / weightStep) * weightStep;
    }

    public Exercise autoDecrease(Long id) {
        Exercise exercise = getExerciseById(id);
        if (exercise.getAutoIncrease()) {
            int currentSets = exercise.getAutoIncreaseCurrentSets();
            int currentReps = exercise.getAutoIncreaseCurrentReps();
            int currentDuration = exercise.getAutoIncreaseCurrentDuration();
            int minSets = exercise.getAutoIncreaseMinSets();
            int maxSets = exercise.getAutoIncreaseMaxSets();
            int minReps = exercise.getAutoIncreaseMinReps();
            int maxReps = exercise.getAutoIncreaseMaxReps();
            double currentWeight = exercise.getAutoIncreaseCurrentWeight();
            double weightStep = exercise.getAutoIncreaseWeightStep();
            double factor = exercise.getAutoIncreaseFactor();

            if (exercise.getType().equals(WorkoutType.DURATION)) {
                currentSets = subtractRepsAndSets(currentSets, factor);
                if (currentSets <= minSets) {
                    currentSets = maxSets;
                    currentDuration = subtractDuration(currentDuration, factor);
                }

            } else {
                currentReps = subtractRepsAndSets(currentReps, factor);

                if (currentReps <= minReps) {
                    currentReps = maxReps;
                    currentSets = subtractRepsAndSets(currentSets, factor);

                    if (exercise.getType().equals(WorkoutType.WEIGHTS)) {
                        if (currentSets <= minSets) {
                            currentSets = maxSets;
                            currentWeight = subtractWeight(currentWeight, factor, weightStep);
                        }
                    }
                    if (exercise.getType().equals(WorkoutType.BODYWEIGHT)) {
                        if (currentSets <= minSets) {
                            currentSets = minSets;
                        }
                    }
                }
                exercise.setAutoIncreaseCurrentSets(currentSets);
                exercise.setAutoIncreaseCurrentReps(currentReps);
                exercise.setAutoIncreaseCurrentDuration(currentDuration);
                exercise.setAutoIncreaseCurrentWeight(currentWeight);
                exercise.setAutoIncreaseWeightStep(weightStep);
                exercise.setAutoIncreaseFactor(factor);
            }
        }
        return exerciseRepository.save(exercise);
    }

    public int subtractRepsAndSets(int value, double multiplier) {
        return Math.max(value - 1, (int) Math.round(value / multiplier));
    }

    public int subtractDuration(int value, double multiplier) {
        return Math.max(value - 5, (int) Math.round(value / multiplier));
    }

    public double subtractWeight(double value, double multiplier, double weightStep) {
        double newWeight = value / multiplier;

        return Math.floor(newWeight / weightStep) * weightStep;
    }
}
