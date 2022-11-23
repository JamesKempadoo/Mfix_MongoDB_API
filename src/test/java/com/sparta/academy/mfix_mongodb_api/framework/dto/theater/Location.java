package com.sparta.academy.mfix_mongodb_api.framework.dto.theater;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location{

	@JsonProperty("geo")
	private Geo geo;

	@JsonProperty("address")
	private Address address;

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