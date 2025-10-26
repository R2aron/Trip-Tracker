package com.example.TripTrack.dto;

import com.example.TripTrack.entities.Trip;
import com.example.TripTrack.mappers.AccommodationMapper;
import com.example.TripTrack.mappers.ItineraryItemMapper;
import com.example.TripTrack.mappers.TransportationMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TripDTO extends BaseTripDTO {

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
//    private UUID id;
//    @NotBlank(message = "Trip name cannot be blank")
//    private String name;
//    @NotNull
//    private String destination;
//    @NotNull
//    @Min(1)
//    private Integer days;
//    @NotNull
//    private LocalDateTime startOfTrip;

    @Valid
    @NotEmpty
    private List<ItineraryDTO> itineraryDTOS;

    @Valid
    @NotNull
    private  List<AccommodationDTO> accommodationDTOS;

    @Valid
    @NotEmpty
    private List<TransportationDTO> transportationDTOS;

    public TripDTO(Trip trip)
    {
//        this.id = trip.getId();
//        this.name = trip.getName();
//        this.destination = trip.getDestination();
//        this.days = trip.getDays();
//        this.startOfTrip = trip.getStartOfTrip();

        super(trip);

        if(trip.getItineraryItems() != null && !trip.getItineraryItems().isEmpty())
        {
            List<ItineraryDTO> dtoList = ItineraryItemMapper.toDtoList(trip.getItineraryItems());
            this.setItineraryDTOS(dtoList);
        }

        if(trip.getTransportation() != null && !trip.getTransportation().isEmpty()) {
            List<TransportationDTO> transportationDTOList = TransportationMapper.toDtoList(trip.getTransportation());
            this.setTransportationDTOS(transportationDTOList);
        }

        if(trip.getAccommodations() != null && !trip.getAccommodations().isEmpty())
        {
            List<AccommodationDTO> accommodationDTOList = AccommodationMapper.toDtoList(trip.getAccommodations());
            this.setAccommodationDTOS(accommodationDTOList);
        }
    }

}