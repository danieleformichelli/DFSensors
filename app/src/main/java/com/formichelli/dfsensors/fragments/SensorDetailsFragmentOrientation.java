package com.formichelli.dfsensors.fragments;

import android.hardware.Sensor;
import android.text.Html;
import android.widget.GridLayout;

import com.formichelli.dfsensors.R;

public class SensorDetailsFragmentOrientation extends SensorDetailsFragmentValuesXYZ {
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SensorDetailsFragmentBase.
     */
    public static SensorDetailsFragmentOrientation newInstance() {
        SensorDetailsFragmentOrientation fragment = SensorDetailsFragmentValuesXYZ.newInstance(new SensorDetailsFragmentOrientation(), Sensor.TYPE_ORIENTATION);
        fragment.setUnitOfMeasure("°");
        return fragment;
    }

    @Override
    protected void addValueView(GridLayout mainView) {
        // azimuth
        mainView.addView(label(Html.fromHtml(getString(R.string.sensor_orientation_azimuth_label, unitOfMeasure))));
        sensorValueXView = value("");
        mainView.addView(sensorValueXView);

        // pitch
        mainView.addView(label(Html.fromHtml(getString(R.string.sensor_orientation_pitch_label, unitOfMeasure))));
        sensorValueYView = value("");
        mainView.addView(sensorValueYView);

        // roll
        mainView.addView(label(Html.fromHtml(getString(R.string.sensor_orientation_roll_label, unitOfMeasure))));
        sensorValueZView = value("");
        mainView.addView(sensorValueZView);
    }
}
