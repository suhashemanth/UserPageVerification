package com.suhas.UserPageVerification.user.repository;

import com.suhas.UserPageVerification.user.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note,Long> {

    List<Note> findByOwnerUserName(String name);
}
