package com.formichelli.dfsensors.fragments;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.widget.GridLayout;
import android.widget.TextView;

import com.formichelli.dfsensors.R;

public class SensorDetailsFragmentOneValue extends SensorDetailsFragmentBase {
    private TextView sensorValueView;
    private String unitOfMeasure = "";

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

    public SensorDetailsFragmentOneValue setUnitOfMeasure(final String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
        return this;
    }

    protected void addValueView(GridLayout mainView) {
        // value
        mainView.addView(label(getString(R.string.sensor_value_label, unitOfMeasure)));
        sensorValueView = value("");
        mainView.addView(sensorValueView);
    }

    @Override
    protected void sensorChanged(SensorEvent sensorEvent) {

        sensorValueView.setText(String.valueOf(sensorEvent.values[0]));
    }

    @Override
    protected void accuracyChanged(Sensor sensor, int i) {
    }
}
