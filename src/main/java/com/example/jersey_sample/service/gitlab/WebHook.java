package com.example.jersey_sample.service.gitlab;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * @author manhnt
 */
public class WebHook {

	@POST
	@Path("/gitlab/hook")
	public void pushEventHook(String body) {
		MessageEndpoint.broadcastMessage(body);
	}
}
