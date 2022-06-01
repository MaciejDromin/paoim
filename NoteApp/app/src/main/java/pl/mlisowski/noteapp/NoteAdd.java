package pl.mlisowski.noteapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.room.Room;
import pl.mlisowski.noteapp.categories.domain.Categories;
import pl.mlisowski.noteapp.common.db.NotesDatabase;
import pl.mlisowski.noteapp.databinding.NotesCreateBinding;
import pl.mlisowski.noteapp.notes.domain.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteAdd extends Fragment {

    private NotesCreateBinding binding;

    private Note note = new Note();

    private EditText noteTitle;
    private EditText noteContent;
    private Spinner spinner;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = NotesCreateBinding.inflate(inflater, container, false);

        NotesDatabase database = Room
                .databaseBuilder(getActivity().getApplicationContext(), NotesDatabase.class, "notes")
                .allowMainThreadQueries()
                .build();

        View view = binding.getRoot();
        noteTitle = view.findViewById(R.id.noteTitle);
        noteContent = view.findViewById(R.id.noteContent);
        spinner = view.findViewById(R.id.noteCategory);

        List<String> temp = new ArrayList<>();

        List<Categories> allCategories = database.categoriesDao().findAllCategories();

        for(Categories category : allCategories) {
            temp.add(category.getName());
        }

        ArrayAdapter<String> categories = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.row, temp);

        spinner.setAdapter(categories);
        spinner.setSelection(categories.getPosition(temp.get(0)));

        Bundle bundle = this.getArguments();
        if (bundle != null && bundle.containsKey("note")) {
            note = bundle.getParcelable("note");
            noteTitle.setText(note.getTitle());
            noteContent.setText(note.getText());
            spinner.setSelection(categories.getPosition(database.categoriesDao().getCategoryNameById(note.getCategoryId())));
        }

        binding.noteSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setTitle(noteTitle.getText().toString());
                note.setText(noteContent.getText().toString());
                note.setCategoryId(database.categoriesDao().getCategoryIdByName((String)spinner.getSelectedItem()));
                if (note.getId() == null) database.noteDao().insert(note);
                else database.noteDao().update(note);
                getActivity().onBackPressed();
            }
        });

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
