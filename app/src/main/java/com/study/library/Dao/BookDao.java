package com.study.library.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Insert;
import com.study.library.Models.Book;

import java.util.List;

@Dao
public
interface BookDao {
    @Query("SELECT * FROM Book")
    LiveData<List<Book>> getAllBooks();
    @Query("SELECT * FROM Book WHERE id = :bookId")
    Book getBookById(int bookId);
    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    void addBook(List<Book> books);
}