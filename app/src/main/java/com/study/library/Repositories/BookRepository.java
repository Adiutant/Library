package com.study.library.Repositories;

import androidx.lifecycle.LiveData;

import com.study.library.Dao.BookDao;
import com.study.library.Models.Book;

import java.util.List;

public class BookRepository{
    BookDao m_bookDao;
    public LiveData<List<Book>> data;
    public BookRepository(BookDao bookDao)
    {
        m_bookDao = bookDao;
        data = m_bookDao.getAllBooks();
    }
    public void addBooks(List<Book> books)
    {
        m_bookDao.addBook(books);
    }
    public Book getBookById(int bookId){return m_bookDao.getBookById(bookId);}
    public LiveData<List<Book>> loadData()
    {
        return m_bookDao.getAllBooks();
    }
}
