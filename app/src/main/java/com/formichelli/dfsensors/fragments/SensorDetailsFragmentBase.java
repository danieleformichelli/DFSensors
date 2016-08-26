package com.formichelli.dfsensors.fragments;


import android.app.Fragment;
import android.content.Context;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
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
 * Use the {@link SensorDetailsFragmentBase#newInstance} factory method to
 * create an instance of this fragment.
 */
public abstract class SensorDetailsFragmentBase extends Fragment implements SensorEventListener {
    private static final String TYPE = "TYPE";

    protected int type;
    private SensorManager sensorManager;
    private Sensor sensor;

    public SensorDetailsFragmentBase() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param type The type of the sensor
     * @return A new instance of fragment SensorDetailsFragmentBase.
     */
    public static <T extends Fragment> T newInstance(T base, int type) {
        Bundle args = new Bundle();
        args.putInt(TYPE, type);
        base.setArguments(args);
        return base;
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

        if (sensor != null) {
            addValueView(mainView);
        }

        return mainView;
    }

    private void setDetails(GridLayout mainView) {
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(type);
        if (sensor == null) {
            mainView.addView(label(getString(R.string.sensor_not_available)));
            return;
        }

        // name
        mainView.addView(label(getString(R.string.sensor_name_label)));
        mainView.addView(value(sensor.getName()));

        // resolution
        mainView.addView(label(getString(R.string.sensor_resolution_label)));
        mainView.addView(value(String.valueOf(sensor.getResolution())));

        // max value
        mainView.addView(label(getString(R.string.sensor_max_range)));
        mainView.addView(value(String.valueOf(sensor.getMaximumRange())));

        // vendor
        mainView.addView(label(getString(R.string.sensor_vendor_label)));
        mainView.addView(value(sensor.getVendor()));

        // version
        mainView.addView(label(getString(R.string.sensor_version_label)));
        mainView.addView(value(String.valueOf(sensor.getVersion())));
    }

    TextView label(CharSequence label) {
        TextView labelView = new TextView(getContext());
        labelView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        labelView.setPadding(0, 0, 0, 10);
        labelView.setText(label);
        return labelView;
    }

    TextView value(CharSequence value) {
        TextView valueView = new TextView(getContext());
        valueView.setPadding(30, 0, 0, 10);
        valueView.setText(value);
        return valueView;
    }

    @Override
    public void onResume() {
        super.onResume();

        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        sensorChanged(sensorEvent);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        accuracyChanged(sensor, i);
    }

    protected abstract void addValueView(GridLayout mainView);

    protected abstract void sensorChanged(SensorEvent sensorEvent);

    protected abstract void accuracyChanged(Sensor sensor, int i);

}
