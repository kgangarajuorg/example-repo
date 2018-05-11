package com.example.flights;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exmple.flights.domain.Flight;

@RestController
public class FlightsController {

	@Value("${server.port}")
	private String port;
	
	@RequestMapping("/something")
	public List<Flight> list(@RequestParam("origin") String origin, @RequestParam("destination") String dest){
		
		return Arrays.asList( new Flight("EK521", port), new Flight("EK522",port), new Flight("EK888",port));
		
	}
}
