package com.example.fluorometerapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalibrationValuesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalibrationValuesFragment extends Fragment{
    View inflatedView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int CAMERA_REQUEST_CODE=20;
    SharedPreferences sharedPreferences;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Double i1=0.0,i2=0.0,i3=0.0,i4=0.0,i5=0.0,v1=0.0,v2=0.0,v3=0.0,v4=0.0,v5=0.0;
    public Double cameraReturnValue=0.0;
    public CalibrationValuesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalibrationValuesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalibrationValuesFragment newInstance(String param1, String param2) {
        CalibrationValuesFragment fragment = new CalibrationValuesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences=getActivity().getSharedPreferences("com.example.notes",MODE_PRIVATE);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calibration_values, container, false);
        Button button=rootView.findViewById(R.id.calibration_plotCurveButton);
        Button cameraButton=rootView.findViewById(R.id.calibration_cameraButton);
        Button linRegButton=rootView.findViewById(R.id.calibration_regressionEquationButton);
        final EditText e1,e2,e3,e4,e5;
        final Button ev1,ev2,ev3,ev4,ev5;
        e1 = rootView.findViewById(R.id.ed1);
        e2 = rootView.findViewById(R.id.ed2);
        e3 = rootView.findViewById(R.id.ed3);
        e4 = rootView.findViewById(R.id.ed4);
        e5 = rootView.findViewById(R.id.ed5);
        ev1 = rootView.findViewById(R.id.vd1);
        ev2 = rootView.findViewById(R.id.vd2);
        ev3 = rootView.findViewById(R.id.vd3);
        ev4 = rootView.findViewById(R.id.vd4);
        ev5 = rootView.findViewById(R.id.vd5);
        ev1.setText(""+v1);
        ev2.setText(""+v2);
        ev3.setText(""+v3);
        ev4.setText(""+v4);
        ev5.setText(""+v5);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1 = Double.parseDouble(e1.getText().toString());
                i2 = Double.parseDouble(e2.getText().toString());
                i3 = Double.parseDouble(e3.getText().toString());
                i4 = Double.parseDouble(e4.getText().toString());
                i5 = Double.parseDouble(e5.getText().toString());



                CalibrationGraphFragment calibrationGraphFragment = new CalibrationGraphFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.calibration_fragment_container, new CalibrationGraphFragment(i1,i2,i3,i4,i5,v1,v2,v3,v4,v5)).addToBackStack(null).commit();

            }
        });

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CameraActivity.class);
                startActivityForResult(intent,CAMERA_REQUEST_CODE);
            }
        });

        ev1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b=(Button) view;
                v1=cameraReturnValue;
                b.setText(""+cameraReturnValue);
            }
        });

        ev2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b=(Button) view;
                v2=cameraReturnValue;
                b.setText(""+cameraReturnValue);
            }
        });

        ev3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b=(Button) view;
                v3=cameraReturnValue;
                b.setText(""+cameraReturnValue);
            }
        });

        ev4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b=(Button) view;
                v4=cameraReturnValue;
                b.setText(""+cameraReturnValue);
            }
        });

        ev5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b=(Button) view;
                v5=cameraReturnValue;
                b.setText(""+cameraReturnValue);
            }
        });

        linRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double intercept, slope;
                double r2;
                double svar0, svar1;
                double[] x=new double[5];
                double[] y=new double[5];
                i1 = Double.parseDouble(e1.getText().toString());
                i2 = Double.parseDouble(e2.getText().toString());
                i3 = Double.parseDouble(e3.getText().toString());
                i4 = Double.parseDouble(e4.getText().toString());
                i5 = Double.parseDouble(e5.getText().toString());
                x[0]=v1;
                x[1]=v2;
                x[2]=v3;
                x[3]=v4;
                x[4]=v5;
                y[0]=i1;//concentration is y value because we will enter the intensity in prediction
                y[1]=i2;
                y[2]=i3;
                y[3]=i4;
                y[4]=i5;
                int n = x.length;

                // first pass
                double sumx = 0.0, sumy = 0.0, sumx2 = 0.0;
                for (int i = 0; i < n; i++) {
                    sumx  += x[i];
                    sumx2 += x[i]*x[i];
                    sumy  += y[i];
                }
                double xbar = sumx / n;
                double ybar = sumy / n;

                // second pass: compute summary statistics
                double xxbar = 0.0, yybar = 0.0, xybar = 0.0;
                for (int i = 0; i < n; i++) {
                    xxbar += (x[i] - xbar) * (x[i] - xbar);
                    yybar += (y[i] - ybar) * (y[i] - ybar);
                    xybar += (x[i] - xbar) * (y[i] - ybar);
                }
                slope  = xybar / xxbar;
                intercept = ybar - slope * xbar;

                Toast.makeText(getActivity(), "Slope,intercept:"+String.format("%.2f",slope)+","+String.format("%.2f",intercept), Toast.LENGTH_LONG).show();
                sharedPreferences.edit().putString("slope",""+slope).apply();
                sharedPreferences.edit().putString("intercept",""+intercept).apply();
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CAMERA_REQUEST_CODE && resultCode== Activity.RESULT_OK){
            cameraReturnValue=data.getDoubleExtra("intensity",0.0);
            Toast.makeText(getActivity(), "Intensity:"+cameraReturnValue, Toast.LENGTH_SHORT).show();
        }
        else if (resultCode==Activity.RESULT_CANCELED){
            Toast.makeText(getActivity(), "Something went wrong, please try again", Toast.LENGTH_SHORT).show();
        }
    }
}