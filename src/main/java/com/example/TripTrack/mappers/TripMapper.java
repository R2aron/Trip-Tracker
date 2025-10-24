package com.example.TripTrack.mappers;

import com.example.TripTrack.dto.*;
import com.example.TripTrack.entities.Accommodation;
import com.example.TripTrack.entities.ItineraryItem;
import com.example.TripTrack.entities.Transportation;
import com.example.TripTrack.entities.Trip;

import java.util.List;
import java.util.stream.Collectors;

public class TripMapper {

    public LightResponseDTO createLightResponseDTO(Trip trip)
    {
        LightResponseDTO lightResponseDTO = new LightResponseDTO(trip);
        return lightResponseDTO;
    }

    public static Trip createFromDto(TripDTO dto)
    {
        Trip trip = new Trip(dto);

        if(dto.getItineraryDTOS() != null && !dto.getItineraryDTOS().isEmpty())
        {
            //de verificat daca trebuie pus acest parent si in ItineraryDTO
            //de facut metoda
            List<ItineraryItem> items = ItineraryItemMapper.itineraryListFromDtos(dto.getItineraryDTOS());
            trip.setItineraryItems(items);
            trip.getItineraryItems().forEach(itineraryItem -> itineraryItem.setParent(trip));
        }

        if(dto.getAccommodationDTOS() != null && !dto.getAccommodationDTOS().isEmpty())
        {
            List<Accommodation> accommodationList = AccommodationMapper.accomodationListFromDtos(dto.getAccommodationDTOS());
            trip.setAccommodations(accommodationList);
            trip.getAccommodations().forEach(accommodation -> accommodation.setParent(trip));

        }

        if(dto.getTransportationDTOS() != null && !dto.getTransportationDTOS().isEmpty())
        {
            List<Transportation> transportationList = TransportationMapper.transportationListFromDtos(dto.getTransportationDTOS());
            trip.setTransportation(transportationList);
            trip.getTransportation().forEach(transportation -> transportation.setParent(trip));

        }
        return trip;
    }

    public static TripDTO toDto(Trip trip)
    {
        TripDTO dto = new TripDTO(trip);

        //cred ca ar trebui sa fac metode in services pentru setarea acestor liste de DTOs.
        if(trip.getItineraryItems() != null && !trip.getItineraryItems().isEmpty())
        {
            List<ItineraryDTO> dtoList = ItineraryItemMapper.toDtoList(trip.getItineraryItems());
            dto.setItineraryDTOS(dtoList);
        }

        if(trip.getTransportation() != null && !trip.getTransportation().isEmpty()) {
            List<TransportationDTO> transportationDTOList = TransportationMapper.toDtoList(trip.getTransportation());
            dto.setTransportationDTOS(transportationDTOList);
        }

        if(trip.getAccommodations() != null && !trip.getAccommodations().isEmpty())
        {
            List<AccommodationDTO> accommodationDTOList = AccommodationMapper.toDtoList(trip.getAccommodations());
            dto.setAccommodationDTOS(accommodationDTOList);
        }

        return dto;
    }

    public static List<TripDTO> toDtoList(List<Trip> tripList)
    {
        if(tripList == null || tripList.isEmpty())
            return List.of();
        List<TripDTO> tripDTOList = tripList.stream()
                .map(trip -> {
                    TripDTO tripDTO = new TripDTO(trip);
                    return  tripDTO;
                }).collect(Collectors.toList());
        return tripDTOList;
    }

    public static List<Trip> entityListfromDto(List<TripDTO> dtos)
    {
        List<Trip> tripList = dtos.stream()
                .map(tripDTO -> {
                    Trip trip  = new Trip(tripDTO);
                    return trip;
                }).collect(Collectors.toList());
        return tripList;
    }

    public static Trip updateEntityFromDto(TripDTO tripDTO,Trip entityToUpdate) {

        entityToUpdate.setName(tripDTO.getName());
        entityToUpdate.setDestination(tripDTO.getDestination());
        entityToUpdate.setDays(tripDTO.getDays());
        entityToUpdate.setStartOfTrip(tripDTO.getStartOfTrip());

        if(tripDTO.getItineraryDTOS() != null)
        {
            entityToUpdate.setItineraryItems(ItineraryItemMapper.itineraryListFromDtos(tripDTO.getItineraryDTOS()));
            entityToUpdate.getItineraryItems().forEach(itineraryItem -> itineraryItem.setParent(entityToUpdate));
        }
        if(tripDTO.getAccommodationDTOS() != null)
        {
            entityToUpdate.setAccommodations(AccommodationMapper.accomodationListFromDtos(tripDTO.getAccommodationDTOS()));
            entityToUpdate.getAccommodations().forEach(accommodation -> accommodation.setParent(entityToUpdate));
        }
        if(tripDTO.getTransportationDTOS() != null)
        {
            entityToUpdate.setTransportation(TransportationMapper.transportationListFromDtos(tripDTO.getTransportationDTOS()));
            entityToUpdate.getTransportation().forEach(transportation -> transportation.setParent(entityToUpdate));
        }


        return entityToUpdate;
    }

}
