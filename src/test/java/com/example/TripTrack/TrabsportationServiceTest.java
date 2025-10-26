package com.example.TripTrack;

import com.example.TripTrack.dto.TransportationDTO;
import com.example.TripTrack.entities.Transportation;
import com.example.TripTrack.entities.Trip;
import com.example.TripTrack.repositories.TransportationRepository;
import com.example.TripTrack.repositories.TripRepository;
import com.example.TripTrack.services.ServiceInterfaces.UpdateTotalPrice;
import com.example.TripTrack.services.TransportationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;



import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrabsportationServiceTest {

    @Mock
    private TransportationRepository transportationRepository;
    @Mock private UpdateTotalPrice updateTotalPrice;
    @Mock private TripRepository tripRepository;
    @InjectMocks
    private TransportationService transportationService;

    private Transportation testTransportation;
    private TransportationDTO testDto;
    private UUID id;
    private Trip testTrip;
    private UUID tripId;
    com.example.TripTrack.enums.TransportationTypes transportationTypes;

    @BeforeEach
    void setUp()
    {
        testTransportation = new Transportation();
        id = UUID.randomUUID();
        testTransportation.setId(id);
        testTransportation.setCategory(com.example.TripTrack.enums.TransportationTypes.PLANE);
        testTransportation.setRoute("Bucharest - Paris");
        testTransportation.setDistance(1875.5f);
        testTransportation.setPrice(350.0);
        testTrip = new Trip();
        tripId = UUID.randomUUID();
        testTrip.setId(tripId);
        testTrip.setName("Trip to Paris");
        testTransportation.setParent(testTrip);

        testDto = new TransportationDTO();
        testDto.setId(id);
        testDto.setCategory(com.example.TripTrack.enums.TransportationTypes.PLANE);
        testDto.setRoute("Bucharest - Paris");
        testDto.setDistance(1875.5f);
        testDto.setPrice(350.0);
    }

    @Test
    void save_ShouldPersist_SetParent_ReturnDto_AndRecalcTripTotal() {
        when(tripRepository.findById(eq(tripId))).thenReturn(Optional.of(testTrip));
        when(transportationRepository.save(any(Transportation.class)))
                .thenAnswer(inv -> inv.getArgument(0, Transportation.class));
        TransportationDTO result = transportationService.save(tripId, testDto);
        assertEquals("Bucharest - Paris", result.getRoute());
        assertEquals(350.0, result.getPrice());
        verify(tripRepository).findById(tripId);
        verify(transportationRepository).save(any(Transportation.class));
        verify(updateTotalPrice).updateTotalPrice(tripId);
        verifyNoMoreInteractions(tripRepository, transportationRepository, updateTotalPrice);
    }

    @Test
    void findById_ShouldReturnTransportation_WhenExists() {
        when(transportationRepository.findById(id))
                .thenReturn(Optional.of(testTransportation));
        Transportation result = transportationService.findById(id);
        assertEquals("Bucharest - Paris", result.getRoute());
        assertEquals(350.0, result.getPrice());
        assertEquals(transportationTypes.PLANE, result.getCategory());
        assertEquals(testTrip, result.getParent());
        verify(transportationRepository).findById(id);
        verifyNoMoreInteractions(transportationRepository);
    }

    @Test
    void findById_ShouldThrowException_WhenNotFound() {
        when(transportationRepository.findById(any(UUID.class)))
                .thenReturn(Optional.empty());
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> transportationService.findById(UUID.randomUUID()));
        assertEquals("Transportation not found", ex.getMessage());
        verify(transportationRepository).findById(any(UUID.class));
        verifyNoMoreInteractions(transportationRepository);
    }

}
