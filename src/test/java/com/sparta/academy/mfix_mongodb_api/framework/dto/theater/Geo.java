package com.sparta.academy.mfix_mongodb_api.framework.dto.theater;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Geo{

	@JsonProperty("coordinates")
	private List<Double> coordinates;

	@JsonProperty("type")
	private String type;

	public List<Double> getCoordinates(){
		return coordinates;
	}

	public String getType(){
		return type;
	}

	public boolean hasValidCoordinates() {
		return ((coordinates.get(1) >= -90 && coordinates.get(1) <=90) && (coordinates.get(0) >= -180 && coordinates.get(0) <=180));
	}

	public boolean isTypeNull() {
		return type == null;
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