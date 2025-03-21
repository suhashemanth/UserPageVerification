package com.suhas.UserPageVerification.user.service;

import com.suhas.UserPageVerification.user.models.Note;

import java.util.List;

public interface NoteService {

    Note createNote(String userName,String content);

    List<Note> getAllNotesForUser(String userName);

    Note updateANote(Long id,String userName,String content);

    void deleteANote(String username,Long id);
}
