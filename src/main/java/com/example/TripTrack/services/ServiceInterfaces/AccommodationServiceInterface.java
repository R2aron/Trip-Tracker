package com.example.TripTrack.services.ServiceInterfaces;

import com.example.TripTrack.dto.AccommodationDTO;
import com.example.TripTrack.entities.Accommodation;
import java.util.List;
import java.util.UUID;

public interface AccommodationServiceInterface {
    public List<AccommodationDTO> findAll();
    public AccommodationDTO getById(UUID id);
    public Accommodation findById(UUID id);
    public AccommodationDTO save(Accommodation accommodation);
    public void deleteById(UUID id);
    public List<AccommodationDTO> getAllAccomodations(List<Accommodation> accommodationList);
    public AccommodationDTO update(AccommodationDTO accommodationDTO, UUID id);

}
