package mobileapplications.workoutbuilder.repository;

import mobileapplications.workoutbuilder.domain.Workout;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    List<Workout> findWorkoutsByUserId(Long userId);
}
