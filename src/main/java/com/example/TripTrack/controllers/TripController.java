package com.example.TripTrack.controllers;

import com.example.TripTrack.dto.*;
import com.example.TripTrack.entities.Trip;
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

    @PostMapping
    public ResponseEntity<TripDTO> createTrip(@Valid @RequestBody TripDTO dto)

    {
        return ResponseEntity.ok(tripService.save(new Trip(dto)));
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
    public ResponseEntity<LightResponseDTO> getTripById(@PathVariable UUID id)
    {
        return ResponseEntity.ok(tripService.findLightResponseDtoById(id));
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

    @GetMapping("/{id}/accommodation")
    public ResponseEntity<List<AccommodationDTO>> getAllAccommodations(@PathVariable UUID id)
    {
        return ResponseEntity.ok(tripService.getAllAccommodationDto(id));
    }

    @GetMapping("/{id}/itinerary")
    public ResponseEntity<List<ItineraryDTO>> getItinerary(@PathVariable UUID id)
    {
        return ResponseEntity.ok(tripService.getAllItineraryDto(id));
    }

    @GetMapping("/{id}/transportation")
    public ResponseEntity<List<TransportationDTO>> getTransportation(@PathVariable UUID id)
    {
        return ResponseEntity.ok(tripService.getAllTransportationDto(id));
    }

    //de gandit cum sa aduc toate updaturile.
    @PutMapping("/{id}/updateAccommodation")
    public ResponseEntity<AccommodationDTO> updateAccommodation(@RequestBody @Valid AccommodationDTO dto, @PathVariable UUID id)
    {
        return ResponseEntity.ok(accommodationService.update(dto,id));
    }

    @PutMapping("/{id}/updateTransportation")
    public ResponseEntity<TransportationDTO> updateTransportation(@RequestBody @Valid TransportationDTO dto, @PathVariable UUID id)
    {
        return ResponseEntity.ok(transportationService.update(dto,id));
    }

    @PutMapping("/{id}/updateItinerary")
    public ResponseEntity<ItineraryDTO> updateItinerary(@RequestBody @Valid ItineraryDTO dto, @PathVariable UUID id)
    {
        return ResponseEntity.ok(itineraryService.update(dto,id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripDTO> updateTrip(@RequestBody @Valid  TripDTO dto, @PathVariable UUID id)
    {
        return ResponseEntity.ok(tripService.update(dto,id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable UUID id)
    {
        tripService.deleteById(id);
       return ResponseEntity.noContent().build();
    }

}
