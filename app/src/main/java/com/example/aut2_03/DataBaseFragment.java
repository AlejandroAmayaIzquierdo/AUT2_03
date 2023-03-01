package com.example.aut2_03;


import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBaseFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;

    public static DataBaseHandler db;
    public static GameSimpleAdapter adapter;

    private ImageView imageView;
    private Bitmap selectedImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.databaseview, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.dataBaseItems);

        db = new DataBaseHandler(this.getContext());

        //db.insertData("Skyrim","beteshda",);

        adapter = new GameSimpleAdapter(db.getGames());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        imageView = view.findViewById(R.id.ImageSelected);
        Button selectGameImage = view.findViewById(R.id.selectGameImage);

        selectGameImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch the image picker activity
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });

        EditText name = view.findViewById(R.id.editTextTextGameName);
        EditText developer = view.findViewById(R.id.editTextTextDeveloperName);

        Button addButton = view.findViewById(R.id.button2);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedImage == null) {
                    Toast.makeText(DataBaseFragment.this.getContext(), "Please select an image", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Convert the selected image to a byte array
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                selectedImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                // Insert the data into the database
                if (db.insertData(name.getText().toString(), developer.getText().toString(), byteArray)) {
                    Toast.makeText(DataBaseFragment.this.getContext(), "Data inserted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DataBaseFragment.this.getContext(), "Data insertion failed", Toast.LENGTH_SHORT).show();
                }
            }
        });




        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            try {
                // Get the selected image as a bitmap
                Uri imageUri = data.getData();
                Toast.makeText(this.getContext(),imageUri + "",Toast.LENGTH_SHORT).show();
                selectedImage = MediaStore.Images.Media.getBitmap(this.getContext().getContentResolver(), imageUri);

                // Set the ImageView to display the selected image
                imageView.setImageBitmap(selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
