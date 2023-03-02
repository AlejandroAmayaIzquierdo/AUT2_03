package com.example.aut2_03.sensores.battery;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.aut2_03.R;

public class BatteryFragment extends Fragment {

    private TextView textView;

    private BatteryReceiver batteryReceiver;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.battery_view,container,false);

        textView = view.findViewById(R.id.battery_TextView);

        batteryReceiver = new BatteryReceiver(textView);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        requireActivity().registerReceiver(batteryReceiver, filter);
    }

    @Override
    public void onStop() {
        super.onStop();
        requireActivity().unregisterReceiver(batteryReceiver);
    }


}
