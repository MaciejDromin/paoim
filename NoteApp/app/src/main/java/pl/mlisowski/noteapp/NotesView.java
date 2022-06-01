package pl.mlisowski.noteapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.room.Room;
import pl.mlisowski.noteapp.categories.domain.Categories;
import pl.mlisowski.noteapp.common.db.NotesDatabase;
import pl.mlisowski.noteapp.databinding.CategoriesViewBinding;
import pl.mlisowski.noteapp.databinding.NotesViewBinding;
import pl.mlisowski.noteapp.notes.domain.Note;
import pl.mlisowski.noteapp.notes.domain.NotesByCategories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotesView extends Fragment {

    private NotesViewBinding binding;

    private NotesDatabase notesDatabase;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = NotesViewBinding.inflate(inflater, container, false);

        ExpandableListView list = binding.allNotes;

        notesDatabase = Room
                .databaseBuilder(getActivity().getApplicationContext(), NotesDatabase.class, "notes")
                .allowMainThreadQueries()
                .build();

        List<NotesByCategories> nbc = notesDatabase.notesByCategoriesDao().findAllNotesByCategories();

        Map<String, List<String>> data = getDataForExpandableList(nbc);
        List<String> expandableTitleList = new ArrayList<>(data.keySet());
        CustomizedExpandableListAdapter expandableListAdapter = new CustomizedExpandableListAdapter(getActivity().getApplicationContext(), expandableTitleList, data);
        list.setAdapter(expandableListAdapter);

        list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String name = data.get((String)parent.getItemAtPosition(groupPosition)).get(childPosition);
                Note selectedNote = notesDatabase.noteDao().getNoteByTitle(name);
                Bundle bundle = new Bundle();
                bundle.putParcelable("note", selectedNote);
                NavHostFragment.findNavController(NotesView.this)
                        .navigate(R.id.action_nav_notes_to_noteAdd, bundle);
                return true;
            }
        });

        binding.addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(NotesView.this)
                        .navigate(R.id.action_nav_notes_to_noteAdd);
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

    private Map<String, List<String>> getDataForExpandableList(List<NotesByCategories> notesByCategories) {
        Map<String, List<String>> ret = new HashMap<>();
        for(NotesByCategories notesByCat : notesByCategories) {
            List<String> notesNames = new ArrayList<>();
            for(Note note : notesByCat.getNoteList()) notesNames.add(note.getTitle());
            ret.put(notesByCat.getCategories().getName(), notesNames);
        }
        return ret;
    }

}
