package com.learn.notesapp.service.impl;

import com.learn.notesapp.dto.NoteDetail;
import com.learn.notesapp.model.Note;
import com.learn.notesapp.repository.NotesRepository;
import com.learn.notesapp.service.NotesService;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NotesServiceImpl implements NotesService {
    @Autowired
    NotesRepository repository;
    @Override
    public List<Note> getAllRecords() {
        return repository.getAllRecords();
    }

    @Override
    public NoteDetail insertSingleNote(NoteDetail note) {
       return repository.insertSingleNote(note);
    }

    @Override
    public Note updateSingleNote(String id, NoteDetail note) throws IllegalAccessException {
        return repository.updateSingleNote(id,note);
    }

    @Override
    public DeleteResult deleteSingleNote(String id) {
        return repository.deleteSingleNote(id);
    }

    @Override
    public List<Note> getRecordByFilter(NoteDetail note) {
        return repository.getRecordByFilter(note);
    }
}
