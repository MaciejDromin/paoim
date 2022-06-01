package pl.mlisowski.noteapp.notes.dao;

import androidx.room.*;
import pl.mlisowski.noteapp.notes.domain.Note;

import java.util.List;
import java.util.Optional;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM note")
    List<Note> findAllNotes();

    @Query("SELECT * FROM note WHERE id = (:noteId)")
    Optional<Note> findNote(Long noteId);

    @Query("SELECT * FROM note WHERE title = (:noteTitle)")
    Optional<Note> findNoteByTitle(String noteTitle);

    @Query("SELECT * FROM note WHERE title = (:noteTitle)")
    Note getNoteByTitle(String noteTitle);

    @Update
    void update(Note note);

    @Insert
    void insert(Note note);

    @Delete
    void delete(Note note);

}
