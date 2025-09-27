package com.example.TripTrack.dto;

import com.example.TripTrack.entities.Accommodation;
import com.example.TripTrack.entities.ItineraryItem;
import com.example.TripTrack.entities.Transportation;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class TripDTO {

    private UUID id;
    private String name;
    private String destination;
    private int days;
    private LocalDateTime startOfTrip;

    //itinerary
    private List<ItineraryItem> itineraryItems;//trebuie sa fie dto-uri

    //Accommodation
    private List<Accommodation> accommodations;

    //Transport
    private List<Transportation> transportations;

}