package com.example.gamesapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    private EditText editSearch;
    private Button btnSearch;
    private Button btnFilters;
    private Button btnCart;

    private ListView listGames;


    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // initialize data in shared preferences if its not initialized
        // load the list view

        setupViews();
        setupPrefs();
        checkPrefs();
        handleFilters();
        handleCart();
        handleSearch();
        

    }

    private void checkPrefs() {

    }

    private void handleFilters(){
        btnFilters.setOnClickListener(e->{

        });
    }

    private void handleSearch(){
        btnSearch.setOnClickListener(e->{

        });
    }

    private void handleCart(){
        btnCart.setOnClickListener(e->{

        });
    }

    private void setupPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    private void setupViews() {
        btnSearch = findViewById(R.id.btnSearch);
        btnFilters = findViewById(R.id.btnFilters);
        btnCart = findViewById(R.id.btnCart);
        listGames = findViewById(R.id.listGames);
    }
}