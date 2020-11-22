package com.example.fluorometerapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalibrationGraphFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalibrationGraphFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "0";
    private static final String ARG_PARAM2 = "0";
    private static final String ARG_PARAM3 = "0";
    private static final String ARG_PARAM4 = "0";
    private static final String ARG_PARAM5 = "0";
    private static final String ARG_PARAM6 = "0";
    private static final String ARG_PARAM7 = "0";
    private static final String ARG_PARAM8 = "0";
    private static final String ARG_PARAM9 = "0";
    private static final String ARG_PARAM10 = "0";


    // TODO: Rename and change types of parameters
    private Double mParam1;
    private Double mParam2;
    private Double mParam3;
    private Double mParam4;
    private Double mParam5;
    private Double mParam6;
    private Double mParam7;
    private Double mParam8;
    private Double mParam9;
    private Double mParam10;


    public CalibrationGraphFragment() {
        // Required empty public constructor
    }

    public CalibrationGraphFragment(Double i1, Double i2, Double i3, Double i4, Double i5, Double v1, Double v2, Double v3, Double v4, Double v5) {
        mParam1 = i1;
        mParam2 = i2;
        mParam3 = i3;
        mParam4 = i4;
        mParam5 = i5;
        mParam6 = v1;
        mParam7 = v2;
        mParam8 = v3;
        mParam9 = v4;
        mParam10 = v5;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment CalibrationGraphFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalibrationGraphFragment newInstance(int i1,int i2, int i3, int i4, int i5, int v1, int v2, int v3, int v4, int v5) {
        CalibrationGraphFragment fragment = new CalibrationGraphFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, i1);
        args.putInt(ARG_PARAM2, i2);
        args.putInt(ARG_PARAM3, i3);
        args.putInt(ARG_PARAM4, i4);
        args.putInt(ARG_PARAM5, i5);
        args.putInt(ARG_PARAM6, v1);
        args.putInt(ARG_PARAM7, v2);
        args.putInt(ARG_PARAM8, v3);
        args.putInt(ARG_PARAM9, v4);
        args.putInt(ARG_PARAM10, v5);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = Double.parseDouble(getArguments().getString(ARG_PARAM1));
            mParam2 = Double.parseDouble(getArguments().getString(ARG_PARAM2));
            mParam3 = Double.parseDouble(getArguments().getString(ARG_PARAM3));
            mParam4 = Double.parseDouble(getArguments().getString(ARG_PARAM4));
            mParam5 = Double.parseDouble(getArguments().getString(ARG_PARAM5));
            mParam6 = Double.parseDouble(getArguments().getString(ARG_PARAM6));
            mParam7 = Double.parseDouble(getArguments().getString(ARG_PARAM7));
            mParam8 = Double.parseDouble(getArguments().getString(ARG_PARAM8));
            mParam9 = Double.parseDouble(getArguments().getString(ARG_PARAM9));
            mParam10 = Double.parseDouble(getArguments().getString(ARG_PARAM10));



        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        View rootView = inflater.inflate(R.layout.fragment_calibration_graph, container, false);
        GraphView graph = (GraphView) rootView.findViewById(R.id.graph);
        List<Pair<Double,Double>> vals = new ArrayList<>();
        vals.add(new Pair(mParam1, mParam6));
        vals.add(new Pair(mParam2, mParam7));
        vals.add(new Pair(mParam3, mParam8));
        vals.add(new Pair(mParam4, mParam9));
        vals.add(new Pair(mParam5, mParam10));
        Collections.sort(vals, new Comparator<Pair<Double, Double>>() {
            @Override
            public int compare(final Pair<Double, Double> o1, final Pair<Double, Double> o2) {
                if(o1.first<o2.first) return -1;
                else if(o1.first==o2.first && (o1.second<o2.second)) return -1;
                else if(o1.first == o2.first && o1.second == o2.second) return 0;
                else return 1;
            }
        });


        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
              new DataPoint(vals.get(0).first, vals.get(0).second),
                new DataPoint(vals.get(1).first, vals.get(1).second),
                new DataPoint(vals.get(2).first, vals.get(2).second),
                new DataPoint(vals.get(3).first, vals.get(3).second),
                new DataPoint(vals.get(4).first, vals.get(4).second)
        });
        Log.println(Log.ASSERT,"Values",vals.get(0).first+" "+vals.get(1).first+" "+vals.get(2).first+" "+vals.get(3).first+" "+vals.get(4).first);
        series.setTitle("Intensity concentration curve");
        series.setColor(Color.GREEN);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
        series.setThickness(8);
        graph.addSeries(series);

//        series.setShape(PointsGraphSeries.Shape.POINT);
        //fragmentManager.popBackStack();
        return rootView;
    }
}