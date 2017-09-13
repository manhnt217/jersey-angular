package com.example.jersey_sample.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import javax.ws.rs.ext.Provider;

/**
 * @author manhnt
 */

@Provider
public class DateTimeBodyWriter extends JacksonJaxbJsonProvider {
	
	public DateTimeBodyWriter() {
		super();
		ObjectMapper om = new ObjectMapper();
		om.registerModule(new JavaTimeModule());
		om.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
		om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		setMapper(om);
	}
}
