package com.sparta.academy.mfix_mongodb_api.framework.dto.comments;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sparta.academy.mfix_mongodb_api.model.entity.Comment;
import org.springframework.data.mongodb.core.aggregation.StringOperators;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommentDTO {

    @JsonProperty
    public String id;

    @JsonProperty
    public String name;

    @JsonProperty
    public String date;

    @JsonProperty
    public String email;

    @JsonProperty
    public String movieId;

    @JsonProperty
    public String text;

    public CommentDTO() {
    }

    public CommentDTO(String id, String name, String date, String email, String movieId, String text) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.email = email;
        this.movieId = movieId;
        this.text = text;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", email='" + email + '\'' +
                ", movieId=" + movieId +
                ", text='" + text + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDTO that = (CommentDTO) o;
        return id.equals(that.id) && name.equals(that.name) && date.equals(that.date) && email.equals(that.email) && movieId.equals(that.movieId) && text.equals(that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date, email, movieId, text);
    }

    // Check that fields are not null
    public boolean isCommentDTOFieldsValid(CommentDTO dto) {
        return  isValueValid(dto.getId()) &&
                isValueValid(dto.getEmail()) &&
                isValueValid(dto.getName()) &&
                isValueValid(dto.getText()) &&
                isValueValid(dto.getDate()) &&
                isValueValid(dto.getMovieId());
    }

    private boolean isValueValid(String value) {
        return value != null && value.length() > 0;
    }

    // Check email format is correct
    public boolean isEmailValid() {
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(this.getEmail());
        return matcher.find();
    }

    public boolean isMovieIdValid() {
        return true; // TODO
    }

    // Check that date is in a valid ISO format
    public boolean isDateValid() {
        try {
            LocalDate.parse(this.getDate());
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
 }
