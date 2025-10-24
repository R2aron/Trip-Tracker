package com.example.TripTrack.services.ServiceInterfaces;

import com.example.TripTrack.dto.AccommodationDTO;
import com.example.TripTrack.entities.Accommodation;
import java.util.List;
import java.util.UUID;

public interface AccommodationServiceInterface {
    public List<AccommodationDTO> findAllByParentId(UUID tripId);
    public AccommodationDTO getById(UUID id);
    public Accommodation findById(UUID id);
    public AccommodationDTO save(UUID tripId,AccommodationDTO accommodationDTO);
    public void deleteById(UUID id);
    public List<AccommodationDTO> getAllAccomodations(List<Accommodation> accommodationList);
    public AccommodationDTO update(AccommodationDTO accommodationDTO, UUID id);

}
