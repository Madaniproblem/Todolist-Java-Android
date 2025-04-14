package com.thamarezki.todolistproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Donelist extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapterlist adapterlist;
    ArrayList<ListModel>donetask = new ArrayList<>();
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_donelist);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        recyclerView = findViewById(R.id.rreviews);
        dbHelper = new DBHelper(this);
        donetask = new ArrayList<>();
        Cursor cursor = dbHelper.getDone();
        while (cursor.moveToNext()) {
            donetask.add(new ListModel(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5) == 1,cursor.getInt(0)));
        }
        cursor.close();
        adapterlist = new Adapterlist(donetask,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterlist);


    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent o = new Intent(Donelist.this, item_activitytodo.class);
        startActivity(o);
        finish();
    }
}