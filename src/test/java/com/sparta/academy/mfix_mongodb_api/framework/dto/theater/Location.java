package com.sparta.academy.mfix_mongodb_api.framework.dto.theater;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Location{

	@JsonProperty("geo")
	@JsonIgnore
	private Geo geo;

	@JsonProperty("address")
	@JsonIgnore
	private Address address;

	public <T> boolean isPropertyNull(T element) {
		return element == null;
	}

	public Geo getGeo(){
		return geo;
	}

	public Address getAddress(){
		return address;
	}

	@Override
 	public String toString(){
		return 
			"Location{" + 
			"geo = '" + geo + '\'' + 
			",address = '" + address + '\'' + 
			"}";
		}
}