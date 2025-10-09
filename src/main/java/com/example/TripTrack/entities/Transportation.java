package com.example.TripTrack.entities;

import com.example.TripTrack.dto.TransportationDTO;
import com.example.TripTrack.enums.TransportationTypes;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Transport")
public class Transportation {
    @Id
    @GeneratedValue
    @org.hibernate.annotations.UuidGenerator
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private com.example.TripTrack.enums.TransportationTypes category;
    private String route;
    private Float distance;
    private  Float price;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    @JsonBackReference
    private Trip parentTrip;

    public Transportation(TransportationDTO dto) {
        this.id = dto.getId();
        this.category = dto.getCategory();
        this.route = dto.getRoute();
        this.distance = dto.getDistance();
        this.price = dto.getPrice();
    }
}
