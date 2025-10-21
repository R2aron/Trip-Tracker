package com.example.TripTrack.mappers;

import com.example.TripTrack.dto.ItineraryDTO;
import com.example.TripTrack.entities.ItineraryItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

//de vazut daca trebuie facuta entity sau service
//de testat cu @Mapper, pana atunci o fac clasa utilitara cu metode stattice.

@Component
public class  ItineraryItemMapper {

    // de facut metoda care transforma 1 la 1 si metoda care transforma * la *
// de judecat daca pot sa fac o clasa generala care sa contina mai multe subclase(itineray,transportation, etc)

    public static List<ItineraryDTO> toDtoList(List<ItineraryItem> itemList)
    {
        List<ItineraryDTO> itineraryDTOList = itemList.stream()
                .map(itineraryItem -> {
                    ItineraryDTO itin = new ItineraryDTO(itineraryItem);// mai scurt cu constructor
                    return itin;
                }).collect(Collectors.toList());

        return itineraryDTOList;
    }

    public static List<ItineraryItem> itineraryListFromDtos(List<ItineraryDTO> dtos)
    {
        List<ItineraryItem> itineraryItems = dtos.stream()
                .map(itin -> {
                    ItineraryItem itineraryItem = new ItineraryItem(itin);
                    return itineraryItem;
                }).collect(Collectors.toList());
        return itineraryItems;
    }

    public static ItineraryItem updateEntityFromDto(ItineraryDTO itineraryDTO,ItineraryItem entityToUpdate)
    {
        if(itineraryDTO.getLocation() != null)
        {
            entityToUpdate.setLocation(itineraryDTO.getLocation());
        }

        if(itineraryDTO.getPrice() != null)
        {
            entityToUpdate.setPrice(itineraryDTO.getPrice());
        }

        if(itineraryDTO.getDate() != null)
        {
            entityToUpdate.setDate(itineraryDTO.getDate());
        }

        if(itineraryDTO.getPrice() != null)
        {
            entityToUpdate.setPrice(itineraryDTO.getPrice());
        }

        return entityToUpdate;
    }
}
