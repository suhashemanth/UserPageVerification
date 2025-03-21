package com.suhas.UserPageVerification.user.controller;

import com.suhas.UserPageVerification.user.models.Note;
import com.suhas.UserPageVerification.user.service.NoteService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService)
    {
        this.noteService=noteService;
    }

    @PostMapping
    public Note addANote(@RequestBody String content, @AuthenticationPrincipal UserDetails userDetails)
    {
        String username = userDetails.getUsername();
        System.out.println("Crete a Note");
        return noteService.createNote(username,content);
    }

    @GetMapping
    public List<Note> getAllNotes(@AuthenticationPrincipal UserDetails userDetails)
    {
        String username = userDetails.getUsername();
        return noteService.getAllNotesForUser(username);
    }

    @PutMapping("{id}")
    public Note updateNote(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id,@RequestBody String content)
    {
        String userName=userDetails.getUsername();
        return noteService.updateANote(id,userName,content);
    }

    @DeleteMapping("{id}")
    public void deleteANote(@AuthenticationPrincipal UserDetails userDetails,@PathVariable Long id)
    {
        String username = userDetails.getUsername();
        noteService.deleteANote(username,id);
    }

}
