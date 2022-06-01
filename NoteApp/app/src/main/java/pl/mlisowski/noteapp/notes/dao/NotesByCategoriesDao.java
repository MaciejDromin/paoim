package pl.mlisowski.noteapp.notes.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;
import pl.mlisowski.noteapp.notes.domain.NotesByCategories;

import java.util.List;

@Dao
public interface NotesByCategoriesDao {

    @Transaction
    @Query("SELECT * FROM categories")
    List<NotesByCategories> findAllNotesByCategories();

}
