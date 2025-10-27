package com.example.TripTrack.mappers;

import com.example.TripTrack.dto.ItineraryDTO;
import com.example.TripTrack.entities.ItineraryItem;

import java.util.List;
import java.util.stream.Collectors;

public class  ItineraryItemMapper {

    // de facut metoda care transforma 1 la 1 si metoda care transforma * la *
// de judecat daca pot sa fac o clasa generala care sa contina mai multe subclase(itineray,transportation, etc)

    public static List<ItineraryDTO> toDtoList(List<ItineraryItem> itemList)
    {
        if(itemList == null || itemList.isEmpty())
            return List.of();
        List<ItineraryDTO> itineraryDTOList = itemList.stream()
                .map(itineraryItem -> {
                    ItineraryDTO itin = new ItineraryDTO(itineraryItem);// mai scurt cu constructor
                    return itin;
                }).collect(Collectors.toList());

        return itineraryDTOList;
    }

    public static List<ItineraryItem> itineraryListFromDtos(List<ItineraryDTO> dtoList)
    {
        if(dtoList == null || dtoList.isEmpty())
            return List.of();
        List<ItineraryItem> itineraryItems = dtoList.stream()
                .map(itin -> {
                    ItineraryItem itineraryItem = new ItineraryItem(itin);
                    return itineraryItem;
                }).collect(Collectors.toList());
        return itineraryItems;
    }

    public static ItineraryItem updateEntityFromDto(ItineraryDTO itineraryDTO,ItineraryItem entityToUpdate)
    {
        entityToUpdate.setLocation(itineraryDTO.getLocation());
        entityToUpdate.setPrice(itineraryDTO.getPrice());
        entityToUpdate.setDate(itineraryDTO.getDate());
        entityToUpdate.setPrice(itineraryDTO.getPrice());
        entityToUpdate.setIsVisited(itineraryDTO.getIsVisited());
        return entityToUpdate;
    }
}
