package com.example.TripTrack.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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
}
