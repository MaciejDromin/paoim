package pl.mlisowski.noteapp.notes.domain;

import androidx.room.Embedded;
import androidx.room.Relation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.mlisowski.noteapp.categories.domain.Categories;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotesByCategories {

    @Embedded public Categories categories;

    @Relation(
            parentColumn = "id",
            entityColumn = "categoryId"
    )
    public List<Note> noteList;

}
