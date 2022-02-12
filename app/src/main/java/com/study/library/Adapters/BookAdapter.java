package com.study.library.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.study.library.R;
import com.study.library.Models.Book;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {

    public void setBooks(List<Book> books) {
        clear();
        addAll(books);
        notifyDataSetChanged();
    }

    public BookAdapter(Context context, List<Book> books) {
        super(context, 0, books);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Book book = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        // Lookup view for data population
        TextView bookName = (TextView) convertView.findViewById(R.id.booksListItem);
        // Populate the data into the template view using the data object
        bookName.setText(book.name);
        // Return the completed view to render on screen
        return convertView;
    }}