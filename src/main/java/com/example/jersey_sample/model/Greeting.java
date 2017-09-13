package com.example.jersey_sample.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author manhnt
 */
public class Greeting {

	private String messsage;

	private Integer version;

	public Greeting(String messsage) {

		this.messsage = messsage;
		this.version = 1;
	}

	public String getMesssage() {
		return messsage;
	}

	public Integer gerVersion() {
		return version;
	}
}
