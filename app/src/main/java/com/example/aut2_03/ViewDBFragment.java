package com.example.aut2_03;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class ViewDBFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.second_activity, container, false);

        GridView gridView = view.findViewById(R.id.grid_games);

        if(gridView != null){
            GameAdapter gameAdapter = new GameAdapter(this.getContext(),DataBaseFragment.db.getGames());
            gridView.setAdapter(gameAdapter);
        }
        return view;
    }
}
