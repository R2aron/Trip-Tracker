package com.example.TripTrack.entities;

import com.example.TripTrack.dto.AccommodationDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
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
    private Float price;
    private String address;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    @JsonBackReference
    private Trip parent;

    public Accommodation(AccommodationDTO dto) {
        this.name = dto.getName();
        this.location = dto.getLocation();
        this.checkIn = dto.getCheckIn();
        this.checkOut = dto.getCheckOut();
        this.price = dto.getPrice();
        this.address = dto.getAddress();
    }
}
