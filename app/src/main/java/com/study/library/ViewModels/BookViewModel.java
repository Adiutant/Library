package com.study.library.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.study.library.Models.Book;
import com.study.library.Repositories.BookRepository;

import java.util.List;

public class BookViewModel extends ViewModel {
    BookRepository m_bookRepository;
    public LiveData<List<Book>> data;
    public BookViewModel(BookRepository bookRepository)
    {
        m_bookRepository = bookRepository;
        data = m_bookRepository.loadData();
    }
    public LiveData<List<Book>> getData()
    {
        data = m_bookRepository.loadData();

        return data;
    }
    public Book getBookById(int bookId)
    {


        return m_bookRepository.getBookById(bookId);
    }


}
