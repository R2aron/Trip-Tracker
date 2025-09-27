package com.example.TripTrack.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccommodationDTO {

    private String name;
    private String location;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private float price;
    private String address;

}
