package com.example.checkout.rest;

import javax.ws.rs.PathParam;

import org.springframework.web.bind.annotation.RestController;

import com.example.checkout.domain.Checkout;

@RestController
public class CheckoutController {

	public Checkout checkout(@PathParam("id") String id) {
		
		return new Checkout();
	}
}
