package com.doorstep.service.Controller;

import com.doorstep.service.Entiity.Admin;
import com.doorstep.service.Repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")

public class AdminController {

    @Autowired
    public AdminRepo adminRepo;

    @PutMapping("/ChangePassword/{adminId}")
    public ResponseEntity<?> changePassword(@PathVariable String adminId, @RequestBody Admin obj){
        var admin = adminRepo.findById(adminId).orElseThrow(()->new RuntimeException("Admin not found "));
        admin.setPassword(obj.getPassword());
        adminRepo.save(admin);
        return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
    }

}
