package com.example.TripTrack.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
public class ItineraryDTO {

    private UUID id;
    private LocalDate date;
    private LocalTime time;
    private String location;
    private float price;
}
