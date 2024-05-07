package com.doorstep.service.Repository;

import com.doorstep.service.Entiity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepo extends JpaRepository<Booking, Integer> {

    List<Booking> findByCustomerBookingEmail(String email);

    List<Booking> findByFreelancerBookingEmail(String email);
}
