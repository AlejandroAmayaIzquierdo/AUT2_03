package com.example.aut2_03;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserSimpleAdapter extends RecyclerView.Adapter<UserRowView> {

    private List<Games> itemList;

    public UserSimpleAdapter(List<Games> itemList) {
        super();
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
        Games item = itemList.get(position);
        holder.nameText.setText(item.getName());
        holder.emailText.setText(item.getDeveloper());

        holder.itemView.findViewById(R.id.remove).setOnClickListener(new myOnclickListener(position) {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), item.getName(), Toast.LENGTH_SHORT).show();
                DataBaseFragment.db.removeUser(item.getName());
                removeAt(this.pos);
            }
        });
    }
    public void removeAt(int position) {
        itemList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, itemList.size());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
