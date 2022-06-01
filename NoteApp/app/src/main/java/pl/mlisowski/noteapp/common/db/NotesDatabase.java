package pl.mlisowski.noteapp.common.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import pl.mlisowski.noteapp.categories.dao.CategoriesDao;
import pl.mlisowski.noteapp.categories.domain.Categories;
import pl.mlisowski.noteapp.notes.dao.NoteDao;
import pl.mlisowski.noteapp.notes.dao.NotesByCategoriesDao;
import pl.mlisowski.noteapp.notes.domain.Note;
import pl.mlisowski.noteapp.notes.domain.NotesByCategories;

@Database(entities = {
        Note.class,
        Categories.class
}, version = 1)
public abstract class NotesDatabase extends RoomDatabase {

    public abstract NoteDao noteDao();
    public abstract CategoriesDao categoriesDao();

    public abstract NotesByCategoriesDao notesByCategoriesDao();

}
