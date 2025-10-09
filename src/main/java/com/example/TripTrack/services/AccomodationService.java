package com.example.TripTrack.services;

import com.example.TripTrack.dto.AccommodationDTO;
import com.example.TripTrack.entities.Accommodation;
import com.example.TripTrack.mappers.AccomodationMapper;
import com.example.TripTrack.repositories.AccommodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccomodationService {
    @Autowired
    AccommodationRepository accommodationRepository;
    @Autowired
    AccomodationMapper accomodationMapper;

    public List<Accommodation> findAll()
    {
        return accommodationRepository.findAll();
    }

    public Optional<Accommodation> findById(UUID id)
    {
        return accommodationRepository.findById(id);
    }

    public Accommodation save(Accommodation accommodation)
    {
        return accommodationRepository.save(accommodation);
    }

    public void deleteById(UUID id)
    {
        accommodationRepository.deleteById(id);
    }

    public List<AccommodationDTO> getAllAccomodations(List<Accommodation> accommodationList)
    {
        List<AccommodationDTO> accommodationDTOList = AccomodationMapper.toDto(accommodationList);
        return accommodationDTOList;
    }

    public AccommodationDTO update(AccommodationDTO accommodationDTO, UUID entity)
    {
        Accommodation accommodation = accomodationMapper.updateEntityFromDto(accommodationDTO,entity);
        accommodationRepository.save(accommodation);
        AccommodationDTO accommodationDTO1 = new AccommodationDTO(accommodation);
        return accommodationDTO1
    }
}
}
