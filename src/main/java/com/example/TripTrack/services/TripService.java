package com.example.TripTrack.services;

import com.example.TripTrack.dto.TripDTO;
import com.example.TripTrack.entities.Accommodation;
import com.example.TripTrack.entities.ItineraryItem;
import com.example.TripTrack.entities.Transportation;
import com.example.TripTrack.entities.Trip;
import com.example.TripTrack.repositories.ItineraryRepository;
import com.example.TripTrack.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public TripDTO toDto(Trip trip)
    {
        TripDTO dto = new TripDTO();
        dto.setId(trip.getId());
        dto.setName(trip.getName());
        dto.setDestination(trip.getDestination());
        dto.setDays(trip.getDays());
        dto.setStartOfTrip(trip.getStartOfTrip());

        if(trip.getItineraryItems() != null && !trip.getItineraryItems().isEmpty())
            dto.setItineraryItems(trip.getItineraryItems());

        if(trip.getTransportation() != null && !trip.getTransportation().isEmpty())
            dto.setTransportations(trip.getTransportation());

        if(trip.getAccommodations() != null && !trip.getAccommodations().isEmpty())
            dto.setAccommodations(trip.getAccommodations());

        return dto;
    }

    public Trip createFromDto(TripDTO dto)
    {
        Trip trip = new Trip();
        trip.setDestination(dto.getDestination());
        trip.setDays(dto.getDays());
        trip.setName(dto.getName());
        trip.setStartOfTrip(dto.getStartOfTrip());


        if(dto.getItineraryItems() != null && !dto.getItineraryItems().isEmpty())
        {
            //de facut metoda
            List<ItineraryItem> items = dto.getItineraryItems().stream()
                    .filter(Objects::nonNull)
                    .map(itm -> {
                        ItineraryItem newItem = new ItineraryItem();
                        newItem.setLocation(itm.getLocation());
                        newItem.setDate(itm.getDate());
                        newItem.setTime(itm.getTime());
                        newItem.setPrice(itm.getPrice());
                        newItem.setParent(trip);
                        return newItem;
                            }).collect(Collectors.toList());
            trip.setItineraryItems(items);
        }

        if(dto.getAccommodations() != null && !dto.getAccommodations().isEmpty())
        {
            List<Accommodation> listToSave = dto.getAccommodations().stream()
                    .filter(Objects::nonNull)
                    .map(booking -> {
                        Accommodation toSave = new Accommodation();
                        toSave.setName(booking.getName());
                        toSave.setLocation(booking.getLocation());
                        toSave.setCheckIn(booking.getCheckIn());
                        toSave.setCheckOut(booking.getCheckOut());
                        toSave.setPrice(booking.getPrice());
                        toSave.setAddress(booking.getAddress());
                        toSave.setParent(trip);
                        return  toSave;
                    }).collect(Collectors.toList());

            trip.setAccommodations(listToSave);
        }

        if(dto.getTransportations() != null && !dto.getTransportations().isEmpty())
        {
            List<Transportation> listOfTransports = dto.getTransportations().stream()
                    .filter(Objects::nonNull)
                    .map(obj -> {
                        Transportation transpToSave = new Transportation();
                        transpToSave.setCategory(obj.getCategory());
                        transpToSave.setRoute(obj.getRoute());
                        transpToSave.setDistance(obj.getDistance());
                        transpToSave.setPrice(obj.getPrice());
                        transpToSave.setParentTrip(trip);
                        return transpToSave;
                    }).collect(Collectors.toList());
            trip.setTransportation(listOfTransports);
        }


        return tripRepository.save(trip);
    }
}
