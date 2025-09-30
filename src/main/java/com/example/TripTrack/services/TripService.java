package com.example.TripTrack.services;

import com.example.TripTrack.dto.AccommodationDTO;
import com.example.TripTrack.dto.ItineraryDTO;
import com.example.TripTrack.dto.TransportationDTO;
import com.example.TripTrack.dto.TripDTO;
import com.example.TripTrack.entities.Accommodation;
import com.example.TripTrack.entities.ItineraryItem;
import com.example.TripTrack.entities.Transportation;
import com.example.TripTrack.entities.Trip;
import com.example.TripTrack.mappers.AccomodationMapper;
import com.example.TripTrack.mappers.ItineraryItemMapper;
import com.example.TripTrack.mappers.TransportationMapper;
import com.example.TripTrack.repositories.ItineraryRepository;
import com.example.TripTrack.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TripService {
    @Autowired
    TripRepository tripRepository;
    @Autowired
    ItineraryRepository itineraryRepository;

    public List<Trip> findAll()
    {
        return tripRepository.findAll();
    }

    public Optional<Trip> findById(UUID id)
    {
        return tripRepository.findById(id);
    }

    public Trip save(Trip trip)
    {
        return tripRepository.save(trip);
    }

    public void deleteById(UUID id)
    {
        tripRepository.deleteById(id);
    }

    public Optional<List<Trip>> findByDaysGreaterThan(int numberOfDays)
    {
        return tripRepository.findByDaysGreaterThan(numberOfDays);

    }

    public Optional<List<Trip>> findByDestination(String destination)
    {
        return tripRepository.findAllByDestination(destination);
    }

    // treuie sa primeasca un tripDto, de modificat la liste
    public Trip update(Trip trip)
    {
        Trip toUpdate = tripRepository.findById(trip.getId())
                        .orElseThrow(() -> new RuntimeException("Trip not found"));

        toUpdate.setId(trip.getId());
        toUpdate.setName(trip.getName());
        toUpdate.setDestination(trip.getDestination());
        toUpdate.setDays(trip.getDays());
        toUpdate.setStartOfTrip(trip.getStartOfTrip());

        toUpdate.setItineraryItems(trip.getItineraryItems());
        toUpdate.setAccommodations(trip.getAccommodations());
        toUpdate.setTransportation(trip.getTransportation());

        return tripRepository.save(toUpdate);


    }//revizuire

//https://chatgpt.com/share/68d93c80-0d9c-8001-936f-748a4a641020

    public TripDTO toDto(Trip trip)
    {
        TripDTO dto = new TripDTO();
        dto.dtoSetter(trip);
//        dto.setId(trip.getId());
//        dto.setName(trip.getName());
//        dto.setDestination(trip.getDestination());
//        dto.setDays(trip.getDays());
//        dto.setStartOfTrip(trip.getStartOfTrip());

        //cred ca ar trebui sa fac metode in services pentru setarea acestor liste de DTOs.
        if(trip.getItineraryItems() != null && !trip.getItineraryItems().isEmpty())
        {
//            List<ItineraryDTO> itineraryDTOList = trip.getItineraryItems().stream()
//                    .map(itineraryItem -> {
//                ItineraryDTO itin = new ItineraryDTO(itineraryItem);// mai scurt cu constructor
//                return itin;
//            }).collect(Collectors.toList());
//            dto.setItineraryDTOS(itineraryDTOList);
            List<ItineraryDTO> dtoList = ItineraryItemMapper.toDto(trip.getItineraryItems());
            dto.setItineraryDTOS(dtoList);
        }
//            dto.setItineraryDTOS(trip.getItineraryItems());


        if(trip.getTransportation() != null && !trip.getTransportation().isEmpty()) {
            List<TransportationDTO> transportationDTOList = TransportationMapper.toDto(trip.getTransportation());
            dto.setTransportationDTOS(transportationDTOList);
        }
//            dto.setTransportationDTOS(trip.getTransportation());

        if(trip.getAccommodations() != null && !trip.getAccommodations().isEmpty())
        {
            List<AccommodationDTO> accommodationDTOList = AccomodationMapper.toDto(trip.getAccommodations());
            dto.setAccommodationDTOS(accommodationDTOList);
        }
//            dto.setAccommodationDTOS(trip.getAccommodations());

        return dto;
    }

    public Trip createFromDto(TripDTO dto)
    {
        Trip trip = new Trip(dto);
//        trip.setDestination(dto.getDestination());
//        trip.setDays(dto.getDays());
//        trip.setName(dto.getName());
//        trip.setStartOfTrip(dto.getStartOfTrip());


        if(dto.getItineraryDTOS() != null && !dto.getItineraryDTOS().isEmpty())
        {
            //de verificat daca trebuie pus acest parent si in ItineraryDTO
            //de facut metoda

//            List<ItineraryItem> items = dto.getItineraryDTOS().stream()
//                    .filter(Objects::nonNull)
//                    .map(itm -> {
//                        ItineraryItem newItem = new ItineraryItem(itm);
//                        newItem.setParent(trip);//de verificat daca trebuie pus acest parent si in ItineraryDTO
//                        return newItem;
//                            }).collect(Collectors.toList());
//            trip.setItineraryItems(items);

            List<ItineraryItem> items = ItineraryItemMapper.entityfromDto(dto.getItineraryDTOS());
            trip.setItineraryItems(items);
        }

        if(dto.getAccommodationDTOS() != null && !dto.getAccommodationDTOS().isEmpty())
        {
//            List<Accommodation> listToSave = dto.getAccommodationDTOS().stream()
//                    .filter(Objects::nonNull)
//                    .map(booking -> {
//                        Accommodation toSave = new Accommodation(booking);
//                        toSave.setParent(trip);
//                        return  toSave;
//                    }).collect(Collectors.toList());
//            trip.setAccommodations(listToSave);
            List<Accommodation> accommodationList = AccomodationMapper.entityfromDto(dto.getAccommodationDTOS());
            trip.setAccommodations(accommodationList);
        }

        if(dto.getTransportationDTOS() != null && !dto.getTransportationDTOS().isEmpty())
        {
//            List<Transportation> listOfTransports = dto.getTransportationDTOS().stream()
//                    .filter(Objects::nonNull)
//                    .map(obj -> {
//                        Transportation transpToSave = new Transportation(obj);
//                        transpToSave.setParentTrip(trip);
//                        return transpToSave;
//                    }).collect(Collectors.toList());
//            trip.setTransportation(listOfTransports);

            List<Transportation> transportationList = TransportationMapper.entityfromDto(dto.getTransportationDTOS());
            trip.setTransportation(transportationList);
        }
        return tripRepository.save(trip);
    }
}
