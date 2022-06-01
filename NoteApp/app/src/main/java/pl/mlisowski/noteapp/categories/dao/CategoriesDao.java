package pl.mlisowski.noteapp.categories.dao;

import androidx.room.*;
import pl.mlisowski.noteapp.categories.domain.Categories;

import java.util.List;

@Dao
public interface CategoriesDao {

    @Query("SELECT * FROM categories")
    List<Categories> findAllCategories();

    @Query("SELECT name FROM categories WHERE id = (:categoryId)")
    String getCategoryNameById(Long categoryId);

    @Query("SELECT id FROM categories WHERE name = (:categoryName)")
    Long getCategoryIdByName(String categoryName);

    @Query("SELECT * FROM categories where name = (:categoryName)")
    Categories getCategory(String categoryName);

    @Insert
    void insert(Categories categories);

    @Update
    void update(Categories categories);

    @Delete
    void delete(Categories categories);
}
