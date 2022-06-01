package pl.mlisowski.noteapp;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.room.Room;
import pl.mlisowski.noteapp.categories.domain.Categories;
import pl.mlisowski.noteapp.common.db.NotesDatabase;
import pl.mlisowski.noteapp.databinding.CategoriesCreateBinding;
import pl.mlisowski.noteapp.databinding.FragmentFirstBinding;
import pl.mlisowski.noteapp.notes.domain.Note;

import java.util.Optional;

public class CategoriesAdd extends Fragment {

    private CategoriesCreateBinding binding;
    private Categories category = new Categories();
    private EditText editText;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        NotesDatabase database = Room
                .databaseBuilder(getActivity().getApplicationContext(), NotesDatabase.class, "notes")
                .allowMainThreadQueries()
                .build();

        binding = CategoriesCreateBinding.inflate(inflater, container, false);

        View view = binding.getRoot();
        editText = view.findViewById(R.id.categoryName);

        Bundle bundle = this.getArguments();
        if (bundle != null && bundle.containsKey("category")) {
            category = bundle.getParcelable("category");
            editText.setText(category.getName());
        }

        binding.categorySave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category.setName(editText.getText().toString());
                if (category.getId() == null) database.categoriesDao().insert(category);
                else database.categoriesDao().update(category);
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
