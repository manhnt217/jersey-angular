package com.example.jersey_sample.provider;

import com.example.jersey_sample.provider.converter.LocalDateConverter;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDate;

/**
 * @author manhnt
 */
@Provider
public class DateTimeConverterProvider implements ParamConverterProvider {
	@Override
	public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
		if(rawType.equals(LocalDate.class)) {
			return (ParamConverter<T>) new LocalDateConverter();
		}
		return null;
	}
}
