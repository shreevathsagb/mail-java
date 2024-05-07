package com.doorstep.service.Controller;

import com.doorstep.service.Entiity.Category;
import com.doorstep.service.Entiity.Customer;
import com.doorstep.service.Entiity.Freelancer;
import com.doorstep.service.Repository.CityRepo;
import com.doorstep.service.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")

public class CustomerController {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    public CityRepo cityRepo;


    @PostMapping("/AddCustomer/{cityId}")
    public ResponseEntity<?> addCustomer(@PathVariable Integer cityId,@RequestBody Customer obj){
        if(customerRepo.existsByMobile(obj.getMobile())){
            throw new RuntimeException("Mobile number already exits");
        }
        if(customerRepo.existsByEmail(obj.getEmail())){
            throw new RuntimeException("Email already exits");
        }
        var city = cityRepo.findById(cityId).orElseThrow(()->new RuntimeException("City not found"));
        obj.setCityCustomer(city);
        customerRepo.save(obj);
        return new ResponseEntity<>("Registration successfully", HttpStatus.OK);
    }

    @GetMapping("/GetCustomer")
    public ResponseEntity<?> getCustomer(){
        var data = customerRepo.findAll();
        return new ResponseEntity<>(data,HttpStatus.OK);
    }
    @GetMapping("/GetCustomerByEmail/{email}")
    public ResponseEntity<?> getCustomerByEmail (@PathVariable String email){
        var data = customerRepo.findByEmail(email).orElseThrow(()->new RuntimeException("Customer not found"));
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    @PutMapping("/UpdateCustomer/{email}/{cityId}")
    public ResponseEntity<?> updateCustomer (@PathVariable String email, @PathVariable Integer cityId, @RequestBody Freelancer obj){
        var customer = customerRepo.findByEmail(email).orElseThrow(()->new RuntimeException("Customer not found"));
        var city = cityRepo.findById(cityId).orElseThrow(()->new RuntimeException("City not found"));
        customer.setName(obj.getName());
        customer.setMobile(obj.getMobile());
        customer.setEmail(obj.getEmail());
        customer.setCityCustomer(city);
        customer.setAddress(obj.getAddress());
        customer.setPassword(obj.getPassword());
        customerRepo.save(customer);
        return new ResponseEntity<>("Profile Updated successfully", HttpStatus.OK);
    }
    @PutMapping("/UpdateStatus/{email}")
    public ResponseEntity<?> updateStatus (@PathVariable String email, @RequestBody Customer obj){
        var data = customerRepo.findByEmail(email).orElseThrow(()->new RuntimeException("Customer not found"));
        data.setStatus(obj.getStatus());
        var res = customerRepo.save(data);
        return new ResponseEntity<>(res.getStatus(), HttpStatus.OK);
    }

}
