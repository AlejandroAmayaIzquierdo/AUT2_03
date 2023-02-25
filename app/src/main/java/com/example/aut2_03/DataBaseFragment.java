package com.example.aut2_03;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

public class DataBaseFragment extends Fragment {

    public static DataBaseHandler db;
    public static UserSimpleAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.databaseview, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.dataBaseItems);

        db = new DataBaseHandler(this.getContext());
        db.insertData("alejandro@gmail.com","ale");
        db.insertData("fran@gmail.com","fran");
        db.insertData("juan@gmail.com","juan");
        db.insertData("test@gmail.com","test");

        adapter = new UserSimpleAdapter(db.getUsers());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        FloatingActionsMenu menuFab = view.findViewById(R.id.multiple_actions);
        FloatingActionButton userFab = view.findViewById(R.id.fab_users);

        userFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "hola", Toast.LENGTH_SHORT).show();
                LinearLayout con = container.findViewById(R.id.nav_1_container);
                View newLayout = LayoutInflater.from(DataBaseFragment.this.getContext()).inflate(R.layout.database_insert_view, con, false);
                con.removeViewAt(0);
                con.addView(newLayout);
                menuFab.collapse();
            }
        });

        return view;
    }
}
