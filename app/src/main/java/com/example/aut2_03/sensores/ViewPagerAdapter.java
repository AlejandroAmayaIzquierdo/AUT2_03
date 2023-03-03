package com.example.aut2_03.sensores;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.aut2_03.sensores.battery.BatteryFragment;
import com.example.aut2_03.sensores.location.LocationFragment;
import com.example.aut2_03.sensores.luz.LuzAmbienteFragment;
import com.example.aut2_03.sensores.resolution.ResolutionFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new ResolutionFragment();
            case 1:
                return new BatteryFragment();
            case 2:
                return new LuzAmbienteFragment();
            case 3:
                return new LocationFragment();
            default:
                return new ResolutionFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
