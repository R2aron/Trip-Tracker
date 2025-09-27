package com.example.TripTrack.it;

import com.example.TripTrack.dto.TripDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MovieIntegratonTest {
    @Autowired
   private MockMvc mockMvc;

    private TripDTO testTripDto;

    void setUp(){
        testTripDto = new TripDTO();

        
    }


}
