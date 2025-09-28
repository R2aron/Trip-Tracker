package com.example.TripTrack.mappers;

import com.example.TripTrack.dto.ItineraryDTO;
import com.example.TripTrack.entities.ItineraryItem;

import java.util.List;
import java.util.stream.Collectors;

//de vazut daca trebuie facuta entity sau service
//de testat cu @Mapper, pana atunci o fac clasa utilitara cu metode stattice.
public class  ItineraryItemMapper {

    // de facut metoda care transforma 1 la 1 si metoda care transforma * la *
// de judecat daca pot sa fac o clasa generala care sa contina mai multe subclase(itineray,transportation, etc)

    public static List<ItineraryDTO> toDto(List<ItineraryItem> itemList)
    {
        List<ItineraryDTO> itineraryDTOList = itemList.stream()
                .map(itineraryItem -> {
                    ItineraryDTO itin = new ItineraryDTO(itineraryItem);// mai scurt cu constructor
                    return itin;
                }).collect(Collectors.toList());

        return itineraryDTOList;
    }
}
