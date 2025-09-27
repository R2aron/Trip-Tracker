package com.example.TripTrack.entities;

import com.example.TripTrack.dto.TripDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trips")
public class Trip {

    @Id
    @GeneratedValue
    @org.hibernate.annotations.UuidGenerator
    private UUID id;
    private String name;
    private String destination;
    private int days;
    private LocalDateTime startOfTrip;



    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<ItineraryItem> itineraryItems;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private  List<Accommodation> accommodations;

    @OneToMany(mappedBy = "parentTrip", cascade = CascadeType.ALL)
    private List<Transportation> transportation;

//    public Trip(TripDTO dto) {
//        this.name = dto.getName();
//    }
}
