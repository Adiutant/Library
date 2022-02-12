package com.study.library.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Author")
public class Author {
    @PrimaryKey @NotNull  public int id;
    @NotNull public String name;
    @NotNull public String birthDate;
    public Author(int l_id, @NonNull String l_name, @NonNull String l_birthdate)
    {
        id = l_id;
        name = l_name;
        birthDate = l_birthdate;

    }
    public Author(){};
}