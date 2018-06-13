package com.example.flights;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exmple.flights.domain.Flight;

@RestController
@RequestMapping("/flights")
public class FlightsController {

	@Value("${server.port}")
	private String port;
	
	@GetMapping("/list")
	public List<Flight> list(@RequestParam("origin") String origin, @RequestParam("destination") String dest){
		
		return Arrays.asList( new Flight("EK521", port), new Flight("EK522",port), new Flight("EK888",port));
	}
	
	@GetMapping("/details/{id}")
	public Flight details(@PathParam("id") String id) {
		return new Flight(id,port);
	}
	
	
}
