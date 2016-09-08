package com.formichelli.dfsensors.fragments;

import android.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.formichelli.dfsensors.R;

import java.util.List;

public class SensorListFragment extends Fragment {
    private static final String TYPE = "type";

    private int type;

    public SensorListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param type The type of the sensors to be displayed
     * @return A new instance of fragment SensorListFragment.
     */
    public static SensorListFragment newInstance(int type) {
        SensorListFragment fragment = new SensorListFragment();
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
        View mainView = inflater.inflate(R.layout.fragment_generic_sensor, container, false);

        final TextView sensorsList = (TextView) mainView.findViewById(R.id.sensors_list);

        new LoadSensorsListTask(sensorsList).execute();

        return mainView;
    }

    private class LoadSensorsListTask extends AsyncTask<Void, Void, Void> {
        private final TextView _targetView;
        final StringBuilder _sensorListBuilder = new StringBuilder();

        public LoadSensorsListTask(TextView targetView) {
            _targetView = targetView;
        }

        @Override
        protected Void doInBackground(Void... targetView) {
            final boolean shouldPrintType = type == Sensor.TYPE_ALL;
            final SensorManager sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
            final List<Sensor> sensorList = sensorManager.getSensorList(type);
            if (sensorList.isEmpty()) {
                _sensorListBuilder.append(getString(R.string.sensor_not_available));
            } else {
                _sensorListBuilder.append(getString(R.string.available_sensors)).append(":");
            }
            for (Sensor sensor : sensorList) {
                _sensorListBuilder.append("\n - ");
                _sensorListBuilder.append(sensor.getName());
                if (shouldPrintType) {
                    _sensorListBuilder.append(" (").append(sensor.getStringType()).append(")");
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            _targetView.setText(_sensorListBuilder.toString());
        }
    }
}
