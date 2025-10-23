package com.example.TripTrack;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(info = @Info(title = "Trip Tracking API",version = "1.0", description = "Aceasta aplicatie este creata pentru a ajuta turistii si pasionatii de travel sa isi planifice calatoriile."))
@SpringBootApplication
public class TripTrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripTrackApplication.class, args);
	}

}

