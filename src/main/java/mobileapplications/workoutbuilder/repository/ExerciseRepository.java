package mobileapplications.workoutbuilder.repository;

import mobileapplications.workoutbuilder.domain.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    // You can add custom queries if needed
}
