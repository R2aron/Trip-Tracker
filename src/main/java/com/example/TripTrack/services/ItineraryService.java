package com.example.TripTrack.services;

import com.example.TripTrack.dto.ItineraryDTO;
import com.example.TripTrack.entities.Accommodation;
import com.example.TripTrack.entities.ItineraryItem;
import com.example.TripTrack.mappers.ItineraryItemMapper;
import com.example.TripTrack.repositories.ItineraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItineraryService {
    @Autowired
    ItineraryRepository itineraryRepository;

    public List<ItineraryItem> findAll()
    {
        return itineraryRepository.findAll();
    }

    public Optional<ItineraryItem> findByID(UUID id)
    {
        return itineraryRepository.findById(id);
    }

    public ItineraryItem save(ItineraryItem itineraryItem)
    {
        return itineraryRepository.save(itineraryItem);
    }

    public void deleteById(UUID id)
    {
        itineraryRepository.deleteById(id);
    }

    public List<ItineraryDTO> getAllItinerary(List<ItineraryItem> itineraryItemList )
    {
        List<ItineraryDTO> itineraryDTOList = ItineraryItemMapper.toDto(itineraryItemList);
        return itineraryDTOList;
    }

//    public ItineraryDTO toDto(ItineraryItem itineraryItem)
//    {
//        ItineraryDTO dto = new ItineraryDTO();
//        dto.setId(itineraryItem.getId());
//        dto.setDate(itineraryItem.getDate());
//        dto.setTime(itineraryItem.getTime());
//        dto.setLocation(itineraryItem.getLocation());
//        dto.setPrice(itineraryItem.getPrice());
//
//        return dto;
//    }
//
//    public ItineraryItem createFromDto(ItineraryDTO dto)
//    {
//        ItineraryItem item = new ItineraryItem();
//        item.setId(dto.getId());
//        item.setDate(dto.getDate());
//        item.setTime(dto.getTime());
//        item.setLocation(dto.getLocation());
//        item.setPrice(dto.getPrice());
//
//        return itineraryRepository.save(item);
//    }
}
