package com.sparta.academy.mfix_mongodb_api.framework.dto.theater;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address{

	@JsonProperty("zipcode")
	private String zipcode;

	@JsonProperty("city")
	private String city;

	@JsonProperty("street1")
	private String street1;

	@JsonProperty("state")
	private String state;

	public String getZipcode(){
		return zipcode;
	}

	public String getCity(){
		return city;
	}

	public String getStreet1(){
		return street1;
	}

	public String getState(){
		return state;
	}

	public boolean hasValidZipcode() {
		return zipcode.contains("[0-9]{5}");
	}

	public boolean isCityFirstLetterCapital() {
		if (isPropertyNullOrBlank(city)) return false;
		return Character.isUpperCase(city.charAt(0));
	}

	public boolean isPropertyNullOrBlank(String element) {
		return element == null || element.isBlank();
	}

	public boolean hasValidState() {
		if (isPropertyNullOrBlank(state)) return false;

		String[] validStates = {"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "MP", "OH", "OK", "OR", "PW", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VI", "VA", "WA", "WV", "WI", "WY"};
		for(String st:validStates){
			if (state.equals(st)) {
				return true;
			}
		}
		return false;
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