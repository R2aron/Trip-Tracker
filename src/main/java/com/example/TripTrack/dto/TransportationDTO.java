package com.example.TripTrack.dto;


import com.example.TripTrack.entities.Transportation;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class TransportationDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;
    @NotNull(message = "Please chose one category")
    private com.example.TripTrack.enums.TransportationTypes category;
    @NotNull
    private String route;
    @NotNull
    @Positive
    private Float distance;
    @NotNull
    private  Double price;


    public TransportationDTO(Transportation transportation)
    {
        this.id = transportation.getId();
        this.route = transportation.getRoute();
        this.distance = transportation.getDistance();
        this.price = transportation.getPrice();
        this.category = transportation.getCategory();
    }

}
