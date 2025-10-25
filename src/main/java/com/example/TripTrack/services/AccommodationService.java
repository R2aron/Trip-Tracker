package com.example.TripTrack.services;

import com.example.TripTrack.dto.AccommodationDTO;
import com.example.TripTrack.entities.Accommodation;
import com.example.TripTrack.entities.Trip;
import com.example.TripTrack.mappers.AccommodationMapper;
import com.example.TripTrack.repositories.AccommodationRepository;
import com.example.TripTrack.repositories.TripRepository;
import com.example.TripTrack.services.ServiceInterfaces.AccommodationServiceInterface;
import com.example.TripTrack.services.ServiceInterfaces.UpdateTotalPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccommodationService implements AccommodationServiceInterface {
    @Autowired
    AccommodationRepository accommodationRepository;
    @Autowired
    TripRepository tripRepository;
    @Autowired
    UpdateTotalPrice updateTotalPrice;

    @Override
    public AccommodationDTO save(UUID tripId, AccommodationDTO dto)
    {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        Accommodation accommodation = new Accommodation(dto);
        accommodation.setParent(trip);
        accommodationRepository.save(accommodation);
        updateTotalPrice.updateTotalPrice(tripId);

        return new AccommodationDTO(accommodation);

    }

    @Override
    public List<Accommodation> findAllByParentId(UUID tripId)
    {
        return accommodationRepository.findAllByParentId(tripId);
    }

    @Override
    public AccommodationDTO getById(UUID id)
    {
        return new AccommodationDTO(accommodationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Accommodation not found")));
    }

    @Override
    public Accommodation findById(UUID id)
    {
        return accommodationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Accommodation not found"));
    }


    @Override
    public void deleteById(UUID id)
    {
        accommodationRepository.deleteById(id);
        updateTotalPrice.updateTotalPrice(id);
    }


    @Override
    public AccommodationDTO update(AccommodationDTO accommodationDTO, UUID id)
    {
        Accommodation accommodationToUpdate = findById(id);
        accommodationRepository.save(AccommodationMapper.updateEntityFromDto(accommodationDTO,accommodationToUpdate));
        updateTotalPrice.updateTotalPrice(id);
        return new AccommodationDTO(accommodationToUpdate);
    }

    @Override
    public List<AccommodationDTO> getAllAccomodations(List<Accommodation> accommodationList)
    {
        return AccommodationMapper.toDtoList(accommodationList);
    }
}
