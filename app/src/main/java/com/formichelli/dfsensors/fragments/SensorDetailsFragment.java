package com.formichelli.dfsensors.fragments;


import android.app.Fragment;
import android.content.Context;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import com.formichelli.dfsensors.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SensorDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SensorDetailsFragment extends Fragment {
    private static final String TYPE = "type";

    private int type;

    public SensorDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param type The type of the sensor
     * @return A new instance of fragment SensorDetailsFragment.
     */
    public static SensorDetailsFragment newInstance(int type) {
        SensorDetailsFragment fragment = new SensorDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt(TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final GridLayout mainView = (GridLayout) inflater.inflate(R.layout.fragment_sensor_details, container, false);

        setDetails(mainView);

        return mainView;
    }

    private void setDetails(GridLayout mainView) {
        SensorManager sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        Sensor defaultSensor = sensorManager.getDefaultSensor(type);
        if (defaultSensor == null) {
            mainView.addView(label(getString(R.string.sensor_not_available)));
            return;
        }

        // name
        mainView.addView(label(getString(R.string.sensor_name_label)));
        mainView.addView(value(defaultSensor.getName()));

        // resolution
        mainView.addView(label(getString(R.string.sensor_resolution_label)));
        mainView.addView(value(String.valueOf(defaultSensor.getResolution())));

        // max value
        mainView.addView(label(getString(R.string.sensor_max_range)));
        mainView.addView(value(String.valueOf(defaultSensor.getMaximumRange())));

        // vendor
        mainView.addView(label(getString(R.string.sensor_vendor_label)));
        mainView.addView(value(defaultSensor.getVendor()));

        // version
        mainView.addView(label(getString(R.string.sensor_version_label)));
        mainView.addView(value(String.valueOf(defaultSensor.getVersion())));
    }

    private View label(String label) {
        TextView labelView = new TextView(getContext());
        labelView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        labelView.setPadding(0, 0, 0, 10);
        labelView.setText(label);
        return labelView;
    }

    private View value(String value) {
        TextView valueView = new TextView(getContext());
        valueView.setPadding(30, 0, 0, 10);
        valueView.setText(value);
        return valueView;
    }
}
