package com.example.TripTrack.dto;

import com.example.TripTrack.entities.ItineraryItem;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ItineraryDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;
    @NotNull
    @FutureOrPresent
    private LocalDate date;
    @NotNull
    private LocalTime time;
    @NotEmpty
    private String location;
    @NotNull
    private Float price;
    @NotNull
    private Boolean isVisited;

    public ItineraryDTO(ItineraryItem itineraryItem)
    {
        this.id = itineraryItem.getId();
        this.date = itineraryItem.getDate();
        this.time = itineraryItem.getTime();
        this.location = itineraryItem.getLocation();
        this.price = itineraryItem.getPrice();
    }
}
