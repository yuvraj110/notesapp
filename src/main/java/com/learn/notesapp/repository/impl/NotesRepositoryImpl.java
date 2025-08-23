package com.learn.notesapp.repository.impl;

import com.learn.notesapp.commons.Constants;
import com.learn.notesapp.dto.NoteDetail;
import com.learn.notesapp.model.Note;
import com.learn.notesapp.repository.NotesRepository;
import com.learn.notesapp.utility.UtilityMethods;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
@Repository
public class NotesRepositoryImpl implements NotesRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Note> getAllRecords() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_admin"));
        Query query = new Query();
        if (isAdmin) {
            return mongoTemplate.find(new Query(), Note.class, Constants.CONTENT);
        } else {
            query.addCriteria(Criteria.where("userName").is(userName));
        }
        return mongoTemplate.find(query, Note.class, Constants.CONTENT);
    }

    @Override
    public NoteDetail insertSingleNote(NoteDetail note) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        note.setUserName(auth.getName());
        return mongoTemplate.insert(note, Constants.CONTENT);
    }

    @Override
    public Note updateSingleNote(String id, NoteDetail note) throws IllegalAccessException {
        Query query =  new Query(Criteria.where(Constants.ID).is(id));
        Update update =  new Update();
        Field[] fields = Note.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(note);
            if (value != null && !field.getName().equals(Constants.ID)) {
                update.set(field.getName(), value);
            }
        }
        update.set("createdAt", LocalDateTime.now());
        return mongoTemplate.findAndModify(query,update, FindAndModifyOptions.options().returnNew(true),Note.class);
    }

    @Override
    public DeleteResult deleteSingleNote(String id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_admin"));
        if(isAdmin) {
            Query query = new Query(Criteria.where(Constants.ID).is(id));
            return mongoTemplate.remove(query, Note.class);
        }else {
            throw new UsernameNotFoundException("You are not applicable to delete the notes. Only admin can delete it.");
        }
    }

    @Override
    public List<Note> getRecordByFilter(NoteDetail note) {
        Query query = new Query();

        if (UtilityMethods.stringNullAndEmptyCheck(note.getId())) {
            query.addCriteria(Criteria.where(Constants.ID).is(note.getId()));
        }
        if (UtilityMethods.stringNullAndEmptyCheck(note.getTitle())) {
            query.addCriteria(Criteria.where("title").regex(note.getTitle(), "i"));
        }
        if (UtilityMethods.stringNullAndEmptyCheck(note.getContent())){
            query.addCriteria(Criteria.where(Constants.CONTENT).regex(note.getContent(), "i"));
        }
        if (note.getPlans()!=null && !note.getPlans().isEmpty()){
            query.addCriteria(Criteria.where("plans").in(note.getPlans()));
        }

        return mongoTemplate.find(query,Note.class);
    }
}
