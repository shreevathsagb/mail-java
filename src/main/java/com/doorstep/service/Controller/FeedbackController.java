package com.doorstep.service.Controller;

import com.doorstep.service.Entiity.Customer;
import com.doorstep.service.Entiity.Feedback;
import com.doorstep.service.Repository.CustomerRepo;
import com.doorstep.service.Repository.FeedbackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin("*")

public class FeedbackController {

    @Autowired
    public FeedbackRepo feedbackRepo;

    @Autowired
    public CustomerRepo customerRepo;

    @PostMapping("/PostFeedback/{email}")
    public ResponseEntity<?> postFeedback (@RequestBody Feedback obj, @PathVariable String email){
        var customer = customerRepo.findByEmail(email).orElseThrow(()->new RuntimeException("Customer not found"));
        obj.setCustomerFeedback(customer);
        obj.setDate(String.valueOf(LocalDate.now()));
        feedbackRepo.save(obj);
        return new ResponseEntity<>("Feedback posted successfully", HttpStatus.OK);
    }

    @GetMapping("/GetFeedbackByEmail/{email}")
    public ResponseEntity<?> getFeedbackByEmail (@PathVariable String email){
        var data = feedbackRepo.findByCustomerFeedbackEmail(email);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/GetFeedback")
    public ResponseEntity<?> getFeedback (){
        var data = feedbackRepo.findAll();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }


}
