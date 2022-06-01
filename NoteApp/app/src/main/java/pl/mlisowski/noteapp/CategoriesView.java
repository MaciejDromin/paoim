package pl.mlisowski.noteapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.room.Room;
import pl.mlisowski.noteapp.categories.domain.Categories;
import pl.mlisowski.noteapp.common.db.NotesDatabase;
import pl.mlisowski.noteapp.databinding.CategoriesViewBinding;
import pl.mlisowski.noteapp.databinding.NotesCreateBinding;

import java.util.ArrayList;
import java.util.List;

public class CategoriesView extends Fragment {

    private CategoriesViewBinding binding;

    private NotesDatabase notesDatabase;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = CategoriesViewBinding.inflate(inflater, container, false);

        ListView list = binding.allCategories;

        ArrayList<String> temp = new ArrayList<>();

        notesDatabase = Room
                .databaseBuilder(getActivity().getApplicationContext(), NotesDatabase.class, "notes")
                .allowMainThreadQueries()
                .build();

        List<Categories> allCategories = notesDatabase.categoriesDao().findAllCategories();

        for(Categories category : allCategories) {
            temp.add(category.getName());
        }

        ArrayAdapter<String> categories = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.row, temp);

        list.setAdapter(categories);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = (String) parent.getItemAtPosition(position);
                Categories selectedCategory = notesDatabase.categoriesDao().getCategory(name);
                Bundle bundle = new Bundle();
                bundle.putParcelable("category", selectedCategory);
                NavHostFragment.findNavController(CategoriesView.this)
                        .navigate(R.id.action_nav_categories_to_categoriesAdd, bundle);
            }
        });

        binding.addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(CategoriesView.this)
                        .navigate(R.id.action_nav_categories_to_categoriesAdd);
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
