package com.thamarezki.todolistproject;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.Calendar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;


public class MainActivity extends AppCompatActivity {

    ImageView iv;
    EditText ia,id,ic,ie;

    Button sbm;
    @SuppressLint({"WrongViewCast", "MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ia = findViewById(R.id.activityadd);
        id = findViewById(R.id.descadd);
        ic = findViewById(R.id.dateadd);
        ie = findViewById(R.id.timeadd);
        iv = findViewById(R.id.backtomain);
        sbm = findViewById(R.id.senddataadd);
        ic.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                    (view1, year1, month1, day1) -> ic.setText(day1 + "/" + (month1 + 1) + "/" + year1),
                    year, month, day);
            datePickerDialog.show();
        });
        ie.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                    (view1, hour1, minute1) -> ie.setText(hour1 + ":" + minute1),
                    hour, minute, true);
            timePickerDialog.show();
        });

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n = new Intent(view.getContext(), item_activitytodo.class);
                startActivity(n);
                finish();
            }
        });
        DBHelper dbHelper = new DBHelper(MainActivity.this);
        sbm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ia.length() > 0 && id.length() > 0 && ic.length() > 0 && ie.length() > 0) {
                    dbHelper.insert_data(ia.getText().toString(),id.getText().toString(),ic.getText().toString(),ie.getText().toString());
                    ia.setText("");
                    id.setText("");
                    ic.setText("");
                    ie.setText("");
                    Toast.makeText(MainActivity.this,"Plan activity already saved",Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this,"Please input some data",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent o = new Intent(MainActivity.this, item_activitytodo.class);
        startActivity(o);
        finish();
    }
}