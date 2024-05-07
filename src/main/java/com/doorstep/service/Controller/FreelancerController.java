package com.doorstep.service.Controller;

import com.doorstep.service.DTO.EmailDTO;
import com.doorstep.service.Entiity.Freelancer;
import com.doorstep.service.Repository.CategaoryRepo;
import com.doorstep.service.Repository.CityRepo;
import com.doorstep.service.Repository.FreelancerRepo;
import com.doorstep.service.SmptMail.SmptServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@CrossOrigin("*")


public class FreelancerController {

    @Autowired
    public FreelancerRepo freelancerRepo;

    @Autowired
    public CategaoryRepo categaoryRepo;

    @Autowired
    public CityRepo cityRepo;
    @Autowired
    public SmptServer smtpservice;



    @PostMapping("/AddFreelancer/{cid}/{cityId}")
    public ResponseEntity<String> addCategory(@PathVariable Integer cid, @PathVariable Integer cityId, @RequestBody Freelancer obj) {
        try {
            if (freelancerRepo.existsByMobile(obj.getMobile())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mobile number already exists");
            }
            if (freelancerRepo.existsByEmail(obj.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
            }

            var category = categaoryRepo.findById(cid)
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            var city = cityRepo.findById(cityId)
                    .orElseThrow(() -> new RuntimeException("City not found"));
            obj.setCategoryObject(category);
            obj.setCity(city);

            Random rnd = new Random();
            int password = rnd.nextInt(1000, 9999);
            obj.setPassword(String.valueOf(password));
            Freelancer savedFreelancer = freelancerRepo.save(obj);

            EmailDTO emailData = new EmailDTO();
            emailData.setRecipient(savedFreelancer.getEmail());

            emailData.setSubject("Login credentials");
            String message = "Welcome to A to Z DoorStep Service Optimization:\nYour Login Credentials:\nFreelnacer ID: " + savedFreelancer.getEmail() + "\nPassword: " + password;
            emailData.setMessage(message);

            smtpservice.sendMail(emailData);

            return ResponseEntity.ok("Freelancer profile created successfully. Login credentials sent to email.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating freelancer profile: " + e.getMessage());
        }
    }

    @GetMapping("/GetFreelancer")
    public ResponseEntity<?> freeLancer(){
        var data = freelancerRepo.findAll();
        return new ResponseEntity<>(data,HttpStatus.OK);
    }
    @GetMapping("/GetFreelancerByEmail/{email}")
    public ResponseEntity<?> getFreelancerByEmail (@PathVariable String email){
        var data = freelancerRepo.findByEmail(email).orElseThrow(()->new RuntimeException("Freelancer not found"));
        return new ResponseEntity<>(data, HttpStatus.OK);
    }


    @PutMapping("/UpdateFreelancer/{email}/{cityId}")
    public ResponseEntity<?> updateFreelancer (@PathVariable String email, @PathVariable Integer cityId, @RequestBody Freelancer obj){
        var freelancer = freelancerRepo.findByEmail(email).orElseThrow(()->new RuntimeException("Freelancer not found"));
        var city = cityRepo.findById(cityId).orElseThrow(()->new RuntimeException("City not found"));
        freelancer.setName(obj.getName());
        freelancer.setMobile(obj.getMobile());
        freelancer.setEmail(obj.getEmail());
        freelancer.setCity(city);
        freelancer.setAddress(obj.getAddress());
        freelancer.setPassword(obj.getPassword());
        freelancerRepo.save(freelancer);
        return new ResponseEntity<>("Profile Updated successfully", HttpStatus.OK);
    }
    @DeleteMapping("/DeleteFreelancer/{fid}")
    public ResponseEntity<?> deleteFreelancer (@PathVariable Integer fid) {
        var data = freelancerRepo.findById(fid).orElseThrow(() -> new RuntimeException("Id not found"));
        freelancerRepo.delete(data);
        return new ResponseEntity<>("Freelancer deleted successfully", HttpStatus.OK);

    }

}
