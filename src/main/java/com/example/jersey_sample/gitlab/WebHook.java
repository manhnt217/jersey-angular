package com.example.jersey_sample.gitlab;

import com.example.jersey_sample.gitlab.sse.MessageEndpoint;

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
