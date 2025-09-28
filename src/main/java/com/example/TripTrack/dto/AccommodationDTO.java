package com.example.TripTrack.dto;

import com.example.TripTrack.entities.Accommodation;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccommodationDTO {

    //aici nu am pus id. Trebuie sa verific dac merge bine cu id-ul pus automat
    private String name;
    private String location;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private float price;
    private String address;

    public AccommodationDTO(Accommodation accommodation) {
        this.name = accommodation.getName();
        this.location = accommodation.getLocation();
        this.checkIn = accommodation.getCheckIn();
        this.checkOut = accommodation.getCheckOut();
        this.price = accommodation.getPrice();
        this.address = accommodation.getAddress();
    }
}
