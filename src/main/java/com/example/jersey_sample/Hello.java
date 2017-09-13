package com.example.jersey_sample;

import com.example.jersey_sample.model.Greeting;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author manhnt
 */
@Path("/hello")
public class Hello {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Greeting hello() {
		return new Greeting("Hello from the other side!");
	}
}
