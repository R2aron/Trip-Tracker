package com.example.TripTrack.services.ServiceInterfaces;

import com.example.TripTrack.dto.ItineraryDTO;
import com.example.TripTrack.entities.ItineraryItem;

import java.util.List;
import java.util.UUID;

public interface ItineraryServiceInterface {
    public List<ItineraryItem> findAll();
    public List<ItineraryItem> findAllByParentId(UUID tripId);
    public ItineraryDTO getById(UUID id);
    public ItineraryItem findById(UUID id);
    public ItineraryDTO save(UUID tripId, ItineraryDTO itineraryDTO);
    public void deleteById(UUID id);
    public List<ItineraryDTO> getAllItinerary(List<ItineraryItem> itineraryItemList);
    public ItineraryDTO update(ItineraryDTO itineraryDTO, UUID entity);
}
