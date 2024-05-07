package com.doorstep.service.Controller;


import com.doorstep.service.DTO.LoginDTO;
import com.doorstep.service.Entiity.Admin;
import com.doorstep.service.Entiity.Customer;
import com.doorstep.service.Entiity.Freelancer;
import com.doorstep.service.Repository.AdminRepo;
import com.doorstep.service.Repository.CustomerRepo;
import com.doorstep.service.Repository.FreelancerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")

public class LoginController {


    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private FreelancerRepo freelancerRepo;


    @PostMapping("/LoginVerify")
    public ResponseEntity<?> loginVerify(@RequestBody LoginDTO obj) {
        if (obj.getUserType().equals("Admin")) {
            Admin admin = adminRepo.findById(obj.getId()).orElseThrow(() -> new RuntimeException("Admin not found"));
            if (admin.getPassword().equals(obj.getPassword())) {
                return new ResponseEntity<>("Admin", HttpStatus.OK);
            } else {
                throw new RuntimeException("Invalid Password");
            }
        } else if (obj.getUserType().equals("Customer")) {
            Customer customer = customerRepo.findByEmail(obj.getId()).orElseThrow(() -> new RuntimeException("Customer not found"));
            if(customer.getStatus().equals("Blocked")){
                throw new RuntimeException("Sorry..your account has been blocked by Admin..!");
            }
           else if (customer.getPassword().equals(obj.getPassword())) {
                return new ResponseEntity<>("Customer", HttpStatus.OK);
            } else {
                throw new RuntimeException("Invalid Password");
            }
        }
        else if (obj.getUserType().equals("Freelancer")) {
            Freelancer freelancer = freelancerRepo.findByEmail(obj.getId()).orElseThrow(() -> new RuntimeException("Freelancer not found"));
            if (freelancer.getPassword().equals(obj.getPassword())) {
                return new ResponseEntity<>("Freelancer", HttpStatus.OK);
            } else {
                throw new RuntimeException("Invalid Password");
            }
        }
        else {
            throw new RuntimeException("Invalid User type");
        }

    }
}
