package com.example.fluorometerapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);
        TextView textView = getSupportActionBar().getCustomView().findViewById(R.id.toolbar_title);
        textView.setText("Fluorometer App");
        Button instructionsButton = findViewById(R.id.main_instructions);
        Button detConcentrationButton = findViewById(R.id.main_determine_concentration);
        Button calibrationButton = findViewById(R.id.main_Calibration);
        //Button tagLocationInMapButton = findViewById(R.id.main_tagLocation);
        Button saveInfoAndShareButton = findViewById(R.id.main_save_info);
        Button aboutButton = findViewById(R.id.main_about);
        Button camButton = findViewById(R.id.main_camera);
        detConcentrationButton.setOnClickListener(this);
        aboutButton.setOnClickListener(this);

        instructionsButton.setOnClickListener(this);
        calibrationButton.setOnClickListener(this);
        //tagLocationInMapButton.setOnClickListener(this);
        saveInfoAndShareButton.setOnClickListener(this);



    }

    Intent intent;
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.main_instructions:
                intent = new Intent(this, InstructionsActivity.class);
                this.startActivity(intent);

//                CameraActivity ca = new CameraActivity();
//                ca.openCamera();
                break;
            case R.id.main_camera:
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intent);
                break;

            case R.id.main_determine_concentration:
                intent = new Intent(this, DetermineConcentrationActivity.class);
                this.startActivity(intent);
                break;
            case R.id.main_about:
                intent = new Intent(this, AboutActivity.class);
                this.startActivity(intent);
                break;
            case R.id.main_Calibration:
                intent = new Intent(this, CalibrationActivity.class);
                this.startActivity(intent);
                break;



        }
    }
}

