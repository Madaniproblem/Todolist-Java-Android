package com.thamarezki.todolistproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class Adapterlist extends RecyclerView.Adapter<Adapterlist.ViewHolder> {

    ArrayList<ListModel>arrayList = new ArrayList<>();
    Context context;
    private DBHelper dbHelper;
    private Activity activity;


    public Adapterlist(ArrayList<ListModel> modelArrayList, Context context) {
        this.arrayList = modelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapterlist.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapterlist.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.info1.setText(arrayList.get(position).getPlanact());
        holder.info2.setText(arrayList.get(position).getDescription());
        holder.info3.setText("Date: "+arrayList.get(position).getDate() );
        holder.info4.setText("Time : "+arrayList.get(position).getTime());
        holder.edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent p = new Intent(context,list_update.class);
               p.putExtra("id",arrayList.get(position).getId());
               p.putExtra("planact",arrayList.get(position).getPlanact());
               p.putExtra("description",arrayList.get(position).getDescription());
               p.putExtra("date",arrayList.get(position).getDate());
               p.putExtra("time",arrayList.get(position).getTime());
               context.startActivity(p);
               if (context instanceof Activity) {
                   ((Activity)context).finish();
               }
            }
        });
        holder.del.setOnClickListener(view -> {
            deldialog(arrayList.get(position));
        });
        holder.check.setOnClickListener(view -> {
            showcomplete(arrayList.get(position),position);
        });

    }

    private void deldialog(ListModel listModel) {
        String i = String.valueOf(listModel.getId());
        new AlertDialog.Builder(context)
                .setTitle("Delete Confirmation")
                .setMessage("Are you sure want to delete data")
                .setPositiveButton("Yes",(dialogInterface, which) -> {
                    DBHelper dataHelper = new DBHelper(context);
                    dataHelper.delete_data(i);
                    Toast.makeText(context,"Deleted data success",Toast.LENGTH_SHORT).show();
                    Intent is = new Intent(context,item_activitytodo.class);
                    is.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(is);
                    if(context instanceof Activity) {
                        ((Activity)context).finish();
                    }
                })
                .setNegativeButton("No",null).show();
    }

    private void showcomplete(ListModel listModel,int position) {
        DBHelper dbHelpers = new DBHelper(context);
        new AlertDialog.Builder(context)
                .setTitle("Information")
                .setMessage("Are you sure the task is complete ?")
                .setPositiveButton("Yes",(dialogInterface, i) -> {
                    boolean updated = dbHelpers.Done(listModel.getId());
                    if (updated) {
                        arrayList.remove(position);
                        notifyItemRemoved(position);
                        Toast.makeText(context,"Already task",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel",null).show();
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView info1,info2,info3,info4;
        ImageView del,edt,check;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            info1 = itemView.findViewById(R.id.activityinfo);
            info2 = itemView.findViewById(R.id.descinfo);
            info3 = itemView.findViewById(R.id.dateinfo);
            info4 = itemView.findViewById(R.id.timeinfo);
            del = itemView.findViewById(R.id.delicon);
            edt = itemView.findViewById(R.id.edticon);
            check = itemView.findViewById(R.id.checkicon);
        }
    }
}
