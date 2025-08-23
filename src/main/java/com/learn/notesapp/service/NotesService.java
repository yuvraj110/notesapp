package com.learn.notesapp.service;

import com.learn.notesapp.dto.NoteDetail;
import com.learn.notesapp.model.Note;
import com.mongodb.client.result.DeleteResult;

import java.util.List;

public interface NotesService {
    List<Note> getAllRecords();

    NoteDetail insertSingleNote(NoteDetail note);

    Note updateSingleNote(String id, NoteDetail note) throws IllegalAccessException;

    DeleteResult deleteSingleNote(String id);

    List<Note> getRecordByFilter(NoteDetail note);
}
