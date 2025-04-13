package com.example.gamesapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gamesapp.data_access.Game;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ItemDetailsActivity extends AppCompatActivity {


    private TextView txtTitle;
    private TextView txtGenre;
    private TextView txtRating;
    private TextView txtDescription;
    private TextView txtQuantity;
    private TextView txtPlatform;
    private TextView txtReleaseDate;
    private TextView txtPrice;
    private ImageView imageGame;

    Button btnAddToCart;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    static final String CART = "CART";
    static final String FLAG_CART = "FLAG_CART";
    static boolean flag = false;
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_item_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupViews();
        setupPrefs();
        fillFields();
        handleAddToCart();
    }

    private void setupPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    private void handleAddToCart() {
        btnAddToCart.setOnClickListener(e -> {
            addToCart();
            Toast.makeText(this, "Game added to cart!", Toast.LENGTH_SHORT).show();
        });
    }

    private void addToCart() {
        boolean flag = prefs.getBoolean(FLAG_CART, false);
        Gson gson = new Gson();
        if (flag) {
            String json = prefs.getString(CART,"");
            Type type = new TypeToken<List<Game>>() {}.getType();
            List<Game> cart = gson.fromJson(json,type);
            cart.add(game);
            String editedJson = gson.toJson(cart);
            editor.putString(CART,editedJson);
            editor.commit();
        } else {
            List<Game> cart = new ArrayList<>();
            cart.add(game);
            String json = gson.toJson(cart);
            editor.putString(CART, json);
            editor.putBoolean(FLAG_CART, true);
            editor.commit();
        }
    }

    private void fillFields() {
        Intent intent = getIntent();
        game = (Game) intent.getSerializableExtra(MainActivity.SELECTED_GAME);
        txtTitle.setText(game.getTitle());
        txtDescription.setText("Description: " + game.getDescription());
        txtPrice.setText("Price: ₪" + game.getPrice());
        txtRating.setText("Rating: " + game.getRating());
        txtReleaseDate.setText("Release Date: " + game.getReleaseDate().toString());
        txtPlatform.setText("Platform: " + game.getPlatform());
        txtGenre.setText("Genre: " + game.getGenre());
        txtQuantity.setText("Quantity: " + game.getQuantity() + " Copies");
        imageGame.setImageDrawable(getResources().getDrawable(game.getCoverImage(), getApplicationContext().getTheme()));

    }

    private void setupViews() {
        txtDescription = findViewById(R.id.txtDescription);
        txtGenre = findViewById(R.id.txtGenre);
        txtPlatform = findViewById(R.id.txtPlatform);
        txtPrice = findViewById(R.id.txtPrice);
        txtRating = findViewById(R.id.txtRating);
        txtQuantity = findViewById(R.id.txtQuantity);
        txtReleaseDate = findViewById(R.id.txtReleaseDate);
        txtTitle = findViewById(R.id.txtTitle);
        imageGame = findViewById(R.id.imageGame);
        btnAddToCart = findViewById(R.id.btnAddToCart);
    }
}