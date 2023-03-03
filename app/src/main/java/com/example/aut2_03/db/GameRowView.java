package com.example.aut2_03.db;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aut2_03.R;

public class GameRowView extends RecyclerView.ViewHolder {

    public TextView nameText;
    public TextView emailText;

    public GameRowView(@NonNull View itemView) {
        super(itemView);

        nameText = itemView.findViewById(R.id.name);
        emailText = itemView.findViewById(R.id.email);
    }
}
