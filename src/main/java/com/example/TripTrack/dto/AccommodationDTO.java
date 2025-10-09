package com.example.TripTrack.dto;

import com.example.TripTrack.entities.Accommodation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccommodationDTO {

    //aici nu am pus id. Trebuie sa verific dac merge bine cu id-ul pus automat
    @NotBlank(message = "Name name cannot be blank")
    private String name;
    @NotNull
    private String location;
    @NotNull
    private LocalDateTime checkIn;
    @NotNull
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
