package com.example.TripTrack.dto;

import com.example.TripTrack.entities.Trip;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TripDTO {

    private UUID id;
    @NotBlank(message = "Trip name cannot be blank")
    private String name;
    @NotNull
    private String destination;
    @NotNull
    @Positive
    private Integer days;
    @NotNull
    private LocalDateTime startOfTrip;

    @Valid
    @NotEmpty
    private List<ItineraryDTO> itineraryDTOS;
    //Accommodation
//    private List<Accommodation> accommodations;

    @Valid
    @NotNull
    private  List<AccommodationDTO> accommodationDTOS;
    //Transport
//    private List<Transportation> transportations;

    @Valid
    @NotEmpty
    private List<TransportationDTO> transportationDTOS;

    public TripDTO(Trip trip)
    {
        this.id = trip.getId();
        this.name = trip.getName();
        this.destination = trip.getDestination();
        this.days = trip.getDays();
        this.startOfTrip = trip.getStartOfTrip();
    }//cred ca este acelasi lucru daca il fac in constructor

}