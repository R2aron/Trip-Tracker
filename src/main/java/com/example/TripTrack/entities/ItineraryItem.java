package com.example.TripTrack.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="itinerary_items")
public class ItineraryItem {

    @Id
    @GeneratedValue
    @org.hibernate.annotations.UuidGenerator
    private UUID id;
    private LocalDate date;
    private LocalTime time;
    private String location;
    private float price;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    @JsonBackReference
    private Trip parent;
}
