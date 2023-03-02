package com.example.aut2_03.sensores.battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.TextView;
import android.widget.Toast;

public class BatteryReceiver extends BroadcastReceiver {

    TextView textView;

    public BatteryReceiver(TextView textView){
        this.textView = textView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                    status == BatteryManager.BATTERY_STATUS_FULL;

            int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
            boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            float batteryPct = level * 100 / (float)scale;

            String chargingMethod = "";
            if (usbCharge) {
                chargingMethod = "USB";
            } else if (acCharge) {
                chargingMethod = "AC";
            }

            String statusText = "DESCONOCIDO";
            switch (status) {
                case BatteryManager.BATTERY_STATUS_CHARGING:
                    statusText = "CARGANDO";
                    break;
                case BatteryManager.BATTERY_STATUS_DISCHARGING:
                    statusText = "DESCARGANDO";
                    break;
                case BatteryManager.BATTERY_STATUS_FULL:
                    statusText = "COMPLETA";
                    break;
                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                    statusText = "NO CARGANDO";
                    break;
            }

            textView.setText("Nivel de batería: " + batteryPct + "%\n isCharging: " + isCharging + "\n" + "estado: " + statusText);
        }
    }
}
