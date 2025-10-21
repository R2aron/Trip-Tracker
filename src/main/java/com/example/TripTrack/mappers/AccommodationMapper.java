package com.example.TripTrack.mappers;

import com.example.TripTrack.dto.AccommodationDTO;
import com.example.TripTrack.entities.Accommodation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccommodationMapper {

    public static List<AccommodationDTO> toDtoList(Accommodation accommodationList)
    {
        List<AccommodationDTO> accommodationDTOList = accommodationList.stream()
                .map(accommodation -> {
                    AccommodationDTO accommodationDTO = new AccommodationDTO(accommodation);
                    return accommodationDTO;
                }).collect(Collectors.toList());
        return accommodationDTOList;
    }

    public static List<Accommodation> accomodationListFromDtos(List<AccommodationDTO> dtos)
    {
        List<Accommodation> accommodationList = dtos.stream()
                .map(accom -> {
                    Accommodation accommodation = new Accommodation(accom);
                    return accommodation;
                }).collect(Collectors.toList());
        return accommodationList;
    }

    //de facut verificari in service pentru chechIn
    public static Accommodation updateEntityFromDto(AccommodationDTO accommodationDTO ,Accommodation entityToUpdate)
    {
        if(accommodationDTO.getAddress() != null)
        {
            entityToUpdate.setAddress(accommodationDTO.getAddress());
        }

        if(accommodationDTO.getLocation() != null )
        {
            entityToUpdate.setLocation(accommodationDTO.getLocation());
        }

        if(accommodationDTO.getCheckIn() != null)
        {
            entityToUpdate.setCheckIn(accommodationDTO.getCheckIn());
        }

        if(accommodationDTO.getCheckOut() != null)
        {
            entityToUpdate.setCheckOut(accommodationDTO.getCheckOut());
        }

        if(accommodationDTO.getName() != null)
        {
            entityToUpdate.setName(accommodationDTO.getName());
        }
        if(accommodationDTO.getPrice() != null)
        {
            entityToUpdate.setPrice(accommodationDTO.getPrice());
        }
        return entityToUpdate;
    }
}
