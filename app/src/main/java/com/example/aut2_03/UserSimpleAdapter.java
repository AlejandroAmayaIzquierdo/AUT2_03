package com.example.aut2_03;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserSimpleAdapter extends RecyclerView.Adapter<UserRowView> {

    private List<User> itemList;

    public UserSimpleAdapter(List<User> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public UserRowView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);

        return new UserRowView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserRowView holder, int position) {
        User item = itemList.get(position);
        holder.nameText.setText(item.getName());
        holder.emailText.setText(item.getEmail());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
