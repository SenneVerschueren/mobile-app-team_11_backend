package mobileapplications.workoutbuilder.repository;

import mobileapplications.workoutbuilder.domain.Bodyweight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BodyweightRepository extends JpaRepository<Bodyweight, Long> {

    List<Bodyweight> findByUserId(Long id);
}
