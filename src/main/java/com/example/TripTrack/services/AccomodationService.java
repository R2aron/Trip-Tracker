package com.example.TripTrack.services;

import com.example.TripTrack.dto.AccommodationDTO;
import com.example.TripTrack.entities.Accommodation;
import com.example.TripTrack.mappers.AccommodationMapper;
import com.example.TripTrack.repositories.AccommodationRepository;
import com.example.TripTrack.services.ServiceInterfaces.AccomodationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccomodationService implements AccomodationServiceInterface {
    @Autowired
    AccommodationRepository accommodationRepository;

    @Override
    public List<AccommodationDTO> findAll()
    {
        List<AccommodationDTO> accommodationDTOList = AccommodationMapper.toDtoList(accommodationRepository.findAll());
        return accommodationDTOList;
    }

    @Override
    public AccommodationDTO getById(UUID id)
    {
        return new AccommodationDTO(accommodationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transportation not found")));
    }

    //metoda findById care returneaza o entitate

    @Override
    public Accommodation findById(UUID id)
    {
        return accommodationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transportation not found"));
    }

    @Override
    public AccommodationDTO save(Accommodation accommodation)
    {
        return  new AccommodationDTO(accommodationRepository.save(accommodation));
    }

    @Override
    public void deleteById(UUID id)
    {
        accommodationRepository.deleteById(id);
    }

    @Override
    public List<AccommodationDTO> getAllAccomodations(List<Accommodation> accommodationList)
    {
        List<AccommodationDTO> accommodationDTOList = AccommodationMapper.toDtoList(accommodationList);
        return accommodationDTOList;
    }

    @Override
    public AccommodationDTO update(AccommodationDTO accommodationDTO, UUID id)
    {
        Accommodation accommodationToUpdate = findById(id);
        Accommodation accommodation = AccommodationMapper.updateEntityFromDto(accommodationDTO,accommodationToUpdate);
        accommodationRepository.save(accommodation);
        return new AccommodationDTO(accommodation);
    }
}
