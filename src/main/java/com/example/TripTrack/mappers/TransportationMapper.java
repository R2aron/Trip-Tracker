package com.example.TripTrack.mappers;

import com.example.TripTrack.dto.TransportationDTO;
import com.example.TripTrack.entities.Transportation;

import java.util.List;
import java.util.stream.Collectors;

public class TransportationMapper {

    public static List<TransportationDTO> toDto(List<Transportation> transportation)
    {
        List<TransportationDTO> transportationDTOList = transportation.stream()
                .map(transp -> {
                    TransportationDTO transportationDTO = new TransportationDTO(transp);
                    return  transportationDTO;
                }).collect(Collectors.toList());
        return transportationDTOList;
    }
}
