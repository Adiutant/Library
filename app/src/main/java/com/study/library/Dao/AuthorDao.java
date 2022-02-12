package com.study.library.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.study.library.Models.Author;
import com.study.library.Models.Book;

import java.util.List;
@Dao
public interface AuthorDao {
    @Query("SELECT * FROM Author")
    LiveData<List<Author>> getAllAuthors();
    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    void addAuthor(List<Author> authors);
    @Query("SELECT * FROM Author WHERE id = :authorId")
    Author getAuthorById(int authorId);
}
