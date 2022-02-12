package com.study.library.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.study.library.App;
import com.study.library.AppDatabase;
import com.study.library.Models.Author;
import com.study.library.Models.Book;
import com.study.library.R;
import com.study.library.Repositories.BookRepository;
import com.study.library.ViewModels.BookViewModel;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookFragment extends Fragment {
    private Book currentBook;
    private AppDatabase db;
    private   BookRepository bookRepository;
    private BookViewModel bookViewModel;
    private TextView bookName;
    private TextView bookInfo;
    private TextView authorInfo;
    private Button changeButton;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BookFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookFragment newInstance(String param1, String param2) {
        BookFragment fragment = new BookFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();

        bookName = getView().findViewById(R.id.bookName);
        bookInfo = getView().findViewById(R.id.bookInfo);
        authorInfo = getView().findViewById(R.id.authorInfo);
        changeButton = getView().findViewById(R.id.changeButton);
        bookName.setText(currentBook.name);
        bookInfo.setText(currentBook.description);
        Author currentAuthor = db.authorDao().getAuthorById(currentBook.authorId);
        authorInfo.setText(getText(R.string.author_header).toString() + " " + currentAuthor.name);

        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentBook.description = bookInfo.getText().toString();
                ArrayList<Book> books_to_add = new ArrayList<Book>();
                books_to_add.add(currentBook);
                Toast.makeText(getContext(),"Описание сохранено", Toast.LENGTH_LONG).show();
                bookRepository.addBooks(books_to_add);
            }
        });


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        long id  = getArguments().getLong("id");
        db = App.getInstance().getDatabase();
        bookRepository = new BookRepository(db.bookDao());

        bookViewModel = new BookViewModel(bookRepository);

        currentBook =  bookViewModel.getBookById((int) id);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book, container, false);
    }
}