package com.example.TripTrack.dto;

import com.example.TripTrack.entities.ItineraryItem;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItineraryDTO {

    private UUID id;
    @NotNull
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
