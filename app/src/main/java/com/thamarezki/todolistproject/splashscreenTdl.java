package com.thamarezki.todolistproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class splashscreenTdl extends AppCompatActivity {

    private static int splash_time_out = 1300;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen_tdl);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               Intent i = new Intent(splashscreenTdl.this, item_activitytodo.class);
               startActivity(i);
               finish();
           }
       },splash_time_out);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}