package com.doorstep.service.Repository;

import com.doorstep.service.Entiity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepo extends JpaRepository<Rating, Integer> {

    List<Rating> findByCustomerRatingEmail(String email);
    List<Rating> findByFreelancerRatingEmail(String email);
    List<Rating> findByBookingRatingId(Integer id);

    boolean existsByCustomerRatingEmail(String email);
    boolean existsByBookingRatingId(Integer id);
}
