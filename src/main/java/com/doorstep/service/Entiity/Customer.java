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
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String mobile;
    private String address;
    private String email;
    private String password;
    private String status;

    @ManyToOne
    @JoinColumn(name = "cityid")
    private City cityCustomer;


    @OneToMany( mappedBy = "customerBooking")
    @JsonIgnore
    private List<Booking> booking;

    @OneToMany( mappedBy = "customerFeedback")
    @JsonIgnore
    private List<Feedback> feedbacks;

    @OneToMany( mappedBy = "customerRating")
    @JsonIgnore
    private List<Rating> ratings;


}
