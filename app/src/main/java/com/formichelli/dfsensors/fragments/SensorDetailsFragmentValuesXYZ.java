package com.formichelli.dfsensors.fragments;

import android.app.Fragment;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.text.Html;
import android.widget.GridLayout;
import android.widget.TextView;

import com.formichelli.dfsensors.R;

public class SensorDetailsFragmentValuesXYZ extends SensorDetailsFragmentBase {
    protected TextView sensorValueXView;
    protected TextView sensorValueYView;
    protected TextView sensorValueZView;

    protected String unitOfMeasure = "";

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SensorDetailsFragmentBase.
     */
    public static SensorDetailsFragmentValuesXYZ newInstance(int type) {
        return SensorDetailsFragmentBase.newInstance(new SensorDetailsFragmentValuesXYZ(), type);
    }

    public static <T extends SensorDetailsFragmentValuesXYZ> T newInstance(T base, int type) {
        return SensorDetailsFragmentBase.newInstance(base, type);
    }

    public SensorDetailsFragmentValuesXYZ setUnitOfMeasure(final String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
        return this;
    }

    @Override
    protected void addValueView(GridLayout mainView) {
        // value
        mainView.addView(label(Html.fromHtml(getString(R.string.sensor_value_x_label, unitOfMeasure))));
        sensorValueXView = value("");
        mainView.addView(sensorValueXView);

        // value
        mainView.addView(label(Html.fromHtml(getString(R.string.sensor_value_y_label, unitOfMeasure))));
        sensorValueYView = value("");
        mainView.addView(sensorValueYView);

        // value
        mainView.addView(label(Html.fromHtml(getString(R.string.sensor_value_z_label, unitOfMeasure))));
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
