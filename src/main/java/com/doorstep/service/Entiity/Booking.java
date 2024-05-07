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

public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "LONGTEXT")
    private String complaint;
    @Column(columnDefinition = "LONGTEXT")
    private String complaintReply;
    @Column(columnDefinition = "LONGTEXT")
    private String image;
    private String date;
    private Float amount;
    private String status;
    private String appDate;
    private String appTime;

    @ManyToOne
    @JoinColumn(name = "cid")
    private Customer customerBooking;


    @ManyToOne
    @JoinColumn(name = "fid")
    private Freelancer freelancerBooking;

    @OneToMany( mappedBy = "bookingRating")
    @JsonIgnore
    private List<Rating> ratings;;

}
