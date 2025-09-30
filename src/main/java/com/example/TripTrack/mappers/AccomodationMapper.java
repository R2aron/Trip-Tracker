package com.example.TripTrack.mappers;

import com.example.TripTrack.dto.AccommodationDTO;
import com.example.TripTrack.entities.Accommodation;

import java.util.List;
import java.util.stream.Collectors;

public class AccomodationMapper {

    public static List<AccommodationDTO> toDto(List<Accommodation> accommodationList)
    {
        List<AccommodationDTO> accommodationDTOList = accommodationList.stream()
                .map(accommodation -> {
                    AccommodationDTO accommodationDTO = new AccommodationDTO(accommodation);
                    return accommodationDTO;
                }).collect(Collectors.toList());
        return accommodationDTOList;
    }

    public static List<Accommodation> entityfromDto(List<AccommodationDTO> dtos)
    {
        List<Accommodation> accommodationList = dtos.stream()
                .map(accom -> {
                    Accommodation accommodation = new Accommodation(accom);
                    return accommodation;
                }).collect(Collectors.toList());
        return accommodationList;
    }
}
