package com.study.library.Fragments;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.study.library.Adapters.BookAdapter;
import com.study.library.App;
import com.study.library.AppDatabase;
import com.study.library.Models.Author;
import com.study.library.Models.Book;
import com.study.library.R;
import com.study.library.Repositories.BookRepository;
import com.study.library.ViewModels.BookViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BooksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BooksFragment extends Fragment {
    public interface Callback {
        public void onBooksItemClickListener(long id);
        public void onBookBackClickListener();
    }
    public Callback callback;
    public ListView booksList;
    public AppDatabase db;
    public  BookRepository bookRepository;
    public BookViewModel bookViewModel;
    public BookAdapter bookAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BooksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BooksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BooksFragment newInstance(String param1, String param2) {
        BooksFragment fragment = new BooksFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            callback = (Callback) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement onSomeEventListener");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        booksList = getView().findViewById(R.id.booksList);
        db = App.getInstance().getDatabase();
        bookRepository = new BookRepository(db.bookDao());

        bookViewModel = new BookViewModel(bookRepository);
        LiveData<List<Book>> books = bookViewModel.getData();
        ArrayList<Book> a_books = (ArrayList<Book>) books.getValue();
        if (a_books != null){
            bookAdapter = new BookAdapter(getContext(), a_books);
        }else{

            bookAdapter = new BookAdapter(getContext(), new ArrayList<Book>());
        }

        booksList.setAdapter(bookAdapter);
        booksList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               callback.onBooksItemClickListener(l);

            }
        });

        bookViewModel.getData().observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(@Nullable List<Book> books) {
                if(books != null) {
                    bookAdapter.setBooks(books);
                }else {
                    bookAdapter.setBooks(new ArrayList<Book>());
                }
            }
        });
        if (bookViewModel.getBookById(0) == null) {
        ArrayList<Book> books_to_add = new ArrayList<Book>();
        books_to_add.add(new Book(0,"War and peace","none",1));

        bookRepository.addBooks(books_to_add);

        ArrayList<Author> authors_to_add = new ArrayList<Author>();
        authors_to_add.add(new Author(0,"A.S Pushkin","D06M06Y1799"));
        authors_to_add.add(new Author(1,"L.N Tolstoy","D09M09Y1828"));

        db.authorDao().addAuthor(authors_to_add);
        }


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_books, container, false);
    }
}