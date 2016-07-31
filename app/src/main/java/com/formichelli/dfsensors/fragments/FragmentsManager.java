package com.formichelli.dfsensors.fragments;

import android.app.Fragment;
import android.hardware.Sensor;

import com.formichelli.dfsensors.R;

import java.util.HashMap;
import java.util.Map;

public class FragmentsManager {
    private static final int INVALID_SENSOR_TYPE = Integer.MIN_VALUE;
    Map<Integer, Fragment> itemIdToFragment = new HashMap<>();

    public Fragment getFragment(int itemId) {
        Fragment targetFragment = itemIdToFragment.get(itemId);
        if (targetFragment == null) {
            targetFragment = createFragment(itemId);
            if (targetFragment != null) {
                itemIdToFragment.put(itemId, targetFragment);
            }
        }

        return targetFragment;
    }

    private Fragment createFragment(int itemId) {
        final int sensorType = getSensorTypeFromItemId(itemId);
        return sensorType == INVALID_SENSOR_TYPE ? null : GenericSensorFragment.newInstance(sensorType);
    }

    private int getSensorTypeFromItemId(int itemId) {
        switch (itemId) {
            case R.id.nav_main_page:
                return Sensor.TYPE_ALL;

            case R.id.nav_accelerometer:
                return Sensor.TYPE_ACCELEROMETER;

            case R.id.nav_ambient_temperature:
                return Sensor.TYPE_AMBIENT_TEMPERATURE;

            case R.id.nav_gravity:
                return Sensor.TYPE_GRAVITY;

            case R.id.nav_gyroscope:
                return Sensor.TYPE_GYROSCOPE;

            case R.id.nav_light:
                return Sensor.TYPE_LIGHT;

            case R.id.nav_linear_acceleration:
                return Sensor.TYPE_LINEAR_ACCELERATION;

            case R.id.nav_magnetic_field:
                return Sensor.TYPE_MAGNETIC_FIELD;

            case R.id.nav_orientation:
                return Sensor.TYPE_ORIENTATION;

            case R.id.nav_pressure:
                return Sensor.TYPE_PRESSURE;

            case R.id.nav_proximity:
                return Sensor.TYPE_PROXIMITY;

            case R.id.nav_relative_humidity:
                return Sensor.TYPE_RELATIVE_HUMIDITY;

            case R.id.nav_rotation_vector:
                return Sensor.TYPE_ROTATION_VECTOR;

            case R.id.nav_temperature:
                return Sensor.TYPE_TEMPERATURE;

            default:
                return INVALID_SENSOR_TYPE;
        }
    }
}
