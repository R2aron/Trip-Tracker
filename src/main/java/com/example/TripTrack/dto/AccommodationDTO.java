package com.example.TripTrack.dto;

import com.example.TripTrack.entities.Accommodation;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccommodationDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;
    @NotBlank(message = "Name name cannot be blank")
    private String name;
    @NotNull
    private String location;
    @NotNull
    @FutureOrPresent
    private LocalDateTime checkIn;
    @NotNull
    @Future
    private LocalDateTime checkOut;
    @NotNull
    private Float price;
    @NotNull
    private String address;

    public AccommodationDTO(Accommodation accommodation) {
        this.name = accommodation.getName();
        this.location = accommodation.getLocation();
        this.checkIn = accommodation.getCheckIn();
        this.checkOut = accommodation.getCheckOut();
        this.price = accommodation.getPrice();
        this.address = accommodation.getAddress();
    }
}
