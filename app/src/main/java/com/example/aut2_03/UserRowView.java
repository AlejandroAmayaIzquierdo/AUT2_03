package com.example.aut2_03;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserRowView extends RecyclerView.ViewHolder {

    public TextView nameText;
    public TextView emailText;

    public UserRowView(@NonNull View itemView) {
        super(itemView);

        nameText = itemView.findViewById(R.id.name);
        emailText = itemView.findViewById(R.id.email);
    }
}
