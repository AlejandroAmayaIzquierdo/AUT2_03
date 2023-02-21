package com.example.aut2_03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LinearLayout container = findViewById(R.id.RecycleContainer);
        RecyclerView recyclerView = container.findViewById(R.id.dataBaseItems);

        List<User> userList = new ArrayList<>();
        userList.add(new User("John Doe", "john.doe@example.com"));
        UserSimpleAdapter adapter = new UserSimpleAdapter(userList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionsMenu menuFab = findViewById(R.id.multiple_actions);
        FloatingActionButton userFab = findViewById(R.id.fab_users);


        userFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View newLayout = LayoutInflater.from(MainActivity.this).inflate(R.layout.database_insert_view, container, false);
                container.removeAllViews();
                container.addView(newLayout);
                menuFab.collapse();
            }
        });

    }
}