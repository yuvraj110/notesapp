package com.learn.notesapp.service;

import com.learn.notesapp.model.Note;
import com.mongodb.client.result.DeleteResult;

import java.util.List;

public interface NotesService {
    List<Note> getAllRecords();

    Note insertSingleNote(Note note);

    Note updateSingleNote(String id, Note note) throws IllegalAccessException;

    DeleteResult deleteSingleNote(String id);
}
