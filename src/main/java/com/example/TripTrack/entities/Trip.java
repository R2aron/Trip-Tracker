package com.example.TripTrack.entities;

import com.example.TripTrack.dto.TripDTO;
import com.example.TripTrack.mappers.AccommodationMapper;
import com.example.TripTrack.mappers.ItineraryItemMapper;
import com.example.TripTrack.mappers.TransportationMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<ItineraryItem> itineraryItems;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private  List<Accommodation> accommodations;

    @OneToMany(mappedBy = "parentTrip", cascade = CascadeType.ALL)
    private List<Transportation> transportation;

//    @ManyToMany(mappedBy = "trips", fetch = FetchType.LAZY)
//    private Set<User> users = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "trips_users",
            joinColumns = @JoinColumn(name = "trip_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();


    public Trip(TripDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.destination = dto.getDestination();
        this.days = dto.getDays();
        this.startOfTrip = dto.getStartOfTrip();

        if(dto.getItineraryDTOS() != null && !dto.getItineraryDTOS().isEmpty())
        {
            List<ItineraryItem> items = ItineraryItemMapper.itineraryListFromDtos(dto.getItineraryDTOS());
            this.setItineraryItems(items);
            this.getItineraryItems().forEach(itineraryItem -> itineraryItem.setParent(this));
        }

        if(dto.getAccommodationDTOS() != null && !dto.getAccommodationDTOS().isEmpty())
        {
            List<Accommodation> accommodationList = AccommodationMapper.accomodationListFromDtos(dto.getAccommodationDTOS());
            this.setAccommodations(accommodationList);
            this.getAccommodations().forEach(accommodation -> accommodation.setParent(this));

        }

        if(dto.getTransportationDTOS() != null && !dto.getTransportationDTOS().isEmpty())
        {
            List<Transportation> transportationList = TransportationMapper.transportationListFromDtos(dto.getTransportationDTOS());
            this.setTransportation(transportationList);
            this.getTransportation().forEach(transportation -> transportation.setParentTrip(this));

        }
    }
}
