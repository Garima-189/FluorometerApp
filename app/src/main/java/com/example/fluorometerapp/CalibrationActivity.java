package com.example.fluorometerapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalibrationActivity extends AppCompatActivity{

    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    CalibrationGraphFragment calibrationGraphFragment;
//Fragment calibrationPlotGraphFragment = findViewById(R.id.calibration_fragment);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibration);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);
        TextView textView = getSupportActionBar().getCustomView().findViewById(R.id.toolbar_title);
        textView.setText("Calibration");

        fragmentManager = getSupportFragmentManager();

        calibrationGraphFragment = new CalibrationGraphFragment();
        fragmentTransaction = fragmentManager.beginTransaction();

        CalibrationValuesFragment calibrationValuesFragment = new CalibrationValuesFragment();
        fragmentTransaction.replace(R.id.calibration_fragment_container, calibrationValuesFragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();



    }

    public void onButtonClick(View view){
        Button button=(Button)view;
        button.setText("something");
    }

}