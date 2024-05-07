package com.doorstep.service.Controller;

import com.doorstep.service.Entiity.Category;
import com.doorstep.service.Repository.CategaoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")

public class CategoryController {

    @Autowired
    private CategaoryRepo categaoryRepo;

    @PostMapping("/AddCategory")
    public ResponseEntity<?> addCategory(@RequestBody Category obj){
        categaoryRepo.save(obj);
        return new ResponseEntity<>("Category added successfully", HttpStatus.OK);
    }

    @GetMapping("/GetCategory")
    public ResponseEntity<?> getCategory(){
        var data = categaoryRepo.findAll();
        return new ResponseEntity<>(data,HttpStatus.OK);
    }

    @PutMapping("/UpdateCategory/{cid}")
    public ResponseEntity<?> updateCategory (@PathVariable Integer cid, @RequestBody Category obj){
        var data = categaoryRepo.findById(cid).orElseThrow(()->new RuntimeException("Id not found"));
        data.setCategoryName(obj.getCategoryName());
        categaoryRepo.save(data);
        return new ResponseEntity<>("Category updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/DeleteCategory/{cid}")
    public ResponseEntity<?> deleteCategory (@PathVariable Integer cid) {
        var data = categaoryRepo.findById(cid).orElseThrow(() -> new RuntimeException("Id not found"));
        categaoryRepo.delete(data);
        return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);

    }
}
