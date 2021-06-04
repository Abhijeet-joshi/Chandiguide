package com.rishiraj.chandiguide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerRowAdapter extends RecyclerView.Adapter<RecyclerRowAdapter.ViewHolder> {

    Context context;
    ArrayList<RowModel> arr = new ArrayList<>();

    static int post;

    public RecyclerRowAdapter(Context context, ArrayList<RowModel> arr) {

        this.context = context;
        this.arr = arr;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull com.rishiraj.chandiguide.RecyclerRowAdapter.ViewHolder holder, int position) {

        holder.title.setText(arr.get(position).title);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                post = position;
                Intent callAPI = new Intent(context,API.class);
                callAPI.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(callAPI);


            }
        });


    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{


        LinearLayout parent;
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //id's find

            parent = itemView.findViewById(R.id.parent);
            title = itemView.findViewById(R.id.title);


        }
    }
}
