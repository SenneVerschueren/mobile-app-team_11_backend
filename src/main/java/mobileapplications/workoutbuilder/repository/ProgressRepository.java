package mobileapplications.workoutbuilder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mobileapplications.workoutbuilder.domain.Progress;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {

    List<Progress> findByExerciseId(Long id);

}
