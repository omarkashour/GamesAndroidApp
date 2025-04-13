package com.example.gamesapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

import java.lang.reflect.Type;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    private EditText editName;
    private EditText editEmail;
    private EditText editAddress;
    private EditText editPhone;
    private EditText editCC;
    private EditText editExpiry;
    private EditText editCvv;
    private Button btnCancel;
    private Button btnCheckout;
    private TextView txtTitle;
    private int total = 0;

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_checkout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupViews();
        setupPrefs();
        handleCancel();
        handleIntent();
        handleCheckout();
    }

    private void handleCheckout() {
        btnCheckout.setOnClickListener(e->{
            String name = editName.getText().toString().trim();
            String email = editEmail.getText().toString().trim();
            String address = editAddress.getText().toString().trim();
            String phone = editPhone.getText().toString().trim();
            String cc = editCC.getText().toString().trim();
            String expiry = editExpiry.getText().toString().trim();
            String cvv = editCvv.getText().toString().trim();
            Gson g = new Gson();

            List<Game> cart = getCart();
            String json = prefs.getString(MainActivity.GAMES,"");
            Type type = new TypeToken<List<Game>>() {}.getType();
            List<Game> games = g.fromJson(json, type);
            if(cart != null){
                for(Game cartGame : cart) {
                    for (Game game : games) {
                        if (cartGame.getTitle().equals(game.getTitle())) {
                            game.setQuantity(game.getQuantity() - 1);
                        }
                    }
                }
            }
            String json2 = g.toJson(games);
            editor.putString(MainActivity.GAMES,json2);
            editor.commit();
            Toast.makeText(this, "Checkout Successful!", Toast.LENGTH_SHORT).show();
            this.finish();

        });
    }


    private List<Game> getCart() {
        boolean flag = prefs.getBoolean(ItemDetailsActivity.FLAG_CART, false);
        Gson gson = new Gson();
        if (flag) {
            String json = prefs.getString(ItemDetailsActivity.CART, "");
            Type type = new TypeToken<List<Game>>() {
            }.getType();
            List<Game> cart = gson.fromJson(json, type);
            return cart;
        }
        return null;
    }

    private void handleIntent() {
        Intent intent = getIntent();
        total = intent.getIntExtra(ShoppingCartActivity.TOTAL,0);
        txtTitle.setText("Checkout (₪"+total+")");
    }

    private void handleCancel() {
        btnCancel.setOnClickListener(e->{
            this.finish();
        });
    }

    private void setupViews() {
        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editAddress = findViewById(R.id.editAddress);
        editPhone = findViewById(R.id.editPhone);
        editCC = findViewById(R.id.editCC);
        editExpiry = findViewById(R.id.editExpiry);
        editCvv = findViewById(R.id.editCvv);
        btnCancel = findViewById(R.id.btnCancel);
        btnCheckout = findViewById(R.id.btnCheckout);
        txtTitle = findViewById(R.id.txtTitle);
    }

    private void setupPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }
}