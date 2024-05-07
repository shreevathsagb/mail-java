package com.doorstep.service.Entiity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter

public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float rating;

    @ManyToOne
    @JoinColumn(name = "cid")
    private Customer customerRating;

    @ManyToOne
    @JoinColumn(name = "fid")
    private Freelancer freelancerRating;


    @ManyToOne
    @JoinColumn(name = "bid")
    private Booking bookingRating;
}
