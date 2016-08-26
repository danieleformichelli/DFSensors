package com.formichelli.dfsensors.fragments;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.widget.GridLayout;
import android.widget.TextView;

import com.formichelli.dfsensors.R;

public class SensorDetailsFragmentRotationVector extends SensorDetailsFragmentBase {
    protected TextView sensorValue0View;
    protected TextView sensorValue1View;
    protected TextView sensorValue2View;
    protected TextView sensorValue3View;
    protected TextView sensorValue4View;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SensorDetailsFragmentBase.
     */
    public static SensorDetailsFragmentRotationVector newInstance(int type) {
        return SensorDetailsFragmentBase.newInstance(new SensorDetailsFragmentRotationVector(), type);
    }

    @Override
    protected void addValueView(GridLayout mainView) {
        // x*sin(θ/2)
        mainView.addView(label(getString(R.string.sensor_rotation_vector_value_0_label)));
        sensorValue0View = value("");
        mainView.addView(sensorValue0View);

        // y*sin(θ/2)
        mainView.addView(label(getString(R.string.sensor_rotation_vector_value_1_label)));
        sensorValue1View = value("");
        mainView.addView(sensorValue1View);

        // z*sin(θ/2)
        mainView.addView(label(getString(R.string.sensor_rotation_vector_value_2_label)));
        sensorValue2View = value("");
        mainView.addView(sensorValue2View);

        if (type != Sensor.TYPE_GAME_ROTATION_VECTOR) {
            // cos(θ/2)
            mainView.addView(label(getString(R.string.sensor_rotation_vector_value_3_label)));
            sensorValue3View = value("");
            mainView.addView(sensorValue3View);

            // accuracy
            mainView.addView(label(getString(R.string.sensor_rotation_vector_value_4_label, "rad")));
            sensorValue4View = value("");
            mainView.addView(sensorValue4View);
        }
    }

    @Override
    protected void sensorChanged(SensorEvent sensorEvent) {
        sensorValue0View.setText(String.valueOf(sensorEvent.values[0]));
        sensorValue1View.setText(String.valueOf(sensorEvent.values[1]));
        sensorValue2View.setText(String.valueOf(sensorEvent.values[2]));
        if (type != Sensor.TYPE_GAME_ROTATION_VECTOR) {
            sensorValue3View.setText(String.valueOf(sensorEvent.values[3]));
            sensorValue4View.setText(String.valueOf(sensorEvent.values[4]));
        }
    }

    @Override
    protected void accuracyChanged(Sensor sensor, int i) {

    }
}
