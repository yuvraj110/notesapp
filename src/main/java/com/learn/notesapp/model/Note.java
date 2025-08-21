package com.learn.notesapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "content")
public class Note {
    @Id
    private String id;
    private String title;
    private String content;
    private LocalDateTime createdAt = LocalDateTime.now();


}
