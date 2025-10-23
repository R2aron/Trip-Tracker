package com.example.TripTrack.entities;

import com.example.TripTrack.dto.TripDTO;
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

    @ManyToMany(mappedBy = "trips", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();


    public Trip(TripDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.destination = dto.getDestination();
        this.days = dto.getDays();
        this.startOfTrip = dto.getStartOfTrip();
        //de vazut daca este mai bine sa fac metoda in aici,comstructor, sau in mapper
    }
}
