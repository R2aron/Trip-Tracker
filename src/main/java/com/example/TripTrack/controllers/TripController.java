package com.example.TripTrack.controllers;

import com.example.TripTrack.dto.ItineraryDTO;
import com.example.TripTrack.dto.TripDTO;
import com.example.TripTrack.entities.ItineraryItem;
import com.example.TripTrack.entities.Trip;
import com.example.TripTrack.services.ItineraryService;
import com.example.TripTrack.services.TripService;
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

    @PostMapping
    public ResponseEntity<TripDTO> createTrip(@RequestBody TripDTO dto)
    {
        Trip toSave = tripService.createFromDto(dto);

        return ResponseEntity.ok(tripService.toDto(toSave));
    }

    @GetMapping
    public ResponseEntity<List<TripDTO>> getTrips()
    {
        return ResponseEntity.ok(tripService.findAll().stream().map(tripService:: toDto).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripDTO> getTripById(@PathVariable UUID id)
    {
        Optional<Trip> tripOptional = tripService.findById(id);

        return tripOptional.map(trip -> ResponseEntity.ok(tripService.toDto(trip)))
                .orElseGet(() -> ResponseEntity.notFound().build());
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
