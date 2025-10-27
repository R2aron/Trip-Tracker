package com.example.TripTrack.services;

import com.example.TripTrack.dto.TransportationDTO;
import com.example.TripTrack.entities.Transportation;
import com.example.TripTrack.entities.Trip;
import com.example.TripTrack.mappers.TransportationMapper;
import com.example.TripTrack.repositories.TransportationRepository;
import com.example.TripTrack.repositories.TripRepository;
import com.example.TripTrack.services.ServiceInterfaces.TransportationServiceInterface;
import com.example.TripTrack.services.ServiceInterfaces.UpdateTotalPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransportationService implements TransportationServiceInterface {
    @Autowired
    TransportationRepository transportationRepository;
    @Autowired
    TripRepository tripRepository;
    @Autowired
    UpdateTotalPrice updateTotalPrice;

    @Override
    public TransportationDTO save(UUID tripId, TransportationDTO dto)
    {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));
        Transportation transportation = new Transportation(dto);
        transportation.setParent(trip);
        transportationRepository.save(transportation);
        updateTotalPrice.updateTotalPrice(tripId);

        return new TransportationDTO(transportation);
    }

    @Override
    public List<Transportation> findAllByParentId(UUID tripId)
    {
        return transportationRepository.findAllByParentId(tripId);
    }

    @Override
    public TransportationDTO getById(UUID id)
    {
        return new TransportationDTO(findById(id));
    }

    @Override
    public Transportation findById(UUID id)
    {
        return transportationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transportation not found"));
    }



    @Override
    public void deleteById(UUID id)
    {
        UUID tripId = findById(id).getParent().getId();
        transportationRepository.deleteById(id);
        updateTotalPrice.updateTotalPrice(tripId);

    }

    @Override
    public List<TransportationDTO> getAllTransportationDto(List<Transportation> transportationList)
    {
        return TransportationMapper.toDtoList(transportationList);
    }

    @Override
    public TransportationDTO update(TransportationDTO transportationDTO, UUID id)
    {
        Transportation entityToUpdate = findById(id);
        transportationRepository.save(TransportationMapper.updateEntityFromDto(transportationDTO,entityToUpdate));
        updateTotalPrice.updateTotalPrice(entityToUpdate.getParent().getId());
        return new TransportationDTO(entityToUpdate);
    }

}
