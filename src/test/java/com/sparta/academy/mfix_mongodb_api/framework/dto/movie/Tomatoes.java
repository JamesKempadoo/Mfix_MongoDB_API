package com.sparta.academy.mfix_mongodb_api.framework.dto.movie;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tomatoes{

	@JsonProperty("viewer")
	private Viewer viewer;

	@JsonProperty("lastUpdated")
	private String lastUpdated;

	public Viewer getViewer(){
		return viewer;
	}

	public String getLastUpdated(){
		return lastUpdated;
	}

	@Override
 	public String toString(){
		return 
			"Tomatoes{" + 
			"viewer = '" + viewer + '\'' + 
			",lastUpdated = '" + lastUpdated + '\'' + 
			"}";
		}
}