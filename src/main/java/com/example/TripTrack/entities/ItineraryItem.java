package com.example.TripTrack.entities;

import com.example.TripTrack.dto.ItineraryDTO;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate date;
    private LocalTime time;
    private String location;
    private Float price;
    private Boolean isVisited = false;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip parent;


    public ItineraryItem(ItineraryDTO dto) {
        this.id = dto.getId();
        this.date = dto.getDate();
        this.time = dto.getTime();
        this.location = dto.getLocation();
        this.price = dto.getPrice();
    }
}
