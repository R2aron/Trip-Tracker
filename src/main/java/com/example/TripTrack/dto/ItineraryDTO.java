package com.example.TripTrack.dto;

import com.example.TripTrack.entities.ItineraryItem;
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

    public ItineraryDTO(ItineraryItem itineraryItem)
    {
        this.id = itineraryItem.getId();
        this.date = itineraryItem.getDate();
        this.time = itineraryItem.getTime();
        this.location = itineraryItem.getLocation();
        this.price = itineraryItem.getPrice();
    }
}
