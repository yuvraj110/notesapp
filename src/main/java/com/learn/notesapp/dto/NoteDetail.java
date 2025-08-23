package com.learn.notesapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NoteDetail {
    private String id;
    private String title;
    private String content;
    private List<String> plans;
    private String userName;
    private LocalDateTime createdAt = LocalDateTime.now();


}
