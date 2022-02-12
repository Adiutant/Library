package com.study.library;

import static androidx.core.os.BundleKt.bundleOf;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;


import com.study.library.Fragments.BooksFragment;


public class MainActivity extends AppCompatActivity implements BooksFragment.Callback {
    NavController navController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);


    }

    @Override
    public void onBooksItemClickListener(long id) {
        Bundle bundle = new Bundle();
        bundle.putLong("id",id);
        navController.navigate(R.id.action_booksFragment_to_bookFragment,bundle);
    }

    @Override
    public void onBookBackClickListener() {

        navController.navigate(R.id.action_bookFragment_to_booksFragment);
    }
}