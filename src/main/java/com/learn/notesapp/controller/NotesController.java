package com.learn.notesapp.controller;

import com.learn.notesapp.commons.Constants;
import com.learn.notesapp.dto.NoteDetail;
import com.learn.notesapp.model.Note;
import com.learn.notesapp.service.NotesService;
import com.learn.notesapp.utility.GenericResponse;
import com.mongodb.client.result.DeleteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/noteapps/")
public class NotesController {

    private static final Logger logger = LoggerFactory.getLogger(NotesController.class);
    @Autowired
    private NotesService service;

    @GetMapping("getAllNotes")
    public ResponseEntity<GenericResponse> getAllRecords() {
        long startTime = System.currentTimeMillis();
        logger.info("invoking NotesController. getAllRecords");
        List<Note> noteList = service.getAllRecords();
        return ResponseEntity.status(HttpStatus.OK).body(
                new GenericResponse(Constants.OK, Constants.DATA_FOUND, null, noteList, System.currentTimeMillis() - startTime));
    }

    @PostMapping(value = "insertNote", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse> insertSingleNote(@RequestBody NoteDetail note) {
        long startTime = System.currentTimeMillis();
        logger.info("invoking NotesController. insertSingleNote");
        NoteDetail result = service.insertSingleNote(note);
        return ResponseEntity.status(HttpStatus.OK).body(
                new GenericResponse(Constants.OK, Constants.INSERT_SUCCESS_MSG, null, result, System.currentTimeMillis() - startTime));
    }

    @PutMapping(value = "updateNote/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse> updateSingleNote(@PathVariable String id, @RequestBody NoteDetail note) throws IllegalAccessException {
        long startTime = System.currentTimeMillis();
        logger.info("invoking NotesController. updateSingleNote");
        Note result = service.updateSingleNote(id, note);
        return ResponseEntity.status(HttpStatus.OK).body(
                new GenericResponse(Constants.OK, Constants.UPDATE_SUCCESS_MSG, null, result, System.currentTimeMillis() - startTime));
    }

    @DeleteMapping(value = "deleteNote/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse> deleteSingleNote(@PathVariable String id) {
        long startTime = System.currentTimeMillis();
        logger.info("invoking NotesController.deleteSingleNote");
        DeleteResult result = service.deleteSingleNote(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new GenericResponse(Constants.OK, Constants.DELETE_SUCCESS_MSG, null, result, System.currentTimeMillis() - startTime));
    }

    @GetMapping(value = "getFilterNotes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse> getRecordByFilter(@RequestBody NoteDetail note) {
        long startTime = System.currentTimeMillis();
        logger.info("invoking NotesController. getRecordByFilter");
        List<Note> noteList = service.getRecordByFilter(note);
        return ResponseEntity.status(HttpStatus.OK).body(
                new GenericResponse(Constants.OK, Constants.DATA_FOUND, null, noteList, System.currentTimeMillis() - startTime));
    }
}
