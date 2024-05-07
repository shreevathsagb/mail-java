package com.doorstep.service.Entiity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String city;

    @OneToMany(mappedBy = "city")
    @JsonIgnore
    private List<Freelancer> freelancerCity;

    @OneToMany(mappedBy = "cityCustomer")
    @JsonIgnore
    private List<Customer> customers;

}
