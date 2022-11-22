package com.sparta.academy.mfix_mongodb_api.entity;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "theaters")
public class Theater {

	@Id
	private String id;

	private Integer theaterId;

	private Location location;

	public void setTheaterId(Integer theaterId){
		this.theaterId = theaterId;
	}

	public Integer getTheaterId(){
		return theaterId;
	}

	public void setLocation(Location location){
		this.location = location;
	}

	public Location getLocation(){
		return location;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"Theaters{" + 
			"theaterId = '" + theaterId + '\'' + 
			",location = '" + location + '\'' + 
			",_id = '" + id + '\'' + 
			"}";
		}
	public static class Location{

		private Geo geo;

		private Address address;

		public void setGeo(Geo geo){
			this.geo = geo;
		}

		public Geo getGeo(){
			return geo;
		}

		public void setAddress(Address address){
			this.address = address;
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

	public static class Geo{

		private List<Object> coordinates;

		private String type;

		public void setCoordinates(List<Object> coordinates){
			this.coordinates = coordinates;
		}

		public List<Object> getCoordinates(){
			return coordinates;
		}

		public void setType(String type){
			this.type = type;
		}

		public String getType(){
			return type;
		}

		@Override
		public String toString(){
			return
					"Geo{" +
							"coordinates = '" + coordinates + '\'' +
							",type = '" + type + '\'' +
							"}";
		}
	}

	public static class Address{

		private String zipcode;

		private String city;

		private String street1;

		private String state;

		public void setZipcode(String zipcode){
			this.zipcode = zipcode;
		}

		public String getZipcode(){
			return zipcode;
		}

		public void setCity(String city){
			this.city = city;
		}

		public String getCity(){
			return city;
		}

		public void setStreet1(String street1){
			this.street1 = street1;
		}

		public String getStreet1(){
			return street1;
		}

		public void setState(String state){
			this.state = state;
		}

		public String getState(){
			return state;
		}

		@Override
		public String toString(){
			return
					"Address{" +
							"zipcode = '" + zipcode + '\'' +
							",city = '" + city + '\'' +
							",street1 = '" + street1 + '\'' +
							",state = '" + state + '\'' +
							"}";
		}
	}
}