package com.example.TripTrack.services.ServiceInterfaces;

import com.example.TripTrack.entities.Accommodation;
import com.example.TripTrack.entities.ItineraryItem;
import com.example.TripTrack.entities.Transportation;
import com.example.TripTrack.entities.Trip;
import com.example.TripTrack.repositories.AccommodationRepository;
import com.example.TripTrack.repositories.ItineraryRepository;
import com.example.TripTrack.repositories.TransportationRepository;
import com.example.TripTrack.repositories.TripRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@Component
@AllArgsConstructor
public class UpdateTotalPrice {

    private final TripRepository tripRepository;
    private final AccommodationRepository accommodationRepository;
    private final TransportationRepository transportationRepository;
    private final ItineraryRepository itineraryRepository;

    public void updateTotalPrice(UUID tripId)
    {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found!"));

        Double totalPrice = 0D;

        totalPrice += trip.getTotalPrice();

        List<Accommodation> accommodationList = accommodationRepository.findAllByParentId(tripId);
        if(accommodationList!= null)
            totalPrice += accommodationList.stream().filter(accommodation -> accommodation.getPrice() != null).mapToDouble(Accommodation::getPrice).sum();

        List<ItineraryItem> itineraryItemList = itineraryRepository.findAllByParentId(tripId);
        if(itineraryItemList!= null)
            totalPrice += itineraryItemList.stream().filter(itineraryItem -> itineraryItem.getPrice() != null).mapToDouble(ItineraryItem::getPrice).sum();

        List<Transportation> transportationList = transportationRepository.findAllByParentId(tripId);
        if( transportationList != null)
            totalPrice += transportationList.stream().filter(transportation -> transportation.getPrice() != null).mapToDouble(Transportation::getPrice).sum();

        trip.setTotalPrice(totalPrice);
        tripRepository.save(trip);
    }
}
