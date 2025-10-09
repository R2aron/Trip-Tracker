package com.example.TripTrack.dto;


import com.example.TripTrack.entities.Trip;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
public class LightResponseDTO {
    private UUID id;
    private String name;
    private String destination;
    private int days;
    private LocalDateTime startOfTrip;
    private byte itineraryItemCount;
    private byte accomodationCount;
    private byte transportationCount;

    public LightResponseDTO(Trip trip)
    {
        this.id = trip.getId();
        this.name = trip.getName();
        this.destination = trip.getDestination();
        this.days = trip.getDays();
        this.startOfTrip = trip.getStartOfTrip();
        this.itineraryItemCount = (byte)trip.getItineraryItems().size();
        this.accomodationCount = (byte) trip.getAccommodations().size();
        this.transportationCount = (byte) trip.getTransportation().size();
    }
}
