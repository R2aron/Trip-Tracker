package com.example.TripTrack.controllers;

import com.example.TripTrack.dto.*;
import com.example.TripTrack.services.ServiceInterfaces.AccommodationServiceInterface;
import com.example.TripTrack.services.ServiceInterfaces.ItineraryServiceInterface;
import com.example.TripTrack.services.ServiceInterfaces.TransportationServiceInterface;
import com.example.TripTrack.services.ServiceInterfaces.TripServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    TripServiceInterface tripService;
    @Autowired
    ItineraryServiceInterface itineraryService;
    @Autowired
    AccommodationServiceInterface accommodationService;
    @Autowired
    TransportationServiceInterface transportationService;


//     ====Trip====

    @PostMapping("/users/{userId}/createFullTrip")
    public ResponseEntity<TripDTO> createTrip(@PathVariable UUID userId, @Valid @RequestBody TripDTO dto)
    {
        return ResponseEntity.ok(tripService.save(userId,dto));
    }

    @PostMapping("/users/{userId}/createBaseTrip")
    public ResponseEntity<TripDTO> createLightTrip(@PathVariable UUID userId, @Valid @RequestBody BaseTripDTO dto)
    {
        return ResponseEntity.ok(tripService.save(userId,dto));
    }

    @GetMapping
    public ResponseEntity<List<TripDTO>> getTrips()
    {
        return ResponseEntity.ok(tripService.findAll());
    }

    @GetMapping("/lightResponse")
    public ResponseEntity<List<LightResponseDTO>> lightResponseTrips()
    {
        return ResponseEntity.ok(tripService.getLightResponseDtoList());
    }

    @GetMapping("/lightResponse/{id}")
    public ResponseEntity<LightResponseDTO> getLightTripById(@PathVariable UUID id)
    {
        return ResponseEntity.ok(tripService.findLightResponseDtoById(id));
    }

    @GetMapping("/FullResponse/{id}")
    public ResponseEntity<TripDTO> getFullTripById(@PathVariable UUID id)
    {
        return ResponseEntity.ok(tripService.findById(id));
    }

    @GetMapping("/findTripsXDaysLonger/{days}")
    public ResponseEntity<List<TripDTO>> getTripsLongerThanDays(@PathVariable int days) {
        return ResponseEntity.ok(tripService.findByDaysGreaterThan(days));
    }

    @GetMapping("/findTripsLessThanXDays/{days}")
    public ResponseEntity<List<TripDTO>> getTripsLessThanDays(@PathVariable int days)
    {
        return ResponseEntity.ok(tripService.findByDaysLessThanEqual(days));
    }

    @GetMapping("/findByLocation/{destination}")
    public ResponseEntity<List<TripDTO>> findByLocation(@PathVariable String destination)
    {
        return ResponseEntity.ok(tripService.findByDestination(destination));
    }

    @PutMapping("/{id}/updateTrip")
    public ResponseEntity<TripDTO> updateTrip(@RequestBody @Valid  TripDTO dto, @PathVariable UUID id)
    {
        return ResponseEntity.ok(tripService.update(dto,id));
    }


    @DeleteMapping("/{id}/deleteTrip")
    public ResponseEntity<Void> deleteTrip(@PathVariable UUID id)
    {
        tripService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

//    ===========================


//    ====ACCOMMODATION====

    @PostMapping("/{id}/createAccommodation")
    public ResponseEntity<AccommodationDTO> createAccommodation(@PathVariable UUID id, @Valid @RequestBody AccommodationDTO accommodationDTO)
    {
        return ResponseEntity.ok(accommodationService.save(id,accommodationDTO));
    }

    @GetMapping("/{id}/getAllAccommodation")
    public ResponseEntity<List<AccommodationDTO>> getAllAccommodations(@PathVariable UUID id)
    {
        return ResponseEntity.ok(tripService.getAllAccommodationDto(id));
    }

    @GetMapping("/{id}/getAccommodationById")
    public ResponseEntity<AccommodationDTO> getAccommodationById(@PathVariable UUID id)
    {
        return ResponseEntity.ok((accommodationService.getById(id)));
    }

    @PutMapping("/{id}/updateAccommodation")
    public ResponseEntity<AccommodationDTO> updateAccommodation(@RequestBody @Valid AccommodationDTO dto, @PathVariable UUID id)
    {
        return ResponseEntity.ok(accommodationService.update(dto,id));
    }

    @DeleteMapping("/{id}/deleteAccommodation")
    public ResponseEntity<Void> delete(@PathVariable UUID id)
    {
        accommodationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

//    ===========================



//     ====ITINERARY====

    @PostMapping("/{id}/createItineraryItem")
    ResponseEntity<ItineraryDTO>  createItinerary(@PathVariable UUID id, @Valid @RequestBody ItineraryDTO itineraryDTO)
    {
        return ResponseEntity.ok(itineraryService.save(id, itineraryDTO));
    }

    @GetMapping("/{id}/itinerary")
    public ResponseEntity<List<ItineraryDTO>> getItinerary(@PathVariable UUID id)
    {
        return ResponseEntity.ok(tripService.getAllItineraryDto(id));
    }

    @GetMapping("/{id}/getItineraryById")
    public ResponseEntity<ItineraryDTO> getItineraryById(@PathVariable UUID id)
    {
        return ResponseEntity.ok((itineraryService.getById(id)));
    }

    @PutMapping("/{id}/updateItinerary")
    public ResponseEntity<ItineraryDTO> updateItinerary(@RequestBody @Valid ItineraryDTO dto, @PathVariable UUID id)
    {
        return ResponseEntity.ok(itineraryService.update(dto,id));
    }

    @DeleteMapping("/{id}/deleteItinerary")
    public ResponseEntity<Void> deleteItinerary(@PathVariable UUID id)
    {
        itineraryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }



//    ===========================


//     ====Transportation====

    @PostMapping("/{id}/createTransportation")
    ResponseEntity<TransportationDTO>  createTransportation(@PathVariable UUID id, @Valid @RequestBody TransportationDTO transportationDTO)
    {
        return ResponseEntity.ok(transportationService.save(id, transportationDTO));
    }

    @GetMapping("/{id}/transportation")
    public ResponseEntity<List<TransportationDTO>> getTransportation(@PathVariable UUID id)
    {
        return ResponseEntity.ok(tripService.getAllTransportationDto(id));
    }

    @GetMapping("/{id}/getTransportationById")
    public ResponseEntity<TransportationDTO> getTransportationById(@PathVariable UUID id)
    {
        return ResponseEntity.ok((transportationService.getById(id)));
    }

    @PutMapping("/{id}/updateTransportation")
    public ResponseEntity<TransportationDTO> updateTransportation(@RequestBody @Valid TransportationDTO dto, @PathVariable UUID id)
    {
        return ResponseEntity.ok(transportationService.update(dto,id));
    }

    @DeleteMapping("/{id}/deleteTransportation")
    public ResponseEntity<Void> deleteTransportation(@PathVariable UUID id)
    {
        transportationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

//    ===========================
}
