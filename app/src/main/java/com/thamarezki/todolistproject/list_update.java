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

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class list_update extends AppCompatActivity {

    EditText cpl,desc,dates,times;
    Button udtbtn;
    ImageView back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_update);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

    cpl = findViewById(R.id.edtact);
    desc = findViewById(R.id.edtdesc);
    dates = findViewById(R.id.edtdate);
    times = findViewById(R.id.edttime);
    udtbtn = findViewById(R.id.senddataaedt);

    Intent o = getIntent();
    int id = o.getIntExtra("id",-1);
    String pct = o.getStringExtra("planact");
    String dsc = o.getStringExtra("description");
    String dt = o.getStringExtra("date");
    String tm = o.getStringExtra("time");

    cpl.setText(pct);
    desc.setText(dsc);
    dates.setText(dt);
    times.setText(tm);
    DBHelper dbHelper = new DBHelper(this);
        dates.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(list_update.this,
                    (view1, year1, month1, day1) -> dates.setText(day1 + "/" + (month1 + 1) + "/" + year1),
                    year, month, day);
            datePickerDialog.show();
        });

        times.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(list_update.this,
                    (view1, hour1, minute1) -> times.setText(hour1 + ":" + minute1),
                    hour, minute, true);
            timePickerDialog.show();
        });
    udtbtn.setOnClickListener(view -> {
        String up = cpl.getText().toString();
        String sd = desc.getText().toString();
        String lastdate = dates.getText().toString();
        String lasttime = times.getText().toString();
        int isu = dbHelper.update_user(id,up,sd,lastdate,lasttime);
         if (isu > 0) {
            Toast.makeText(list_update.this,"Updated succesfully",Toast.LENGTH_SHORT).show();
             Intent os = new Intent(list_update.this, list_update.class);
             os.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
             startActivity(os);
             finish();
         }
         else {
             Toast.makeText(list_update.this,"Failed",Toast.LENGTH_SHORT).show();
         }
    });

    back = findViewById(R.id.backtomain2);
    back.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent p = new Intent(list_update.this,item_activitytodo.class);
            startActivity(p);
            finish();
        }
    });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent x = new Intent(list_update.this,item_activitytodo.class);
        startActivity(x);
        finish();
    }
}