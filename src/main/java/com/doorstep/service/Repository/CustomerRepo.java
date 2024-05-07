package com.doorstep.service.Repository;

import com.doorstep.service.Entiity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    boolean existsByMobile(String mobile);
    boolean existsByEmail(String email);

    Optional<Customer> findByEmail(String email);
}
