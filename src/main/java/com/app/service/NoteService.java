package com.app.service;

import com.app.models.Note;
import com.app.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

@AllArgsConstructor
@Service
public class NoteService {
    private final NoteRepository nRep;

    public List<Note> listAll(){
        return (List<Note>) nRep.findAll();
    }

    public Note add(Note note) {
        note.setId(Jojo.generateId());
        return nRep.save(note);
    }

    public void deleteById(long id) {
        if (!nRep.existsById(id)) {
            throw new IllegalArgumentException("Note with id " + id + " not found");
        }
        nRep.deleteById(id);
    }

    public void update(Note note) {
        if (!nRep.existsById(note.getId())) {
            throw new IllegalArgumentException("Note with id " + note.getId() + " not found");
        }
        nRep.save(note);
    }

    public Note getById(long id) {
        Optional<Note> note = nRep.findById(id);
        return note.orElseThrow(() -> new NoSuchElementException("There is no note with id " + id));
    }
    static class Jojo{
        public static long generateId() {
            Random random = new Random();
            return Math.abs(random.nextInt());
        }
    }
}
