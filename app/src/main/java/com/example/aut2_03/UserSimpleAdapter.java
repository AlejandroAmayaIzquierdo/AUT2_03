package com.example.aut2_03;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

        holder.itemView.findViewById(R.id.remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), item.getName(), Toast.LENGTH_SHORT).show();
                DataBaseFragment.db.removeUser(item.getName());
                DataBaseFragment.adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
