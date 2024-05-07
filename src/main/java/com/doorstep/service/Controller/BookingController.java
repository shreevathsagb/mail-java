package com.doorstep.service.Controller;


import com.doorstep.service.Entiity.Booking;
import com.doorstep.service.Repository.BookingRepo;
import com.doorstep.service.Repository.CustomerRepo;
import com.doorstep.service.Repository.FreelancerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin("*")

public class BookingController {

    @Autowired
    public BookingRepo bookingRepo;

    @Autowired
    public CustomerRepo customerRepo;

    @Autowired
    public FreelancerRepo freelancerRepo;

    @PostMapping("/BookFreelancer/{cid}/{fid}")
    public ResponseEntity<?> bookFreelancer(@PathVariable String cid, @PathVariable String fid, @RequestBody Booking obj){
        var customer =customerRepo.findByEmail(cid).orElseThrow(()->new RuntimeException("Customer not found"));
        var freelancer =freelancerRepo.findByEmail(fid).orElseThrow(()->new RuntimeException("Freelancer not found"));
        obj.setCustomerBooking(customer);
        obj.setFreelancerBooking(freelancer);
        obj.setDate(String.valueOf(LocalDate.now()));
        obj.setStatus("Pending");
        bookingRepo.save(obj);
        return new ResponseEntity<>("Booking successfully", HttpStatus.OK);
    }


    @GetMapping("/GetBookingByCustomer/{cid}")
    public ResponseEntity<?> getBookingByCustomer(@PathVariable String cid) {
       var bookings = bookingRepo.findByCustomerBookingEmail(cid);
       return new ResponseEntity<>(bookings,HttpStatus.OK);
    }

    @GetMapping("/GetBookingByFreelancer/{fid}")
    public ResponseEntity<?> getBookingByFreelancer(@PathVariable String fid) {
        var bookings = bookingRepo.findByFreelancerBookingEmail(fid);
        return new ResponseEntity<>(bookings,HttpStatus.OK);
    }


    @PutMapping("/EstimateCost/{bid}")
    public ResponseEntity<?> estimateCost(@PathVariable Integer bid, @RequestBody Booking obj){
    var booking = bookingRepo.findById(bid).orElseThrow(()->new RuntimeException("Id not found"));
    booking.setAmount(obj.getAmount());
    booking.setComplaintReply(obj.getComplaintReply());
    booking.setStatus("Estimated cost approval pending");
    bookingRepo.save(booking);
    return new ResponseEntity<>("Estimated cost updated successfully", HttpStatus.OK);
    }


    @PutMapping("/RejectBooking/{bid}")
    public ResponseEntity<?> rejectBooking(@PathVariable Integer bid, @RequestBody Booking obj){
    var booking = bookingRepo.findById(bid).orElseThrow(()->new RuntimeException("Id not found"));
    booking.setComplaintReply(obj.getComplaintReply());
    booking.setStatus("Booking rejected by freelancer");
    bookingRepo.save(booking);
    return new ResponseEntity<>("Booking rejected  successfully", HttpStatus.OK);
    }

    @PutMapping("/ApproveCost/{bid}")
    public ResponseEntity<?> approveCost(@PathVariable Integer bid){
        var booking = bookingRepo.findById(bid).orElseThrow(()->new RuntimeException("Id not found"));
        booking.setStatus("Customer estimated cost approved");
        bookingRepo.save(booking);
        return new ResponseEntity<>("Approved successfully", HttpStatus.OK);
    }

    @PutMapping("/WorkCompleted/{bid}")
    public ResponseEntity<?> workCompleted(@PathVariable Integer bid){
        var booking = bookingRepo.findById(bid).orElseThrow(()->new RuntimeException("Id not found"));
        booking.setStatus("Customer complaint has been solved");
        bookingRepo.save(booking);
        return new ResponseEntity<>("Complaint solved successfully", HttpStatus.OK);
    }

    @PutMapping("/Appointment/{bid}")
    public ResponseEntity<?> Appointment(@PathVariable Integer bid,@RequestBody Booking obj){
        var booking = bookingRepo.findById(bid).orElseThrow(()->new RuntimeException("Id not found"));
        booking.setStatus("Appointment Scheduled");
        booking.setAppDate(obj.getAppDate());
        booking.setAppTime(obj.getAppTime());
        bookingRepo.save(booking);
        return new ResponseEntity<>("Appointment scheduled successfully", HttpStatus.OK);
    }
}
