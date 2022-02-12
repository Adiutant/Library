package com.study.library;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.study.library.Dao.AuthorDao;
import com.study.library.Dao.BookDao;
import com.study.library.Models.Author;
import com.study.library.Models.Book;

@Database(entities = {Author.class, Book.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract BookDao bookDao();
    public abstract AuthorDao authorDao();
}
