package com.sparta.academy.mfix_mongodb_api.framework.dto.movie;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;

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

	public boolean isDirectorsNull() {

		return directors == null;
	}

	public boolean isDirectorsEmpty() {

		return directors.size() == 0;
	}

	public boolean isYearNull() {

		return year == null;
	}

	public boolean isYearEmpty() {

		return year.length() == 0;
	}

	public boolean isRunTimeNull() {

		return runtime == null;
	}

	public boolean isRunTimeAboveZero() {

		return runtime > 0;
	}

	public boolean isYearAfter2022() {

		return Integer.parseInt(year) >= 2022;
	}

	private static final DateTimeFormatter releasedFormat = DateTimeFormatter.ofPattern("EEE-LLL-d-HH:mm:ss-zzz-yyyy", Locale.ENGLISH);
	public boolean isReleasedDateParseable(){
		try {
			LocalDateTime.parse(released.replace(" ", "-"), releasedFormat);
			return true;
		} catch (DateTimeParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isValidURL(String URL){
		boolean isValid = false;
		try {
			java.net.URL posterURL = new URL(URL);
			isValid = true;
		} catch (MalformedURLException ignored) {}
		return isValid;
	}

	public boolean isArrayOnlyLetters(List<String> list){
		boolean isOnlyLetters = true;
		//loop through list of countries
		for(String item : list){
			//regex to check if it only contains letters
			if (!item.matches("[a-zA-Z]+")) {
				isOnlyLetters = false;
				break;
			}
		}
		return isOnlyLetters;
	}

	public  boolean isNumMflixCommentsNull(){
		return numMflixComments == null;
	}

	public  boolean isNumMflixCommentsValid(){
		try {
			if (numMflixComments >= 0){
				return true;
			}
		} catch (IllegalArgumentException e){
			e.printStackTrace();
		}
		return false;
	}

	public  boolean isCastNull(){
		return cast == null;
	}

	public boolean isCastEmpty(){
		return cast.isEmpty();
	}

	public  boolean isTomatoesNull(){
		return tomatoes == null;
	}
}