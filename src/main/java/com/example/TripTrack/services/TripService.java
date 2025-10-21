package com.example.TripTrack.services;

import com.example.TripTrack.dto.*;
import com.example.TripTrack.entities.ItineraryItem;
import com.example.TripTrack.entities.Transportation;
import com.example.TripTrack.entities.Trip;
import com.example.TripTrack.mappers.TripMapper;
import com.example.TripTrack.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TripService {
    @Autowired
    TripRepository tripRepository;

    @Autowired
    AccomodationService accomodationService;

    @Autowired
    TransportationService transportationService;

    @Autowired
    ItineraryService itineraryService;

    public List<TripDTO> findAll() {
        return TripMapper.toDtoList(tripRepository.findAll());
    }

    public List<Trip> getAll()
    {
        return tripRepository.findAll();
    }

    public LightResponseDTO findLightResponseDtoById(UUID id)
    {
        return new LightResponseDTO(tripRepository.findById(id).orElseThrow(() -> new RuntimeException("Trip not found")));
    }

    public List<LightResponseDTO> getLightResponseDtoList()
    {
        List<LightResponseDTO> lightResponseDTOList = getAll().stream().map(entity -> {
            LightResponseDTO lightResponseDTO = new LightResponseDTO(entity);
            return lightResponseDTO;
        }).collect(Collectors.toList());

        return lightResponseDTOList;

    }

    public TripDTO findTripDtoById(UUID id) {
        return new TripDTO(tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found")));
    }

    public Trip getTripById(UUID id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found"));
    }

    public TripDTO save(Trip trip) {
        return TripMapper.toDto(tripRepository.save(trip));
    }

    public void deleteById(UUID id) {
        tripRepository.deleteById(id);
    }

    public List<TripDTO> findByDaysGreaterThan(int numberOfDays) {
        return TripMapper.toDtoList(tripRepository.findByDaysGreaterThan(numberOfDays)
                .orElseThrow(() -> new RuntimeException("No trips that long")));
    }

    public List<TripDTO> findByDestination(String destination) {
        return TripMapper.toDtoList(tripRepository.findAllByDestination(destination)
                .orElseThrow(() -> new RuntimeException("No trip with this destination")));
    }

    public List<TripDTO> findByDaysLessThanEqual(int days) {
        return TripMapper.toDtoList(tripRepository.findByDaysLessThanEqual(days)
                .orElseThrow(() -> new RuntimeException("No trip that short")));
//        de returnat dto
    }

    public List<AccommodationDTO> getAllAccomodationDto(UUID id)
    {
        Trip trip = getTripById(id);
        return accomodationService.getAllAccomodations(trip.getAccommodations());
    }

    public List<ItineraryDTO> getAllItineraryDto(UUID id)
    {
        Trip trip = getTripById(id);
        return itineraryService.getAllItinerary(trip.getItineraryItems());
    }

    public List<TransportationDTO> getAllTransportationDto(UUID id)
    {
        Trip trip = getTripById(id);
        return transportationService.getAllTransportation(trip.getTransportation());
    }


    public TripDTO update(TripDTO tripDTO, UUID id) {

        Trip tripToUpdate = getTripById(id);
        Trip tripUpdated = TripMapper.updateEntityFromDto(tripDTO,tripToUpdate);
        return new TripDTO(tripRepository.save(tripUpdated));


//        Trip toUpdate = tripRepository.findById(trip.getId())
//                .orElseThrow(() -> new RuntimeException("Trip not found"));
//
//        toUpdate.setId(trip.getId());
//        toUpdate.setName(trip.getName());
//        toUpdate.setDestination(trip.getDestination());
//        toUpdate.setDays(trip.getDays());
//        toUpdate.setStartOfTrip(trip.getStartOfTrip());
//
//        toUpdate.setItineraryItems(trip.getItineraryItems());
//        toUpdate.setAccommodations(trip.getAccommodations());
//        toUpdate.setTransportation(trip.getTransportation());
//
//        return tripRepository.save(toUpdate);
//

    }//revizuire

//https://chatgpt.com/share/68d93c80-0d9c-8001-936f-748a4a641020

}




