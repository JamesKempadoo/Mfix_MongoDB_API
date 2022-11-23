package com.sparta.academy.mfix_mongodb_api.entity.theater;

import java.util.List;

//@Document(collection = "geos")
public class Geo{

//	@Field
	private List<Double> coordinates;

//	@Field
	private String type;

	public void setCoordinates(List<Double> coordinates) {
		this.coordinates = coordinates;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Double> getCoordinates(){
		return coordinates;
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