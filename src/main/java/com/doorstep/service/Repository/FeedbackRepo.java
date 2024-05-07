package com.doorstep.service.Repository;

import com.doorstep.service.Entiity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {

        List<Feedback> findByCustomerFeedbackEmail(String email);
}
