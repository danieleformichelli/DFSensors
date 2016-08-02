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
                return SensorDetailsFragmentValuesXYZ.newInstance(sensorType).setUnitOfMeasure(UnitsOfMeasure.ACCELERATION);

            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                return SensorDetailsFragmentOneValue.newInstance(sensorType).setUnitOfMeasure(UnitsOfMeasure.TEMPERATURE);

            case Sensor.TYPE_GRAVITY:
                return SensorDetailsFragmentValuesXYZ.newInstance(sensorType).setUnitOfMeasure(UnitsOfMeasure.ACCELERATION);

            case Sensor.TYPE_GYROSCOPE:
                return SensorDetailsFragmentValuesXYZ.newInstance(sensorType).setUnitOfMeasure(UnitsOfMeasure.ACCELERATION);

            case Sensor.TYPE_LIGHT:
                return SensorDetailsFragmentOneValue.newInstance(sensorType).setUnitOfMeasure(UnitsOfMeasure.LIGHT);

            case Sensor.TYPE_LINEAR_ACCELERATION:
                return SensorDetailsFragmentValuesXYZ.newInstance(sensorType).setUnitOfMeasure(UnitsOfMeasure.ACCELERATION);

            case Sensor.TYPE_MAGNETIC_FIELD:
                return SensorDetailsFragmentValuesXYZ.newInstance(sensorType).setUnitOfMeasure(UnitsOfMeasure.MAGNETIC_FIELD);

            case Sensor.TYPE_ORIENTATION: // TODO use non deprecated method
                return SensorDetailsFragmentOrientation.newInstance();

            case Sensor.TYPE_PRESSURE:
                return SensorDetailsFragmentOneValue.newInstance(sensorType).setUnitOfMeasure(UnitsOfMeasure.PRESSURE);

            case Sensor.TYPE_PROXIMITY:
                return SensorDetailsFragmentOneValue.newInstance(sensorType).setUnitOfMeasure(UnitsOfMeasure.PROXIMITY);

            case Sensor.TYPE_RELATIVE_HUMIDITY:
                return SensorDetailsFragmentOneValue.newInstance(sensorType).setUnitOfMeasure(UnitsOfMeasure.HUMIDITY);

            case Sensor.TYPE_ROTATION_VECTOR:
                return SensorDetailsFragmentRotatioinVector.newInstance(sensorType);

            case Sensor.TYPE_GAME_ROTATION_VECTOR:
                return SensorDetailsFragmentRotatioinVector.newInstance(sensorType);

            default:
                return SensorDetailsFragmentOneValue.newInstance(sensorType);
        }
    }
}
