package mobileapplications.workoutbuilder.repository;

import mobileapplications.workoutbuilder.domain.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetRepository extends JpaRepository<Set, Long> {
}
