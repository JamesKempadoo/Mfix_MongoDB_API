package com.sparta.academy.mfix_mongodb_api.framework.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class UserCollectionDTO {
    @JsonProperty
    private ArrayList<UserDTO> users;
}
