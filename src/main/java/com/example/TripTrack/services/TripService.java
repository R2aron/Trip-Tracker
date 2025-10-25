package com.example.TripTrack.services;

import com.example.TripTrack.dto.*;
import com.example.TripTrack.entities.Trip;
import com.example.TripTrack.mappers.TripMapper;
import com.example.TripTrack.repositories.TripRepository;
import com.example.TripTrack.services.ServiceInterfaces.TripServiceInterface;
import com.example.TripTrack.services.ServiceInterfaces.UpdateTotalPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TripService implements TripServiceInterface {
    @Autowired
    TripRepository tripRepository;
    @Autowired
    AccommodationService accommodationService;
    @Autowired
    TransportationService transportationService;
    @Autowired
    ItineraryService itineraryService;
    @Autowired
    UpdateTotalPrice updateTotalPrice;


    @Override
    public TripDTO save(Trip trip) {
        tripRepository.save(trip);
        updateTotalPrice.updateTotalPrice(trip.getId());
        return new TripDTO(tripRepository.save(trip));
    }

    @Override
    public <T extends BaseTripDto> TripDTO save(T dto)
    {
        Trip trip = new Trip(dto);
        tripRepository.save(trip);
        updateTotalPrice.updateTotalPrice(trip.getId());
        return new TripDTO(tripRepository.save(trip));
    }


    @Override
    public List<TripDTO> findAll() {
        return TripMapper.toDtoList(tripRepository.findAll());
    }

    @Override
    public List<Trip> getAll()
    {
        return tripRepository.findAll();
    }

    @Override
    public LightResponseDTO findLightResponseDtoById(UUID id)
    {
        return new LightResponseDTO(tripRepository.findById(id).orElseThrow(() -> new RuntimeException("Trip not found")));
    }

    @Override
    public List<LightResponseDTO> getLightResponseDtoList()
    {
        List<LightResponseDTO> lightResponseDTOList = getAll().stream().map(entity -> {
            LightResponseDTO lightResponseDTO = new LightResponseDTO(entity);
            return lightResponseDTO;
        }).collect(Collectors.toList());

        return lightResponseDTOList;

    }

    @Override
    public TripDTO findById(UUID id) {
        return new TripDTO(tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found")));
    }

    @Override
    public Trip getById(UUID id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found"));
    }

    @Override
    public void deleteById(UUID id) {
        tripRepository.deleteById(id);
        updateTotalPrice.updateTotalPrice(id);
    }

    @Override
    public List<TripDTO> findByDaysGreaterThan(int numberOfDays) {
        return TripMapper.toDtoList(tripRepository.findByDaysGreaterThan(numberOfDays)
                .orElseThrow(() -> new RuntimeException("No trips that long")));
    }

    @Override
    public List<TripDTO> findByDestination(String destination) {
        return TripMapper.toDtoList(tripRepository.findAllByDestination(destination)
                .orElseThrow(() -> new RuntimeException("No trip with this destination")));
    }

    @Override
    public List<TripDTO> findByDaysLessThanEqual(int days) {
        return TripMapper.toDtoList(tripRepository.findByDaysLessThanEqual(days)
                .orElseThrow(() -> new RuntimeException("No trip that short")));
    }

    @Override
    public List<AccommodationDTO> getAllAccommodationDto(UUID id)
    {
        Trip trip = getById(id);
        return accommodationService.getAllAccomodations(trip.getAccommodations());
    }

    @Override
    public List<ItineraryDTO> getAllItineraryDto(UUID id)
    {
        Trip trip = getById(id);
        return itineraryService.getAllItinerary(trip.getItineraryItems());
    }

    @Override
    public List<TransportationDTO> getAllTransportationDto(UUID id)
    {
        Trip trip = getById(id);
        return transportationService.getAllTransportationDto(trip.getTransportation());
    }


    @Override
    public TripDTO update(TripDTO tripDTO, UUID id) {

        Trip tripToUpdate = getById(id);
        tripRepository.save(TripMapper.updateEntityFromDto(tripDTO,tripToUpdate));
        updateTotalPrice.updateTotalPrice(id);
        return new TripDTO(tripToUpdate);
    }
}




