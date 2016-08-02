package com.formichelli.dfsensors.fragments;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.widget.GridLayout;
import android.widget.TextView;

import com.formichelli.dfsensors.R;

public class SensorDetailsFragmentOneValue extends SensorDetailsFragmentBase {
    private TextView sensorValueView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param type The type of the sensor
     * @return A new instance of fragment SensorDetailsFragmentBase.
     */
    public static SensorDetailsFragmentOneValue newInstance(int type) {
        return SensorDetailsFragmentBase.newInstance(new SensorDetailsFragmentOneValue(), type);
    }

    protected void addValueView(GridLayout mainView) {
        // value
        mainView.addView(label(getString(R.string.sensor_value_label)));
        sensorValueView = value("");
        mainView.addView(sensorValueView);
    }

    @Override
    protected void sensorChanged(SensorEvent sensorEvent) {

        sensorValueView.setText(readValue(sensorEvent.values));
    }

    protected String readValue(float[] values) {
        return String.valueOf(values[0]);
    }

    @Override
    protected void accuracyChanged(Sensor sensor, int i) {
    }
}
