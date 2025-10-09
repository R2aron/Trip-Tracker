package com.example.TripTrack.controllers;

import com.example.TripTrack.dto.*;
import com.example.TripTrack.entities.Trip;
import com.example.TripTrack.services.AccomodationService;
import com.example.TripTrack.services.ItineraryService;
import com.example.TripTrack.services.TransportationService;
import com.example.TripTrack.services.TripService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/trips")
public class TripController {

    @Autowired
    TripService tripService;
    @Autowired
    ItineraryService itineraryService;
    @Autowired
    AccomodationService accomodationService;
    @Autowired
    TransportationService transportationService;

    @PostMapping
    public ResponseEntity<TripDTO> createTrip(@Valid @RequestBody TripDTO dto)
    {
        Trip toSave = tripService.createFromDto(dto);

        return ResponseEntity.ok(tripService.toDto(toSave));
    }

    @GetMapping
    public ResponseEntity<List<TripDTO>> getTrips()
    {
        return ResponseEntity.ok(tripService.findAll().stream().map(tripService:: toDto).collect(Collectors.toList()));
    }

    @GetMapping("/lightResponse")
    public ResponseEntity<List<LightResponseDTO>> lightResponseTrips()
    {
        return ResponseEntity.ok(tripService.findAll().stream().map(tripService::createLightResponseDTO).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LightResponseDTO> getTripById(@PathVariable UUID id)
    {
        Optional<Trip> tripOptional = tripService.findById(id);

        return tripOptional.map(trip -> ResponseEntity.ok(tripService.createLightResponseDTO(trip)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //de gandit cum sa aduc toate updaturile.
    @PutMapping("/")
    public ResponseEntity<AccommodationDTO> updateAccomodation(@RequestBody AccommodationDTO dto, @PathVariable UUID id)
    {
        return ResponseEntity.ok(accomodationService.update(dto,id));
    }

    @PutMapping
    public ResponseEntity<TripDTO> updateTrip(@RequestBody TripDTO dto)
    {
        Trip toUpdate = tripService.createFromDto(dto);
        Trip updatedTrip = tripService.update(toUpdate);

        return ResponseEntity.ok(tripService.toDto(updatedTrip));
    }//nu este bine facuta metodas // de refacut update : https://www.geeksforgeeks.org/java/spring-boot-crud-operations/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable UUID id)
    {
        tripService.deleteById(id);
       return ResponseEntity.noContent().build();
    }

    @GetMapping("/findTripsXDaysLonger{days}")
    public ResponseEntity<List<TripDTO>> getTripsLongerThanDays(@PathVariable int days)
    {
        List<Trip> listTripOptional = tripService.findByDaysGreaterThan(days)
                .orElseThrow(() -> new RuntimeException("Trips longer than " + days + " does not exists"));
        List<TripDTO> tripsDTO = listTripOptional.stream().map(tripService::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(tripsDTO);
    }// de vazut cum scot mesajul de eroare

    @GetMapping("/findByLocation/{destination}")
    public ResponseEntity<List<TripDTO>> findByLocation(@PathVariable String destination)
    {
        List<Trip> lLocations = tripService.findByDestination(destination)
                .orElseThrow(() -> new RuntimeException("No trip with location: "+ destination));
        List<TripDTO> locationsDto = lLocations.stream().map(tripService::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(locationsDto);
    }

    @GetMapping("/{id}/accomodation")
    public ResponseEntity<List<AccommodationDTO>> getAccomodations(@PathVariable UUID id)
    {
        Trip trip = tripService.findById(id)
                .orElseThrow( () -> new RuntimeException("Trip not found with id: " + id));
        List<AccommodationDTO> accommodationDTOList = accomodationService.getAllAccomodations(trip.getAccommodations());

        return ResponseEntity.ok(accommodationDTOList);
    }

    @GetMapping("/{id}/itinerary")
    public ResponseEntity<List<ItineraryDTO>> getItinerary(@PathVariable UUID id)
    {
        Trip trip = tripService.findById(id)
                .orElseThrow( () -> new RuntimeException("Trip not found with id: " + id));
        List<ItineraryDTO> itineraryDTOList = itineraryService.getAllItinerary(trip.getItineraryItems());

        return ResponseEntity.ok(itineraryDTOList);
    }

    @GetMapping("/{id}/transportation")
    public ResponseEntity<List<TransportationDTO>> getTransportation(@PathVariable UUID id)
    {
        Trip trip = tripService.findById(id)
                .orElseThrow( () -> new RuntimeException("Trip not found with id: " + id));
        List<TransportationDTO> transportationDTOList = transportationService.getAllTransportation(trip.getTransportation());

        return ResponseEntity.ok(transportationDTOList);
    }
//    ---------------------------------------------------------------------


//    @PostMapping("/itinerary")
//    public ResponseEntity<ItineraryDTO> create itinerary(@RequestBody ItineraryDTO dto)
//    {
//        ItineraryItem itemToSave = itineraryService.createFromDto(dto);
//        return ResponseEntity.ok(itineraryService.toDto(itemToSave));
//    }

//    @GetMapping("/itinerary")
//    public  ResponseEntity<List<ItineraryDTO>> getAll()
//    {
//        List<ItineraryDTO> itineraryList= itineraryService.findAll().stream().map(itineraryService::toDto).collect(Collectors.toList());
//        return ResponseEntity.ok(itineraryList);
//    }
//
//    @GetMapping("/itinerary")
//    public ResponseEntity<ItineraryDTO> getById(UUID id)
//    {
//        ItineraryItem item = itineraryService.findByID(id)
//                .orElseThrow(() -> new RuntimeException("Item not found"));
//        //gresit
//    }



}
