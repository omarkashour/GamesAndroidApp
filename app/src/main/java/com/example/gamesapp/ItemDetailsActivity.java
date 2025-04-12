package com.example.gamesapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gamesapp.data_access.Game;

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
        fillFields();
    }

    private void fillFields() {
        Intent intent = getIntent();
        Game game = (Game) intent.getSerializableExtra(MainActivity.SELECTED_GAME);
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
    }
}