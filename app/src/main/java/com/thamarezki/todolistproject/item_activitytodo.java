package com.thamarezki.todolistproject;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class item_activitytodo extends AppCompatActivity {

    FloatingActionButton fab,fal;
    RecyclerView rv;

    ArrayList<ListModel>arrayList = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_item_activitytodo);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        fab = findViewById(R.id.floatadd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(item_activitytodo.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        fal = findViewById(R.id.listadd);
        fal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p = new Intent(item_activitytodo.this,Donelist.class);
                startActivity(p);
                finish();
            }
        });
        rv = findViewById(R.id.rreview);
        rv.setLayoutManager(new LinearLayoutManager(this));
        DBHelper dbHelper = new DBHelper(this);
        Cursor cursor = dbHelper.cushow();
        while (cursor.moveToNext()) {
            arrayList.add(new ListModel(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5) == 1,cursor.getInt(0)));
        }
        Adapterlist adapterlist = new Adapterlist(arrayList,this);
        rv.setAdapter(adapterlist);


    }

}