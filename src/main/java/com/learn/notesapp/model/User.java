package com.learn.notesapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @Id
    private String id;
    private String userName;
    private String password;
    private String role;
}
