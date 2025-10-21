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
import java.util.UUID;

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
        Trip toSave = new Trip(dto);
        return ResponseEntity.ok(new TripDTO(toSave));
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

    //de gandit cum sa aduc toate updaturile.
    @PutMapping("/updateAccomodation")
    public ResponseEntity<AccommodationDTO> updateAccomodation(@RequestBody @Valid AccommodationDTO dto, @PathVariable UUID id)
    {
        return ResponseEntity.ok(accomodationService.update(dto,id));
    }

    @PutMapping("/updateTransportation")
    public ResponseEntity<TransportationDTO> updateTransportation(@RequestBody @Valid TransportationDTO dto, @PathVariable UUID id)
    {
        return ResponseEntity.ok(transportationService.update(dto,id));
    }

    @PutMapping("/updateItinerary")
    public ResponseEntity<ItineraryDTO> updateItinerary(@RequestBody @Valid ItineraryDTO dto, @PathVariable UUID id)
    {
        return ResponseEntity.ok(itineraryService.update(dto,id));
    }

    @PutMapping
    public ResponseEntity<TripDTO> updateTrip(@RequestBody @Valid  TripDTO dto, @PathVariable UUID id)
    {
        return ResponseEntity.ok(tripService.update(dto,id));
    }//nu este bine facuta metodas // de refacut update : https://www.geeksforgeeks.org/java/spring-boot-crud-operations/


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable UUID id)
    {
        tripService.deleteById(id);
       return ResponseEntity.noContent().build();
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

    @GetMapping("/{id}/accomodation")
    public ResponseEntity<List<AccommodationDTO>> getAccomodations(@PathVariable UUID id)
    {
//        return ResponseEntity.ok(tripService.find)
        return ResponseEntity.ok(tripService.getAllAccomodationDto(id));
    }

    @GetMapping("/{id}/itinerary")
    public ResponseEntity<List<ItineraryDTO>> getItinerary(@PathVariable UUID id)
    {
        return ResponseEntity.ok(tripService.getAllItinerary(id));
    }

    @GetMapping("/{id}/transportation")
    public ResponseEntity<List<TransportationDTO>> getTransportation(@PathVariable UUID id)
    {
        return ResponseEntity.ok(tripService.getAllTransportationDto(id));
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
