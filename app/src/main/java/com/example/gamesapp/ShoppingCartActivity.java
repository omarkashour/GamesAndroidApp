package com.example.gamesapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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

public class ShoppingCartActivity extends AppCompatActivity {

    private Button btnClearCart;
    private Button btnCheckout;
    private ListView listCart;
    private TextView txtTotalPrice;
    private int total = 0;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shopping_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupViews();
        setupPrefs();
        checkPrefs();
        handleClearCart();
        handleCheckout();
    }

    private void handleCheckout() {
        btnCheckout.setOnClickListener(e->{
            Intent intent = new Intent(ShoppingCartActivity.this, CheckoutActivity.class);
//            intent.putExtra()
            startActivity(intent);
        });
    }

    private void handleClearCart() {
        btnClearCart.setOnClickListener(e->{
            boolean flag = prefs.getBoolean(ItemDetailsActivity.FLAG_CART, false);
            Gson gson = new Gson();
            if (flag) {
                List<Game> cart = new ArrayList<>();
                String json = gson.toJson(cart);
                editor.putString(ItemDetailsActivity.CART, json);
                editor.commit();
                fillView(cart);
                updateTotalPrice(cart);
            }
        });
    }

    private void setupPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    private void checkPrefs() {
        boolean flag = prefs.getBoolean(ItemDetailsActivity.FLAG_CART, false);
        Gson gson = new Gson();
        if (flag) {
            String json = prefs.getString(ItemDetailsActivity.CART, "");
            Type type = new TypeToken<List<Game>>() {
            }.getType();
            List<Game> cart = gson.fromJson(json, type);
                fillView(cart);
                updateTotalPrice(cart);

        }
    }

    private void updateTotalPrice(List<Game> cart) {
        total = 0;
        for(Game g : cart){
            total+=g.getPrice();
        }
        txtTotalPrice.setText("Total: ₪" + total);
    }

    private void fillView(List<Game> cart) {
        ArrayAdapter<Game> adapter = new ArrayAdapter<>(ShoppingCartActivity.this, android.R.layout.simple_list_item_1, cart);
        listCart.setAdapter(adapter);
    }

    private void setupViews() {
        btnCheckout = findViewById(R.id.btnCheckout);
        btnClearCart = findViewById(R.id.btnClearCart);
        listCart = findViewById(R.id.listCart);
        txtTotalPrice = findViewById(R.id.txtTotalPrice);
    }
}