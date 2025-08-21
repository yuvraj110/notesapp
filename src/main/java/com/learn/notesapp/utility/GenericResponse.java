package com.learn.notesapp.utility;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class GenericResponse {

    private String resultCode;
    private String resultDescription;
    private String errorDescription;
    private Object resultObj;
    private long executionTime;

    public GenericResponse() {
        super();
    }

    public GenericResponse(String resultCode, String resultDescription, String errorDescription, Object resultObj, long executionTime) {
        super();
        this.resultCode = resultCode;
        this.resultDescription = resultDescription;
        this.errorDescription = errorDescription;
        this.resultObj = resultObj;
        this.executionTime = executionTime;
    }

}
