package mobileapplications.workoutbuilder.repository;

import mobileapplications.workoutbuilder.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);  // Custom method to find a user by email
}