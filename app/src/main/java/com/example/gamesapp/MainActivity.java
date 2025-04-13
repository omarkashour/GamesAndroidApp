package com.example.gamesapp;

import android.content.Intent;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Console;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
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
    static final String SELECTED_GAME = "SELECTED_GAME";
    static final String FLAG = "FLAG";
    static final String GAMES = "GAMES";
    static boolean flag = false;
    private List<Game> games = new ArrayList<>();


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
        handleItemClick();
        

    }

    private void handleItemClick() {

        listGames.setOnItemClickListener((adapterView, view, i, l) -> {
            Game selectedGame = (Game) adapterView.getItemAtPosition(i);
            Intent intent = new Intent(MainActivity.this, ItemDetailsActivity.class);
            intent.putExtra(SELECTED_GAME, selectedGame);
            startActivity(intent);
        });
    }

    private void checkPrefs() {
        flag = prefs.getBoolean(FLAG, false);
        Gson g = new Gson();
        if(flag){
            String json = prefs.getString(GAMES,"");
            Type type = new TypeToken<List<Game>>() {}.getType();
            games = g.fromJson(json, type);
            Log.i("data_debug", "Read data");
        }else{
            IGameDA gameDA = GameDAFactory.getGameDA();
            String json = g.toJson(gameDA.getGames());
            editor.putString("GAMES",json);
            editor.putBoolean(FLAG, true);
            editor.commit();
            Log.i("data_debug", "Wrote data");
            checkPrefs(); // to read the written data from sharedpreferences
        }
    }

    private void handleFilters(){
        btnFilters.setOnClickListener(e->{ // here we have to display a popup filters menu
            Intent intent = new Intent(MainActivity.this, FiltersActivity.class);
            startActivity(intent);
        });
    }

    private void handleSearch(){
        btnSearch.setOnClickListener(e->{
            String gameTitle = editSearch.getText().toString().trim();
//            Log.i("query", searchQuery);
            if(filters == null) {
                if (gameTitle.equals("")) {
                    ArrayAdapter<Game> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, games);
                    listGames.setAdapter(adapter);
                } else {
                    List<Game> gamesByTitle = getGamesByTitle(gameTitle);
                    ArrayAdapter<Game> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, gamesByTitle);
                    listGames.setAdapter(adapter);
                }
            }else{
                ArrayAdapter<Game> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, games);
                listGames.setAdapter(adapter);
            }
        });
    }

    private void handleCart(){
        btnCart.setOnClickListener(e->{
            Intent intent = new Intent(MainActivity.this, ShoppingCartActivity.class);
            startActivity(intent);
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

    public List<Game> getGamesByTitle(String title) {
        List<Game> result = new ArrayList<>();
        for (Game b : games) {
            if (b.getTitle().toLowerCase().contains(title.toLowerCase()))
                result.add(b);
        }
        return result;
    }

    public List<Game> getGamesByFilters(Game filters) {
        List<Game> result = new ArrayList<>();
        for (Game g : games) {
            // 1) title substring match?
            if (filters.getTitle() != null &&
                    !g.getTitle().toLowerCase().contains(filters.getTitle().toLowerCase())) {
                continue;
            }

            // 2) exact genre match?
            if (filters.getGenre() != null &&
                    !g.getGenre().equalsIgnoreCase(filters.getGenre())) {
                continue;
            }

            // 3) exact platform match?
            if (filters.getPlatform() != null &&
                    !g.getPlatform().equalsIgnoreCase(filters.getPlatform())) {
                continue;
            }

            // 4) minimum rating?
            if (filters.getRating() > 0 &&
                    g.getRating() < filters.getRating()) {
                continue;
            }

            // 5) maximum price?
            if (filters.getPrice() > 0 &&
                    g.getPrice() > filters.getPrice()) {
                continue;
            }

            // 6) release date range?
            Date from = filters.getReleaseDate();
            if (from != null && g.getReleaseDate().before(from)) {
                continue;
            }

            // if we get here, the current game passed all active filters
            result.add(g);
        }

        return result;
    }

}