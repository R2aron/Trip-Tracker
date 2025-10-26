package com.example.TripTrack.entities;

import com.example.TripTrack.dto.BaseTripDTO;
import com.example.TripTrack.dto.TripDTO;
import com.example.TripTrack.mappers.AccommodationMapper;
import com.example.TripTrack.mappers.ItineraryItemMapper;
import com.example.TripTrack.mappers.TransportationMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trips")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String destination;
    private Integer days;
    private LocalDateTime startOfTrip;
    private Double totalPrice = 0d;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItineraryItem> itineraryItems;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private  List<Accommodation> accommodations;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transportation> transportation;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;



    public <T extends BaseTripDTO> Trip(T dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.destination = dto.getDestination();
        this.days = dto.getDays();
        this.startOfTrip = dto.getStartOfTrip();
        this.totalPrice = dto.getTotalPrice();


        if(dto instanceof TripDTO tripDTO) {
            if (tripDTO.getItineraryDTOS() != null && !tripDTO.getItineraryDTOS().isEmpty()) {
                List<ItineraryItem> items = ItineraryItemMapper.itineraryListFromDtos(tripDTO.getItineraryDTOS());
                this.setItineraryItems(items);
                this.getItineraryItems().forEach(itineraryItem -> itineraryItem.setParent(this));
            }

            if (tripDTO.getAccommodationDTOS() != null && !tripDTO.getAccommodationDTOS().isEmpty()) {
                List<Accommodation> accommodationList = AccommodationMapper.accomodationListFromDtos(tripDTO.getAccommodationDTOS(),false);
                this.setAccommodations(accommodationList);
                this.getAccommodations().forEach(accommodation -> accommodation.setParent(this));

            }

            if (tripDTO.getTransportationDTOS() != null && !tripDTO.getTransportationDTOS().isEmpty()) {
                List<Transportation> transportationList = TransportationMapper.transportationListFromDtos(tripDTO.getTransportationDTOS());
                this.setTransportation(transportationList);
                this.getTransportation().forEach(transportation -> transportation.setParent(this));

            }
        }
    }
}
