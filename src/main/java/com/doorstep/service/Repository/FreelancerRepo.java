package com.doorstep.service.Repository;

import com.doorstep.service.Entiity.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FreelancerRepo extends JpaRepository<Freelancer, Integer> {
    boolean existsByMobile(String mobile);
    boolean existsByEmail(String email);

    Optional<Freelancer> findByEmail(String email);
}
