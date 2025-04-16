package com.example.gamesapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gamesapp.data_access.Game;

public class FiltersActivity extends AppCompatActivity {

    private Spinner spinnerMinRating;
    private CheckBox checkboxAction;
    private CheckBox checkboxAdventure;
    private CheckBox checkboxRpg;
    private CheckBox checkboxFamily;
    private CheckBox checkboxPhysics;
    private CheckBox checkboxSandbox;
    private CheckBox checkboxSimulation;
    private CheckBox checkboxEducation;

    private RadioGroup radioGroupPlatform;
    private RadioButton radioPC;
    private RadioButton radioPlaystation;
    private RadioButton radioSwitch;
    private RadioButton radioXbox;

    private Button btnClearFilters;
    private Button btnApplyFilters;

    static final String FILTERS = "FILTERS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_filters);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setupViews();
        handleApply();
        handleClear();
    }

    private void handleApply() {
        btnApplyFilters.setOnClickListener(e -> {
            Game filters = new Game();

            StringBuilder genresBuilder = new StringBuilder();

            if (checkboxAction.isChecked()) genresBuilder.append("Action,");
            if (checkboxAdventure.isChecked()) genresBuilder.append("Adventure,");
            if (checkboxRpg.isChecked()) genresBuilder.append("RPG,");
            if (checkboxFamily.isChecked()) genresBuilder.append("Family,");
            if (checkboxPhysics.isChecked()) genresBuilder.append("Physics,");
            if (checkboxSandbox.isChecked()) genresBuilder.append("Sandbox,");
            if (checkboxSimulation.isChecked()) genresBuilder.append("Simulation,");
            if (checkboxEducation.isChecked()) genresBuilder.append("Education,");

            String genres = genresBuilder.toString();
            if (genres.endsWith(",")) {
                genres = genres.substring(0, genres.length() - 1);
            }

            filters.setGenre(genres);

            int selectedPlatformId = radioGroupPlatform.getCheckedRadioButtonId();
            if (selectedPlatformId == radioPC.getId()) {
                filters.setPlatform(Game.PC);
            } else if (selectedPlatformId == radioPlaystation.getId()) {
                filters.setPlatform(Game.PLAYSTATION);
            } else if (selectedPlatformId == radioSwitch.getId()) {
                filters.setPlatform(Game.NINTENDO_SWITCH);
            } else if (selectedPlatformId == radioXbox.getId()) {
                filters.setPlatform(Game.XBOX);
            }

            Integer minRating = (Integer) spinnerMinRating.getSelectedItem();
            filters.setRating(minRating);

            Intent intent = new Intent(FiltersActivity.this , MainActivity.class);
            intent.putExtra(FILTERS, filters);
            startActivity(intent);

        });
    }

    private void handleClear() {
        btnClearFilters.setOnClickListener(e->{
            Intent intent = new Intent(FiltersActivity.this , MainActivity.class);
            startActivity(intent);
        });
    }



    private void setupViews() {

        spinnerMinRating = findViewById(R.id.spinnerMinRating);
        Integer[] ratings = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ratings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMinRating.setAdapter(adapter);

        checkboxAction = findViewById(R.id.checkbox_action);
        checkboxAdventure = findViewById(R.id.checkbox_adventure);
        checkboxRpg = findViewById(R.id.checkbox_rpg);
        checkboxFamily = findViewById(R.id.checkbox_family);
        checkboxPhysics = findViewById(R.id.checkbox_physics);
        checkboxSandbox = findViewById(R.id.checkbox_sandbox);
        checkboxSimulation = findViewById(R.id.checkbox_simulation);
        checkboxEducation = findViewById(R.id.checkbox_education);


        radioGroupPlatform = findViewById(R.id.radioGroupPlatform);
        radioPC = findViewById(R.id.radioPC);
        radioPlaystation = findViewById(R.id.radioPlaystation);
        radioSwitch = findViewById(R.id.radioSwitch);
        radioXbox = findViewById(R.id.radioXbox);


        btnClearFilters = findViewById(R.id.btnClearFilters);
        btnApplyFilters = findViewById(R.id.btnApplyFilters);
    }
}