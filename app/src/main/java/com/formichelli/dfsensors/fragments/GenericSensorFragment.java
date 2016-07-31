package com.formichelli.dfsensors.fragments;

import android.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.formichelli.dfsensors.R;

public class GenericSensorFragment extends Fragment {
    private static final String TYPE = "type";

    private int type;

    public GenericSensorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param type The type of the sensors to be displayed
     * @return A new instance of fragment GenericSensorFragment.
     */
    public static GenericSensorFragment newInstance(int type) {
        GenericSensorFragment fragment = new GenericSensorFragment();
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
        final View mainLayout = inflater.inflate(R.layout.fragment_generic_sensor, container, false);
        final TextView sensorsList = (TextView) mainLayout.findViewById(R.id.sensors_list);

        final boolean shouldPrintType = type == Sensor.TYPE_ALL;
        final SensorManager sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        final StringBuilder sensorListBuilder = new StringBuilder("\nAvailable sensors:");
        for (Sensor sensor : sensorManager.getSensorList(type)) {
            sensorListBuilder.append("\n - ");
            sensorListBuilder.append(sensor.getName());
            if (shouldPrintType) {
                sensorListBuilder.append(" (").append(sensor.getStringType()).append(")");
            }
        }

        sensorsList.setText(sensorListBuilder.toString());

        return mainLayout;
    }
}
