package com.example.gamesapp;

import android.os.Bundle;
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

    private Spinner spinnerReleasedAfter;
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
        btnApplyFilters.setOnClickListener(e->{
            Game filters = new Game();
        });
    }

    private void handleClear() {
        btnClearFilters.setOnClickListener(e->{

        });
    }



    private void setupViews() {

        spinnerMinRating = findViewById(R.id.spinnerMinRating);


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