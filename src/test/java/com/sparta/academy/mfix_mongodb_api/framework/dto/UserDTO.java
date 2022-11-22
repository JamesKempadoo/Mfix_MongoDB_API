package com.sparta.academy.mfix_mongodb_api.framework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sparta.academy.mfix_mongodb_api.entity.User;
import nonapi.io.github.classgraph.json.Id;

public class UserDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("password")
    private String password;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    private final static int PASSWORD_ENCRYPTION_LENGTH = 60;

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isIdNull() {
        return id == null;
    }

    public boolean isPasswordNull() {
        return password == null;
    }

    public boolean isNameNull() {
        return name == null;
    }

    public boolean isEmailNull() {
        return email == null;
    }

    public boolean isIdBlank() {
        return id.isBlank();
    }

    public boolean isPasswordBlank() {
        return password.isBlank();
    }

    public boolean isNameBlank() {
        return name.isBlank();
    }

    public boolean isEmailBlank() {
        return email.isBlank();
    }

    public boolean isEmailValidEmail() {
        return false;
    }

    public boolean isPasswordEncryptionValidLength() {
        return password.length() == PASSWORD_ENCRYPTION_LENGTH;
    }

    public boolean isNameWithSpecialCharacter() {
        return false;
    }

    public boolean isIdWithSpecialCharacter() {
        return false;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
