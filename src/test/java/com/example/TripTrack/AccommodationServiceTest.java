package com.example.TripTrack;

import com.example.TripTrack.dto.AccommodationDTO;
import com.example.TripTrack.entities.Accommodation;
import com.example.TripTrack.entities.Trip;
import com.example.TripTrack.repositories.AccommodationRepository;
import com.example.TripTrack.repositories.TripRepository;
import com.example.TripTrack.services.AccommodationService;
import com.example.TripTrack.services.ServiceInterfaces.UpdateTotalPrice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccommodationServiceTest {

    @Mock
    private AccommodationRepository accommodationRepository;
    @Mock private UpdateTotalPrice updateTotalPrice;
    @Mock private TripRepository tripRepository;
    @InjectMocks
    private AccommodationService accommodationService;

    private Accommodation testAccommodation;
    private AccommodationDTO testDto;
    private UUID id;
    private Trip testTrip;
    private UUID tripId;

    @BeforeEach
    void setUp()
    {
        testAccommodation = new Accommodation();
        id = UUID.randomUUID();
        testAccommodation.setId(id);
        testAccommodation.setName("InterContinental");
        testAccommodation.setLocation("Central");
        testAccommodation.setCheckIn(LocalDateTime.parse("2025-11-15T15:00:00"));
        testAccommodation.setCheckOut(LocalDateTime.parse("2025-11-17T12:00:00"));
        testAccommodation.setPrice(850.0);
        testAccommodation.setAddress("Bulevardul Nicolae Balcescu sector 1");
        testTrip = new Trip();
        tripId = UUID.randomUUID();
        testTrip.setId(tripId);
        testTrip.setName("Bucharest visit");
        testAccommodation.setParent(testTrip);

        testDto = new AccommodationDTO();
        testDto.setId(id);
        testDto.setName("InterContinental");
        testDto.setLocation("Central");
        testDto.setCheckIn(LocalDateTime.parse("2025-11-15T15:00:00"));
        testDto.setCheckOut(LocalDateTime.parse("2025-11-17T12:00:00"));
        testDto.setPrice(850.0);
        testDto.setAddress("Bulevardul Nicolae Balcescu sector 1");
    }

    @Test
    void findById_ShouldReturnAccommodation_WhenExists() {
        when(accommodationRepository.findById(id)).thenReturn(Optional.of(testAccommodation));
        Accommodation found = accommodationService.findById(id);
        assertNotNull(found);
        assertEquals("InterContinental", found.getName());
        assertEquals(850.0, found.getPrice());
        verify(accommodationRepository, times(1)).findById(id);
        verifyNoMoreInteractions(accommodationRepository);
    }

    @Test
    void findById_ShouldThrowException_WhenNotFound() {
        when(accommodationRepository.findById(id)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> accommodationService.findById(id));
        assertEquals("Accommodation not found", exception.getMessage());
        verify(accommodationRepository, times(1)).findById(id);
        verifyNoMoreInteractions(accommodationRepository);
    }

    @Test
    void findById_ShouldThrow_WhenNotFound() {
        when(accommodationRepository.findById(id)).thenReturn(Optional.empty());
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> accommodationService.findById(id));
        assertEquals("Accommodation not found", ex.getMessage());
        verify(accommodationRepository).findById(id);
        verifyNoMoreInteractions(accommodationRepository);
    }

    @Test
    void deleteById_ShouldCallRepository() {
        when(accommodationRepository.findById(id)).thenReturn(Optional.of(testAccommodation));
        doNothing().when(accommodationRepository).deleteById(id);
        UUID tripId = testAccommodation.getParent().getId();
        doNothing().when(updateTotalPrice).updateTotalPrice(eq(tripId));
        accommodationService.deleteById(id);
        verify(accommodationRepository).findById(id);
        verify(accommodationRepository).deleteById(id);
        verify(updateTotalPrice).updateTotalPrice(eq(tripId));
        verifyNoMoreInteractions(accommodationRepository, updateTotalPrice);
    }
}
