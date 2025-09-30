package com.example.TripTrack.dto;


import com.example.TripTrack.entities.Transportation;
import com.example.TripTrack.entities.Trip;
import lombok.Data;

import java.util.UUID;

@Data
public class TransportationDTO {

    private UUID id;//aici  am pus id. Trebuie sa verific dac merge bine cu id-ul pus automat
    private com.example.TripTrack.enums.TransportationTypes category;
    private String route;
    private Float distance;
    private  Float price;


    public TransportationDTO(Transportation transportation)
    {
        this.id = transportation.getId();
        this.route = transportation.getRoute();;
        this.distance = transportation.getDistance();
        this.price = transportation.getPrice();
    }

}
