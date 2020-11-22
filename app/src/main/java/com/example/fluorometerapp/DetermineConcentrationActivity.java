package com.example.fluorometerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DetermineConcentrationActivity extends AppCompatActivity {
    private double slopeVal;
    private double interceptVal;
    SharedPreferences sharedPreferences;
    private static final int CAMERA_REQUEST_CODE=20;
    public Double cameraReturnValue=0.0;
    TextView slopeIntercept;
    Button button;
    TextView intensity;
    TextView concentration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_determine_concentration);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);
        TextView textView = getSupportActionBar().getCustomView().findViewById(R.id.toolbar_title);
        textView.setText("Concentration");

        slopeIntercept=findViewById(R.id.slopeIntercept);
        button= findViewById(R.id.determineConc);
        intensity=findViewById(R.id.intensity);
        concentration=findViewById(R.id.concentration);
        sharedPreferences=this.getSharedPreferences("com.example.notes",MODE_PRIVATE);
        String m=sharedPreferences.getString("slope","0.0");
        String c=sharedPreferences.getString("intercept","0.0");

        slopeVal=Double.parseDouble(m);
        interceptVal=Double.parseDouble(c);
        if (slopeVal==0 && interceptVal==0)
            slopeIntercept.setText("Slope, intercept: 0,0\n(make sure you have calibrated your device)");
        else{
            slopeIntercept.setText("Slope, intercept:"+String.format("%.2f",slopeVal)+","+String.format("%.2f",interceptVal));
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetermineConcentrationActivity.this, CameraActivity.class);
                startActivityForResult(intent,CAMERA_REQUEST_CODE);
            }
        });


    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CAMERA_REQUEST_CODE && resultCode== Activity.RESULT_OK){
            cameraReturnValue=data.getDoubleExtra("intensity",0.0);
            Toast.makeText(DetermineConcentrationActivity.this, "Intensity:"+cameraReturnValue, Toast.LENGTH_SHORT).show();
            double conc=(slopeVal*cameraReturnValue)+interceptVal;
            intensity.setText("Intensity= " + String.format("%.2f",cameraReturnValue)+" lux");
            concentration.setText("Concentration= "+String.format("%.2f",conc)+" g/L");
        }
        else if (resultCode==Activity.RESULT_CANCELED){
            Toast.makeText(DetermineConcentrationActivity.this, "Something went wrong, please try again", Toast.LENGTH_SHORT).show();
        }
    }
}