package com.study.library.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;


@Entity(tableName = "Book")
public class Book {
    public Book(int l_id, @NonNull String l_name, @NonNull String l_description, int l_authorId)
    {
        id = l_id;
        name = l_name;
        description = l_description;
        authorId = l_authorId;

    }
    public Book(){};
     @PrimaryKey @NotNull public int id;
     @NotNull public String name;
    @NotNull public String description;
    @NotNull public int authorId;
}