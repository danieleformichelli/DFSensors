package com.formichelli.dfsensors.fragments;

import android.app.Fragment;
import android.hardware.Sensor;

import com.formichelli.dfsensors.Utils;

import java.util.HashMap;
import java.util.Map;

public class FragmentsManager {
    Map<Integer, Fragment> sensorTypeToFragment = new HashMap<>();

    public Fragment getFragment(int sensorType) {
        Fragment targetFragment = sensorTypeToFragment.get(sensorType);
        if (targetFragment == null) {
            targetFragment = createFragment(sensorType);
            if (targetFragment != null) {
                sensorTypeToFragment.put(sensorType, targetFragment);
            }
        }

        return targetFragment;
    }

    private Fragment createFragment(int sensorType) {
        switch (sensorType) {
            case Utils.INVALID_SENSOR_TYPE:
                return null;

            case Sensor.TYPE_ALL:
                return SensorListFragment.newInstance(sensorType);

            case Sensor.TYPE_ACCELEROMETER:
                return AccelerometerDetailsFragment.newInstance();

            default:
                return SensorDetailsFragmentOneValue.newInstance(sensorType);
        }
    }
}
