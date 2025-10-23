package com.example.TripTrack.mappers;

import com.example.TripTrack.dto.TransportationDTO;
import com.example.TripTrack.entities.Transportation;

import java.util.List;
import java.util.stream.Collectors;

public class TransportationMapper {

    public static List<TransportationDTO> toDtoList(List<Transportation> transportationList)
    {
        if(transportationList == null || transportationList.isEmpty())
            return List.of();
        List<TransportationDTO> transportationDTOList = transportationList.stream()
                .map(transp -> {
                    TransportationDTO transportationDTO = new TransportationDTO(transp);
                    return  transportationDTO;
                }).collect(Collectors.toList());
        return transportationDTOList;
    }

    public static List<Transportation> transportationListFromDtos(List<TransportationDTO> dtoList)
    {
        if(dtoList == null || dtoList.isEmpty())
            return List.of();
        List<Transportation> transportationList = dtoList.stream()
                .map(transp -> {
                    Transportation transportation  = new Transportation(transp);
                    return transportation;
                }).collect(Collectors.toList());
        return transportationList;
    }

    public static Transportation updateEntityFromDto(TransportationDTO transportationDTO,Transportation entityToUpdate)
    {
        entityToUpdate.setCategory(transportationDTO.getCategory());
        entityToUpdate.setPrice(transportationDTO.getPrice());
        entityToUpdate.setDistance(transportationDTO.getDistance());
        entityToUpdate.setRoute(transportationDTO.getRoute());
        return entityToUpdate;
    }
}
