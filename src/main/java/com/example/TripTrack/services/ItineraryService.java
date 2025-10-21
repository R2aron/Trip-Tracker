package com.example.TripTrack.services;

import com.example.TripTrack.dto.ItineraryDTO;
import com.example.TripTrack.entities.ItineraryItem;
import com.example.TripTrack.mappers.ItineraryItemMapper;
import com.example.TripTrack.repositories.ItineraryRepository;
import com.example.TripTrack.services.ServiceInterfaces.InineraryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItineraryService implements InineraryServiceInterface {
    @Autowired
    ItineraryRepository itineraryRepository;

    @Override
    public List<ItineraryDTO> findAll()
    {
        return ItineraryItemMapper.toDtoList(itineraryRepository.findAll());
    }

    @Override
    public ItineraryDTO getItineraryDtoById(UUID id)
    {
        return new ItineraryDTO(itineraryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transportation not found")));
    }

    //metoda findById care returneaza o entitate

    public ItineraryItem findItineraryById(UUID id)
    {
        return itineraryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transportation not found"));
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
        ItineraryItem entityToUpdate = findItineraryById(id);
        ItineraryItem itineraryItem = ItineraryItemMapper.updateEntityFromDto(itineraryDTO,entityToUpdate);
        itineraryRepository.save(itineraryItem);
        ItineraryDTO itineraryDTO1 = new ItineraryDTO(itineraryItem);
        return itineraryDTO1;
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
