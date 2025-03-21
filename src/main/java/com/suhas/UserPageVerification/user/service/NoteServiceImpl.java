package com.suhas.UserPageVerification.user.service;

import com.suhas.UserPageVerification.user.models.Note;
import com.suhas.UserPageVerification.user.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class NoteServiceImpl implements NoteService{

    private final NoteRepository noteRepository;
    NoteServiceImpl(NoteRepository noteRepository)
    {
        this.noteRepository=noteRepository;
    }
    @Override
    public Note createNote(String userName, String content) {
        Note note=new Note();
        note.setContent(content);
        note.setOwnerUserName(userName);
        return noteRepository.save(note);
    }

    @Override
    public List<Note> getAllNotesForUser(String userName) {
       return noteRepository.findByOwnerUserName(userName);
    }

    @Override
    public Note updateANote(Long id, String userName, String content) {
        List<Note> list = noteRepository.findByOwnerUserName(userName).stream().filter(a -> Objects.equals(a.getId(), id)).toList();
        Note note = list.getFirst();
        note.setContent(content);
        return note;
    }

    @Override
    public void deleteANote(String username, Long id) {
        Note first = noteRepository.findByOwnerUserName(username).stream().filter(a -> Objects.equals(a.getId(), id)).toList().getFirst();
        noteRepository.deleteById(id);
    }


}
