package com.example.TripTrack.mappers;

import com.example.TripTrack.dto.AccommodationDTO;
import com.example.TripTrack.entities.Accommodation;
import com.example.TripTrack.services.AccomodationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AccomodationMapper {
    @Autowired
    AccomodationService accomodationService;

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


//    de verificat daca nu este mai eficient sa updatez entitatea din constructor.
    // cred ca ar fi mai bine sa fac o metoda separata pentru update.
    public Accommodation updateEntityFromDto(AccommodationDTO accommodationDTO, UUID entityToUpdateID)
    {
        Accommodation entityToUpdate = accomodationService.findById(entityToUpdateID)
                .orElseThrow(() -> new RuntimeException("Accomodation not faund"));

        if(accommodationDTO.getAddress() != null &&!(accommodationDTO.getAddress().trim().isEmpty()))
        {
            entityToUpdate.setAddress(accommodationDTO.getAddress());
        }

        if(accommodationDTO.getLocation() != null &&!(accommodationDTO.getLocation().trim().isEmpty()))
        {
            entityToUpdate.setLocation(accommodationDTO.getLocation());
        }

        if(accommodationDTO.getCheckIn() != null && !accommodationDTO.getCheckIn().isBefore(LocalDateTime.now()))
        {
            entityToUpdate.setCheckIn(accommodationDTO.getCheckIn());
        }

        if(accommodationDTO.getCheckOut() != null && !accommodationDTO.getCheckOut().isBefore(LocalDateTime.now()))
        {
            entityToUpdate.setCheckIn(accommodationDTO.getCheckIn());
        }

        if(accommodationDTO.getName() != null &&!(accommodationDTO.getName().trim().isEmpty()))
        {
            entityToUpdate.setName(accommodationDTO.getName());
        }
        if(accommodationDTO.getPrice() != null)
        {
            entityToUpdate.setPrice(accommodationDTO.getPrice());
        }

//        accomodationService.save(entityToUpdate);  de verificat claritatea.
        return entityToUpdate;
        //return statemant
    }
}
