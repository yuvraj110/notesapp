package com.learn.notesapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetails {

    private String id;
    private String userName;
    private String password;
    private String role;
}
