package com.sparta.academy.mfix_mongodb_api.model.entity.theater;

public class Location{

//	@Field
	private Geo geo;

//	@Field
	private Address address;

	public void setGeo(Geo geo) {
		this.geo = geo;
	}

	public void setAddress(Address address) {
		this.address = address;
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