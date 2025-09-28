package com.example.TripTrack.dto;

import com.example.TripTrack.entities.Accommodation;
import com.example.TripTrack.entities.ItineraryItem;
import com.example.TripTrack.entities.Transportation;
import com.example.TripTrack.entities.Trip;
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
//    private List<ItineraryItem> itineraryItems;//trebuie sa fie dto-uri

    private List<ItineraryDTO> itineraryDTOS;
    //Accommodation
//    private List<Accommodation> accommodations;
    private  List<AccommodationDTO> accommodationDTOS;
    //Transport
//    private List<Transportation> transportations;
    private List<TransportationDTO> transportationDTOS;

    public void dtoSetter(Trip trip)
    {
        this.id = trip.getId();
        this.name = trip.getName();
        this.destination = trip.getDestination();
        this.days = trip.getDays();
        this.startOfTrip = trip.getStartOfTrip();
    }//cred ca este acelasi lucru daca il fac in constructor

}