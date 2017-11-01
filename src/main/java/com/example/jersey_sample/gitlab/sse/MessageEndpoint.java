package com.example.jersey_sample.gitlab.sse;

import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseBroadcaster;
import org.glassfish.jersey.media.sse.SseFeature;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author manhnt
 */
public class MessageEndpoint {
	private static SseBroadcaster broadcaster = new SseBroadcaster();

	@GET
	@Path("/gitlab/sse")
	@Produces(SseFeature.SERVER_SENT_EVENTS)
	public EventOutput getEvents() {
		final EventOutput eventOutput = new EventOutput();
		broadcaster.add(eventOutput);
		return eventOutput;
	}

	public static String broadcastMessage(String message) {
		OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
		OutboundEvent event = eventBuilder.name("message")
				.mediaType(MediaType.TEXT_PLAIN_TYPE)
				.data(String.class, message).build();

		broadcaster.broadcast(event);
		return "Message '" + message + "' has been broadcast.";
	}
}