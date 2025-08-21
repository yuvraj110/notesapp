package com.learn.notesapp.repository;

import com.learn.notesapp.model.Note;
import com.mongodb.client.result.DeleteResult;

import java.util.List;

public interface NotesRepository {
    List<Note> getAllRecords();

    Note insertSingleNote(Note note);

    Note updateSingleNote(String id, Note note) throws IllegalAccessException;

    DeleteResult deleteSingleNote(String id);
}
