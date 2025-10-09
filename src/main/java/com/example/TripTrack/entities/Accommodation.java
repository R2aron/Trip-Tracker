package com.example.TripTrack.entities;

import com.example.TripTrack.dto.AccommodationDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String location;
    @NotNull
    private LocalDateTime checkIn;
    @NotNull
    private LocalDateTime checkOut;
    @NotNull
    private float price;
    @NotNull
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
