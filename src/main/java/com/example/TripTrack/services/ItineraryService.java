package com.example.TripTrack.services;

import com.example.TripTrack.dto.ItineraryDTO;
import com.example.TripTrack.entities.ItineraryItem;
import com.example.TripTrack.mappers.ItineraryItemMapper;
import com.example.TripTrack.repositories.ItineraryRepository;
import com.example.TripTrack.services.ServiceInterfaces.ItineraryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItineraryService implements ItineraryServiceInterface {
    @Autowired
    ItineraryRepository itineraryRepository;

    @Override
    public List<ItineraryDTO> findAll()
    {
        return ItineraryItemMapper.toDtoList(itineraryRepository.findAll());
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
    public ItineraryDTO save(ItineraryItem itineraryItem)
    {
        return new ItineraryDTO(itineraryRepository.save(itineraryItem));
    }

    @Override
    public void deleteById(UUID id)
    {
        itineraryRepository.deleteById(id);
    }

    @Override
    public List<ItineraryDTO> getAllItinerary(List<ItineraryItem> itineraryItemList)
    {
        List<ItineraryDTO> itineraryDTOList = ItineraryItemMapper.toDtoList(itineraryItemList);
        return itineraryDTOList;
    }

    @Override
    public ItineraryDTO update(ItineraryDTO itineraryDTO, UUID id)
    {
        ItineraryItem entityToUpdate = findById(id);
        ItineraryItem itineraryItem = ItineraryItemMapper.updateEntityFromDto(itineraryDTO,entityToUpdate);
        return new ItineraryDTO(itineraryRepository.save(itineraryItem));
    }
}
