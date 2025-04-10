package com.example.gamesapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gamesapp.data_access.Game;
import com.example.gamesapp.data_access.GameDAFactory;
import com.example.gamesapp.data_access.IGameDA;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private EditText editSearch;
    private Button btnSearch;
    private Button btnFilters;
    private Button btnCart;

    private ListView listGames;

    Game filters;
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
        btnFilters.setOnClickListener(e->{ // here we have to display a popup filters menu

        });
    }

    private void handleSearch(){
        btnSearch.setOnClickListener(e->{
            String gameTitle = editSearch.getText().toString().trim();
//            Log.i("query", searchQuery);
            IGameDA gameDA = GameDAFactory.getGameDA();
            if(filters == null) {
                if (gameTitle.equals("")) {
                    List<Game> list = gameDA.getGames();
                    ArrayAdapter<Game> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, list);
                    listGames.setAdapter(adapter);
                } else {
                    List<Game> list = gameDA.getGamesByTitle(gameTitle);
                    ArrayAdapter<Game> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, list);
                    listGames.setAdapter(adapter);
                }
            }else{
                List<Game> list = gameDA.getGamesByFilters(filters);
                ArrayAdapter<Game> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, list);
                listGames.setAdapter(adapter);
            }
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
        editSearch = findViewById(R.id.editSearch);
    }
}