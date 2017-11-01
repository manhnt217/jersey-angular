package com.example.jersey_sample.service.provider.converter;

import javax.ws.rs.ext.ParamConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author manhnt
 */
public class LocalDateConverter implements ParamConverter<LocalDate> {
	
	@Override
	public LocalDate fromString(String dateString) {
		
		if (dateString == null || "".equals(dateString)) return null;
		return LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
	}
	
	@Override
	public String toString(LocalDate date) {
		return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
	}
}
