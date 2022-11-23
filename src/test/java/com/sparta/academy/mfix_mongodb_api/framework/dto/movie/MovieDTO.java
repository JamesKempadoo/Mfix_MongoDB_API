package com.sparta.academy.mfix_mongodb_api.framework.dto.movie;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieDTO{

	@JsonProperty("year")
	private String year;

	@JsonProperty("directors")
	private List<String> directors;

	@JsonProperty("runtime")
	private Integer runtime;

	@JsonProperty("countries")
	private List<String> countries;

	@JsonProperty("title")
	private String title;

	@JsonProperty("type")
	private String type;

	@JsonProperty("num_mflix_comments")
	private Integer numMflixComments;

	@JsonProperty("cast")
	private List<String> cast;

	@JsonProperty("tomatoes")
	private Tomatoes tomatoes;

	@JsonProperty("fullplot")
	private String fullplot;

	@JsonProperty("imdb")
	private Imdb imdb;

	@JsonProperty("plot")
	private String plot;

	@JsonProperty("genres")
	private List<String> genres;

	@JsonProperty("lastupdated")
	private String lastupdated;

	@JsonProperty("id")
	private String id;

	@JsonProperty("poster")
	private String poster;

	@JsonProperty("released")
	private String released;

	public String getYear(){
		return year;
	}

	public List<String> getDirectors(){
		return directors;
	}

	public Integer getRuntime(){
		return runtime;
	}

	public List<String> getCountries(){
		return countries;
	}

	public String getTitle(){
		return title;
	}

	public String getType(){
		return type;
	}

	public Integer getNumMflixComments(){
		return numMflixComments;
	}

	public List<String> getCast(){
		return cast;
	}

	public Tomatoes getTomatoes(){
		return tomatoes;
	}

	public String getFullplot(){
		return fullplot;
	}

	public Imdb getImdb(){
		return imdb;
	}

	public String getPlot(){
		return plot;
	}

	public List<String> getGenres(){
		return genres;
	}

	public String getLastupdated(){
		return lastupdated;
	}

	public String getId(){
		return id;
	}

	public String getPoster(){
		return poster;
	}

	public String getReleased(){
		return released;
	}

	@Override
 	public String toString(){
		return 
			"MovieDTO{" + 
			"year = '" + year + '\'' + 
			",directors = '" + directors + '\'' + 
			",runtime = '" + runtime + '\'' + 
			",countries = '" + countries + '\'' + 
			",title = '" + title + '\'' + 
			",type = '" + type + '\'' + 
			",num_mflix_comments = '" + numMflixComments + '\'' + 
			",cast = '" + cast + '\'' + 
			",tomatoes = '" + tomatoes + '\'' + 
			",fullplot = '" + fullplot + '\'' + 
			",imdb = '" + imdb + '\'' + 
			",plot = '" + plot + '\'' + 
			",genres = '" + genres + '\'' + 
			",lastupdated = '" + lastupdated + '\'' + 
			",id = '" + id + '\'' + 
			",poster = '" + poster + '\'' + 
			",released = '" + released + '\'' + 
			"}";
	}

	public boolean isDateParseable(String date){
		try {
			LocalDate.parse(date, DateTimeFormatter.ISO_DATE_TIME);
			return true;
		} catch (DateTimeParseException e) {
			e.printStackTrace();
		}
		return false;
	}
}