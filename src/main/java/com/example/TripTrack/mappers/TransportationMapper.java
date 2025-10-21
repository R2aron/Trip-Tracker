package com.example.TripTrack.mappers;

import com.example.TripTrack.dto.TransportationDTO;
import com.example.TripTrack.entities.Transportation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransportationMapper {

    public static List<TransportationDTO> toDtoList(List<Transportation> transportation)
    {
        List<TransportationDTO> transportationDTOList = transportation.stream()
                .map(transp -> {
                    TransportationDTO transportationDTO = new TransportationDTO(transp);
                    return  transportationDTO;
                }).collect(Collectors.toList());
        return transportationDTOList;
    }

    public static List<Transportation> transportationListFromDtos(List<TransportationDTO> dtos)
    {
        List<Transportation> transportationList = dtos.stream()
                .map(transp -> {
                    Transportation transportation  = new Transportation(transp);
                    return transportation;
                }).collect(Collectors.toList());
        return transportationList;
    }

    public Transportation updateEntityFromDto(TransportationDTO transportationDTO,Transportation entityToUpdate) {
        if (transportationDTO.getCategory() != null) {
            entityToUpdate.setCategory(transportationDTO.getCategory());
        }

        if (transportationDTO.getPrice() != null) {
            entityToUpdate.setPrice(transportationDTO.getPrice());
        }

        if (transportationDTO.getDistance() != null) {
            entityToUpdate.setDistance(transportationDTO.getDistance());
        }

        if (transportationDTO.getRoute() != null) {
            entityToUpdate.setRoute(transportationDTO.getRoute());
        }

        return entityToUpdate;
    }
}
