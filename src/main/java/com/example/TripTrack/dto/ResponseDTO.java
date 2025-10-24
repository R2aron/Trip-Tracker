package com.example.TripTrack.dto;

import com.example.TripTrack.entities.Trip;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ResponseDTO {
    private UUID id;
    private String name;
    private String destination;
    private int days;
    private LocalDateTime startOfTrip;

    private List<ItineraryDTO> itineraryDTOS;
    private  List<AccommodationDTO> accommodationDTOS;
    private List<TransportationDTO> transportationDTOS;

    public ResponseDTO(Trip trip)
    {
        this.id = trip.getId();
        this.name = trip.getName();
        this.destination = trip.getDestination();
        this.days = trip.getDays();
        this.startOfTrip = trip.getStartOfTrip();
    }
}
