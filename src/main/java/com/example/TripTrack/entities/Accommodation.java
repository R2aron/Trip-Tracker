package com.example.TripTrack.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Accommodations")
public class Accommodation {

    @Id
    @GeneratedValue
    @org.hibernate.annotations.UuidGenerator
    private UUID id;

    private String name;
    private String location;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private float price;
    private String address;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    @JsonBackReference
    private Trip parent;

}
