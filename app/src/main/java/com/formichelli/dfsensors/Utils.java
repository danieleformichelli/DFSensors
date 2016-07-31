package com.formichelli.dfsensors;

import android.content.Context;
import android.hardware.Sensor;

public class Utils {
    public static final int INVALID_SENSOR_TYPE = Integer.MIN_VALUE;
    private static Context context;

    public static void setContext(Context context) {
        Utils.context = context;
    }

    public static String getSensorDescription(int sensorType) {
        switch (sensorType) {
            case Sensor.TYPE_ALL:
                return "";

            case Sensor.TYPE_ACCELEROMETER:
                return context.getString(R.string.accelerometer);

            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                return context.getString(R.string.ambient_temperature);

            case Sensor.TYPE_GRAVITY:
                return context.getString(R.string.gravity);

            case Sensor.TYPE_GYROSCOPE:
                return context.getString(R.string.gyroscope);

            case Sensor.TYPE_LIGHT:
                return context.getString(R.string.light);

            case Sensor.TYPE_LINEAR_ACCELERATION:
                return context.getString(R.string.linear_acceleration);

            case Sensor.TYPE_MAGNETIC_FIELD:
                return context.getString(R.string.magnetic_field);

            case Sensor.TYPE_ORIENTATION:
                return context.getString(R.string.orientation);

            case Sensor.TYPE_PRESSURE:
                return context.getString(R.string.pressure);

            case Sensor.TYPE_PROXIMITY:
                return context.getString(R.string.proximity);

            case Sensor.TYPE_RELATIVE_HUMIDITY:
                return context.getString(R.string.relative_humidity);

            case Sensor.TYPE_ROTATION_VECTOR:
                return context.getString(R.string.rotation_vector);

            case Sensor.TYPE_TEMPERATURE:
                return context.getString(R.string.temperature);

            default:
                return String.valueOf(sensorType);
        }
    }

    public static int getSensorTypeFromItemId(int itemId) {
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
                return Utils.INVALID_SENSOR_TYPE;
        }
    }
}
