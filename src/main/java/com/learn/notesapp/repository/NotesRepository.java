package com.learn.notesapp.repository;

import com.learn.notesapp.dto.NoteDetail;
import com.learn.notesapp.model.Note;
import com.mongodb.client.result.DeleteResult;

import java.util.List;

public interface NotesRepository {
    List<Note> getAllRecords();

    NoteDetail insertSingleNote(NoteDetail note);

    Note updateSingleNote(String id, NoteDetail note) throws IllegalAccessException;

    DeleteResult deleteSingleNote(String id);

    List<Note> getRecordByFilter(NoteDetail note);
}
