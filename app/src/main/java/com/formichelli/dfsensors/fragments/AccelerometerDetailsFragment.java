package com.formichelli.dfsensors.fragments;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.text.Html;
import android.widget.GridLayout;
import android.widget.TextView;

import com.formichelli.dfsensors.R;

public class AccelerometerDetailsFragment extends SensorDetailsFragmentBase {
    private TextView sensorValueXView;
    private TextView sensorValueYView;
    private TextView sensorValueZView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param type The type of the sensor
     * @return A new instance of fragment SensorDetailsFragmentBase.
     */
    public static AccelerometerDetailsFragment newInstance() {
        return SensorDetailsFragmentBase.newInstance(new AccelerometerDetailsFragment(), Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void addValueView(GridLayout mainView) {
        // value
        mainView.addView(label(Html.fromHtml(getString(R.string.sensor_value_x_label))));
        sensorValueXView = value("");
        mainView.addView(sensorValueXView);

        // value
        mainView.addView(label(Html.fromHtml(getString(R.string.sensor_value_y_label))));
        sensorValueYView = value("");
        mainView.addView(sensorValueYView);

        // value
        mainView.addView(label(Html.fromHtml(getString(R.string.sensor_value_z_label))));
        sensorValueZView = value("");
        mainView.addView(sensorValueZView);
    }

    @Override
    protected void sensorChanged(SensorEvent sensorEvent) {
        sensorValueXView.setText(String.valueOf(sensorEvent.values[0]));
        sensorValueYView.setText(String.valueOf(sensorEvent.values[1]));
        sensorValueZView.setText(String.valueOf(sensorEvent.values[2]));
    }

    @Override
    protected void accuracyChanged(Sensor sensor, int i) {

    }
}
