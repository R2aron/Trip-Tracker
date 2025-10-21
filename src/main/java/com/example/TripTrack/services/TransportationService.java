package com.example.TripTrack.services;

import com.example.TripTrack.dto.TransportationDTO;
import com.example.TripTrack.entities.Transportation;
import com.example.TripTrack.mappers.TransportationMapper;
import com.example.TripTrack.repositories.TransportationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransportationService {
    @Autowired
    TransportationRepository transportationRepository;
    @Autowired
    TransportationMapper transportationMapper;

    public List<TransportationDTO> findAll()
    {
        return TransportationMapper.toDtoList(transportationRepository.findAll());
    }

    public TransportationDTO getTransportationDtoById(UUID id)
    {
        return new TransportationDTO(transportationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transportation not found")));
    }

    //metoda findById care returneaza o entitate

    public Transportation findTransportationById(UUID id)
    {
        return transportationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transportation not found"));
    }


    public TransportationDTO save(Transportation transportation)
    {
        return new TransportationDTO(transportationRepository.save(transportation));
    }

    public void deleteById(UUID id)
    {
        transportationRepository.deleteById(id);
    }

    public List<TransportationDTO> getAllTransportation(List<Transportation> transportation)
    {
        List<TransportationDTO> transportationDTOList = TransportationMapper.toDtoList(transportation);
        return transportationDTOList;
    }

    public TransportationDTO update(TransportationDTO transportationDTO, UUID id)
    {
        Transportation entityToUpdate = findTransportationById(id);//apelare metoda findById de mai sus.
        Transportation transportation = transportationMapper.updateEntityFromDto(transportationDTO,entityToUpdate);
        transportationRepository.save(transportation);
        TransportationDTO transportationDTO1 = new TransportationDTO(transportation);
        return transportationDTO1;
    }

}
