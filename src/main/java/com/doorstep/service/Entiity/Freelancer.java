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

public class Freelancer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String mobile;
    private String email;
    private String password;
    private String address;

    @ManyToOne
    @JoinColumn(name = "cid")
    private Category categoryObject;

    @ManyToOne
    @JoinColumn(name = "cityid")
    private City city;

    @OneToMany(mappedBy = "freelancerBooking")
    @JsonIgnore
    private List<Booking> bookingList;

    @OneToMany( mappedBy = "freelancerRating")
    @JsonIgnore
    private List<Rating> ratingsList;

}
