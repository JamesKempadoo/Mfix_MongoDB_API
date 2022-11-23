package com.sparta.academy.mfix_mongodb_api.framework.dto.movie;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Imdb{

	@JsonProperty("rating")
	private Integer rating;

	@JsonProperty("votes")
	private Integer votes;

	@JsonProperty("id")
	private Object id;

	public Integer getRating(){
		return rating;
	}

	public Integer getVotes(){
		return votes;
	}

	public Object getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"Imdb{" + 
			"rating = '" + rating + '\'' + 
			",votes = '" + votes + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}