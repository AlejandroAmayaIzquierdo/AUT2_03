package com.example.aut2_03.db;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.aut2_03.R;

public class ViewDBFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.second_activity, container, false);

        GridView gridView = view.findViewById(R.id.grid_games);

        if(gridView != null){
            GameAdapter gameAdapter = new GameAdapter(this.getContext(), DataBaseFragment.db.getGames());
            gridView.setAdapter(gameAdapter);
        }
        return view;
    }
}
