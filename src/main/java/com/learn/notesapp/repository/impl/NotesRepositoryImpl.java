package com.learn.notesapp.repository.impl;

import com.learn.notesapp.model.Note;
import com.learn.notesapp.repository.NotesRepository;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
@Repository
public class NotesRepositoryImpl implements NotesRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Note> getAllRecords()  {
        return mongoTemplate.find(new Query(), Note.class, "content");
    }

    @Override
    public Note insertSingleNote(Note note) {
        return mongoTemplate.insert(note,"content");
    }

    @Override
    public Note updateSingleNote(String id, Note note) throws IllegalAccessException {
        Query query =  new Query(Criteria.where("id").is(id));
        Update update =  new Update();
        Field[] fields = Note.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(note);
            if (value != null && !field.getName().equals("id")) {
                update.set(field.getName(), value);
            }
        }
        update.set("createdAt", LocalDateTime.now());
        return mongoTemplate.findAndModify(query,update, FindAndModifyOptions.options().returnNew(true),Note.class);
    }

    @Override
    public DeleteResult deleteSingleNote(String id) {
        Query query =  new Query(Criteria.where("id").is(id));
        return mongoTemplate.remove(query,Note.class);
    }
}
