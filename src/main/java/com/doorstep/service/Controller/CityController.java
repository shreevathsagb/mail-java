package com.doorstep.service.Controller;

import com.doorstep.service.Entiity.City;
import com.doorstep.service.Repository.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")

public class CityController {

    @Autowired
    public CityRepo cityRepo;

    @PostMapping("/AddCity")
    public ResponseEntity<?> addCity(@RequestBody City obj){
        cityRepo.save(obj);
        return new ResponseEntity<>("City added successfully", HttpStatus.OK);
    }

    @GetMapping("/GetCity")
    public ResponseEntity<?> getCity(){
        var data = cityRepo.findAll();
        return new ResponseEntity<>(data,HttpStatus.OK);
    }

    @PutMapping("/UpdateCity/{cid}")
    public ResponseEntity<?> updateCategory (@PathVariable Integer cid, @RequestBody City obj){
        var data = cityRepo.findById(cid).orElseThrow(()->new RuntimeException("City not found"));
        data.setCity(obj.getCity());
        cityRepo.save(data);
        return new ResponseEntity<>("City updated successfully", HttpStatus.OK);
    }
}
