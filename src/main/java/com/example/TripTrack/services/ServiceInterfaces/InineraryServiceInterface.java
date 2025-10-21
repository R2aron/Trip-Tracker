package com.example.TripTrack.services.ServiceInterfaces;

import com.example.TripTrack.dto.ItineraryDTO;
import com.example.TripTrack.entities.ItineraryItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InineraryServiceInterface {
    public List<ItineraryDTO> findAll();
    public Optional<ItineraryDTO> findByID(UUID id);
    public ItineraryDTO save(ItineraryItem itineraryItem);
    public void deleteById(UUID id);
    public List<ItineraryDTO> getAllItinerary(List<ItineraryItem> itineraryItemList);
    public ItineraryDTO update(ItineraryDTO itineraryDTO, UUID entity);
}
