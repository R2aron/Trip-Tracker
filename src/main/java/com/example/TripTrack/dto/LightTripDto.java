//package com.example.TripTrack.dto;
//
//import com.example.TripTrack.entities.Trip;
//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import lombok.Data;
//
//import java.time.LocalDateTime;
//
//@Data
//public class LightTripDto extends BaseTripDto{
//    @NotBlank(message = "Trip name cannot be blank")
//    private String name;
//    @NotNull
//    private String destination;
//    @NotNull
//    @Min(1)
//    private Integer days;
//    @NotNull
//    private LocalDateTime startOfTrip;
//
//    public LightTripDto(Trip trip)
//    {
//        this.name = trip.getName();
//        this.destination = trip.getDestination();
//        this.days = trip.getDays();
//        this.startOfTrip = trip.getStartOfTrip();
//    }
//}
