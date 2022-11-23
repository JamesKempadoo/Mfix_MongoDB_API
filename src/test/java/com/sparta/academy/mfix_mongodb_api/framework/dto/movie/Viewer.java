package com.sparta.academy.mfix_mongodb_api.framework.dto.movie;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Viewer{

	@JsonProperty("rating")
	private Integer rating;

	@JsonProperty("numReviews")
	private Integer numReviews;

	public Integer getRating(){
		return rating;
	}

	public Integer getNumReviews(){
		return numReviews;
	}

	@Override
 	public String toString(){
		return 
			"Viewer{" + 
			"rating = '" + rating + '\'' + 
			",numReviews = '" + numReviews + '\'' + 
			"}";
		}
}