package com.example.aut2_03.sensores.luz;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.aut2_03.R;

public class LuzAmbienteFragment extends Fragment {

    private SensorManager sensorManager;
    private Sensor lightSensor;

    private TextView textView;

    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float lightLevel = event.values[0];
            String situation;

            if (lightLevel < 100) {
                situation = "Oscuro";
            } else if (lightLevel < 2000) {
                situation = "Normal";
            } else {
                situation = "Brillante";
            }
            textView.setText("Luz: " + situation);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.light_view,container,false);

        textView = view.findViewById(R.id.light_TextView);

        sensorManager = (SensorManager) this.getActivity().getSystemService(Context.SENSOR_SERVICE);
        ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.BODY_SENSORS}, 1);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorListener, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorListener);
    }
}
