package com.example.TripTrack.services;

import com.example.TripTrack.dto.ItineraryDTO;
import com.example.TripTrack.entities.ItineraryItem;
import com.example.TripTrack.entities.Trip;
import com.example.TripTrack.mappers.ItineraryItemMapper;
import com.example.TripTrack.repositories.ItineraryRepository;
import com.example.TripTrack.repositories.TripRepository;
import com.example.TripTrack.services.ServiceInterfaces.ItineraryServiceInterface;
import com.example.TripTrack.services.ServiceInterfaces.UpdateTotalPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItineraryService implements ItineraryServiceInterface {
    @Autowired
    ItineraryRepository itineraryRepository;
    @Autowired
    TripRepository tripRepository;
    @Autowired
    UpdateTotalPrice updateTotalPrice;

    @Override
    public ItineraryDTO save(UUID tripId, ItineraryDTO dto)
    {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));
        ItineraryItem itineraryItem = new ItineraryItem(dto);
        itineraryItem.setParent(trip);
        itineraryRepository.save(itineraryItem);
        updateTotalPrice.updateTotalPrice(tripId);
        return new ItineraryDTO(itineraryItem);
    }

    @Override
    public List<ItineraryItem> findAll()
    {
        return itineraryRepository.findAll();
    }


    @Override
    public List<ItineraryItem> findAllByParentId(UUID tripId)
    {
        return itineraryRepository.findAllByParentId(tripId);
    }

    @Override
    public ItineraryDTO getById(UUID id)
    {
        return new ItineraryDTO(itineraryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Itinerary not found")));
    }

    @Override
    public ItineraryItem findById(UUID id)
    {
        return itineraryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Itinerary not found"));
    }


    @Override
    public void deleteById(UUID id)
    {
        itineraryRepository.deleteById(id);
        updateTotalPrice.updateTotalPrice(id);
    }

    @Override
    public List<ItineraryDTO> getAllItinerary(List<ItineraryItem> itineraryItemList)
    {
        return ItineraryItemMapper.toDtoList(itineraryItemList);
    }

    @Override
    public ItineraryDTO update(ItineraryDTO itineraryDTO, UUID id)
    {
        ItineraryItem itineraryToUpdate = findById(id);
        itineraryRepository.save(ItineraryItemMapper.updateEntityFromDto(itineraryDTO,itineraryToUpdate));
        updateTotalPrice.updateTotalPrice(id);
        return new ItineraryDTO(itineraryToUpdate);
    }
}
