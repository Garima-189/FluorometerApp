package com.example.fluorometerapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Double i1=0.0,i2=0.0,i3=0.0,i4=0.0,i5=0.0,v1=0.0,v2=0.0,v3=0.0,v4=0.0,v5=0.0;

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



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calibration_values, container, false);
        Button button=rootView.findViewById(R.id.calibration_plotCurveButton);
        final EditText e1,e2,e3,e4,e5,ev1,ev2,ev3,ev4,ev5;
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



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i1 = Double.parseDouble(e1.getText().toString());
                i2 = Double.parseDouble(e2.getText().toString());
                i3 = Double.parseDouble(e3.getText().toString());
                i4 = Double.parseDouble(e4.getText().toString());
                i5 = Double.parseDouble(e5.getText().toString());
                v1 = Double.parseDouble(ev1.getText().toString());
                v2 = Double.parseDouble(ev2.getText().toString());
                v3 = Double.parseDouble(ev3.getText().toString());
                v4 = Double.parseDouble(ev4.getText().toString());
                v5 = Double.parseDouble(ev5.getText().toString());


                CalibrationGraphFragment calibrationGraphFragment = new CalibrationGraphFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.calibration_fragment_container, new CalibrationGraphFragment(i1,i2,i3,i4,i5,v1,v2,v3,v4,v5)).addToBackStack(null).commit();

            }
        });
        return rootView;
    }
}