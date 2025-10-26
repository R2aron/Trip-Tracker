package com.example.TripTrack.mappers;

import com.example.TripTrack.dto.AccommodationDTO;
import com.example.TripTrack.entities.Accommodation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccommodationMapper {


    public static List<AccommodationDTO> toDtoList(List<Accommodation> accommodationList)
    {
        if(accommodationList == null || accommodationList.isEmpty())
            return List.of();

        List<AccommodationDTO> accommodationDTOList = accommodationList.stream()
                .map(accommodation -> {
                    AccommodationDTO accommodationDTO = new AccommodationDTO(accommodation);
                    return accommodationDTO;
                }).collect(Collectors.toList());
        return accommodationDTOList;
    }

    public static List<Accommodation> accomodationListFromDtos(List<AccommodationDTO> dtoList, Boolean isUpdate)
    {
        if(dtoList == null || dtoList.isEmpty())
            return List.of();
        return dtoList.stream()
                .map(accom -> {
                    Accommodation accommodation = new Accommodation(accom);
                        return accommodation;
                }).collect(Collectors.toList());
    }

    public static Accommodation updateEntityFromDto(AccommodationDTO accommodationDTO ,Accommodation entityToUpdate)
    {
        entityToUpdate.setAddress(accommodationDTO.getAddress());
        entityToUpdate.setLocation(accommodationDTO.getLocation());
        entityToUpdate.setCheckIn(accommodationDTO.getCheckIn());
        entityToUpdate.setCheckOut(accommodationDTO.getCheckOut());
        entityToUpdate.setName(accommodationDTO.getName());
        entityToUpdate.setPrice(accommodationDTO.getPrice());

        return entityToUpdate;
    }
}
