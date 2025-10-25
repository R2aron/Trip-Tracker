package com.example.TripTrack.dto;

import com.example.TripTrack.entities.Trip;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class BaseTripDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;
    @NotBlank(message = "Trip name cannot be blank")
    private String name;
    @NotNull
    private String destination;
    @NotNull
    @Min(1)
    private Integer days;
    @NotNull
    @FutureOrPresent(message = "Start of trip cannot be in past")
    private LocalDateTime startOfTrip;
    @NotNull
    private Double totalPrice;

    public BaseTripDto(Trip trip)
    {
        this.id = trip.getId();
        this.name = trip.getName();
        this.destination = trip.getDestination();
        this.days = trip.getDays();
        this.startOfTrip = trip.getStartOfTrip();
        this.totalPrice = trip.getTotalPrice();
    }
}
