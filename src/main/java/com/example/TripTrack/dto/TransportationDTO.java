package com.example.TripTrack.dto;


import lombok.Data;

import java.util.UUID;

@Data
public class TransportationDTO {

    private UUID id;
    private Enum<com.example.TripTrack.enums.TransportationTypes> category;
    private String route;
    private Float distance;
    private  Float price;

}
