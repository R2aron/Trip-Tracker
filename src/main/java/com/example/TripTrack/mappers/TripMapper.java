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
//        trip.setDestination(dto.getDestination());
//        trip.setDays(dto.getDays());
//        trip.setName(dto.getName());
//        trip.setStartOfTrip(dto.getStartOfTrip());


        if(dto.getItineraryDTOS() != null && !dto.getItineraryDTOS().isEmpty())
        {
            //de verificat daca trebuie pus acest parent si in ItineraryDTO
            //de facut metoda

//            List<ItineraryItem> items = dto.getItineraryDTOS().stream()
//                    .filter(Objects::nonNull)
//                    .map(itm -> {
//                        ItineraryItem newItem = new ItineraryItem(itm);
//                        newItem.setParent(trip);//de verificat daca trebuie pus acest parent si in ItineraryDTO
//                        return newItem;
//                            }).collect(Collectors.toList());
//            trip.setItineraryItems(items);

            List<ItineraryItem> items = ItineraryItemMapper.itineraryListFromDtos(dto.getItineraryDTOS());
            trip.setItineraryItems(items);
            trip.getItineraryItems().forEach(itineraryItem -> itineraryItem.setParent(trip));
        }

        if(dto.getAccommodationDTOS() != null && !dto.getAccommodationDTOS().isEmpty())
        {
//            List<Accommodation> listToSave = dto.getAccommodationDTOS().stream()
//                    .filter(Objects::nonNull)
//                    .map(booking -> {
//                        Accommodation toSave = new Accommodation(booking);
//                        toSave.setParent(trip);
//                        return  toSave;
//                    }).collect(Collectors.toList());
//            trip.setAccommodations(listToSave);
            List<Accommodation> accommodationList = AccommodationMapper.accomodationListFromDtos(dto.getAccommodationDTOS());
            trip.setAccommodations(accommodationList);
            trip.getAccommodations().forEach(accommodation -> accommodation.setParent(trip));

        }

        if(dto.getTransportationDTOS() != null && !dto.getTransportationDTOS().isEmpty())
        {
//            List<Transportation> listOfTransports = dto.getTransportationDTOS().stream()
//                    .filter(Objects::nonNull)
//                    .map(obj -> {
//                        Transportation transpToSave = new Transportation(obj);
//                        transpToSave.setParentTrip(trip);
//                        return transpToSave;
//                    }).collect(Collectors.toList());
//            trip.setTransportation(listOfTransports);

            List<Transportation> transportationList = TransportationMapper.transportationListFromDtos(dto.getTransportationDTOS());
            trip.setTransportation(transportationList);
            trip.getTransportation().forEach(transportation -> transportation.setParentTrip(trip));

        }
        return trip;
    }

    public static TripDTO toDto(Trip trip)
    {
        TripDTO dto = new TripDTO(trip);
//        dto.setId(trip.getId());
//        dto.setName(trip.getName());
//        dto.setDestination(trip.getDestination());
//        dto.setDays(trip.getDays());
//        dto.setStartOfTrip(trip.getStartOfTrip());

        //cred ca ar trebui sa fac metode in services pentru setarea acestor liste de DTOs.
        if(trip.getItineraryItems() != null && !trip.getItineraryItems().isEmpty())
        {
//            List<ItineraryDTO> itineraryDTOList = trip.getItineraryItems().stream()
//                    .map(itineraryItem -> {
//                ItineraryDTO itin = new ItineraryDTO(itineraryItem);// mai scurt cu constructor
//                return itin;
//            }).collect(Collectors.toList());
//            dto.setItineraryDTOS(itineraryDTOList);
            List<ItineraryDTO> dtoList = ItineraryItemMapper.toDtoList(trip.getItineraryItems());
            dto.setItineraryDTOS(dtoList);
        }
//            dto.setItineraryDTOS(trip.getItineraryItems());


        if(trip.getTransportation() != null && !trip.getTransportation().isEmpty()) {
            List<TransportationDTO> transportationDTOList = TransportationMapper.toDtoList(trip.getTransportation());
            dto.setTransportationDTOS(transportationDTOList);
        }
//            dto.setTransportationDTOS(trip.getTransportation());

        if(trip.getAccommodations() != null && !trip.getAccommodations().isEmpty())
        {
            List<AccommodationDTO> accommodationDTOList = AccommodationMapper.toDtoList(trip.getAccommodations());
            dto.setAccommodationDTOS(accommodationDTOList);
        }
//            dto.setAccommodationDTOS(trip.getAccommodations());

        return dto;
    }

    public static List<TripDTO> toDtoList(List<Trip> trips)
    {
        List<TripDTO> tripDTOList = trips.stream()
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
                    Trip trip  = new Transportation(tripDTO);
                    return trip;
                }).collect(Collectors.toList());
        return tripList;
    }

    public static Trip updateEntityFromDto(TripDTO tripDTO,Trip entityToUpdate) {

            entityToUpdate.setName(tripDTO.getName());
            entityToUpdate.setDestination(tripDTO.getDestination());
            entityToUpdate.setDays(tripDTO.getDays());
            entityToUpdate.setStartOfTrip(tripDTO.getStartOfTrip());

            entityToUpdate.setItineraryItems(ItineraryItemMapper.itineraryListFromDtos(tripDTO.getItineraryDTOS()));
            entityToUpdate.setAccommodations(AccommodationMapper.accomodationListFromDtos(tripDTO.getAccommodationDTOS()));
            entityToUpdate.setTransportation(TransportationMapper.transportationListFromDtos(tripDTO.getTransportationDTOS()));

        return entityToUpdate;
    }

}
