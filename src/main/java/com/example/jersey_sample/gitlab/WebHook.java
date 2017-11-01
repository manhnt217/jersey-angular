package com.example.jersey_sample.gitlab;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import static com.example.jersey_sample.Log.GITLAB_LOG;

/**
 * @author manhnt
 */
@SuppressWarnings("ALL")
public class WebHook {

	@POST
	@Path("/push")
	public void pushEventHook(String body) {
		GITLAB_LOG.trace("Body: " + body);
	}
}
