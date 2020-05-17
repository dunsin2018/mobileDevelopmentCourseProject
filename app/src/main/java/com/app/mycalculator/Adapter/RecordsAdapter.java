package com.app.mycalculator.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.app.mycalculator.Model.FireBaseRecordModel;
import com.app.mycalculator.R;
import com.app.mycalculator.Utils.DateFormat;

import java.util.ArrayList;
import java.util.Date;


public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.ViewHolder> {

    private ArrayList<FireBaseRecordModel> mData;
    private LayoutInflater mInflater;
    private  Context mContext;

    // data is passed into the constructor
    public RecordsAdapter(Context context, ArrayList<FireBaseRecordModel> data) {
        this.mInflater = LayoutInflater.from(context);
        mContext=context;
        this.mData = data;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recordlayout, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position)
    {

        final FireBaseRecordModel obj = mData.get(position);
        holder.equation.setText(String.format("Equation : %s", obj.getEquation()));
        holder.timestamp.setText(String.format("TimeStamp  : %s", DateFormat.getDate(obj.getTimeStamp().toDate())));

    }



    @Override
    public int getItemCount() {
        return mData.size();
    }






    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView equation;
        TextView timestamp;

        ViewHolder(View itemView) {
            super(itemView);
            equation=itemView.findViewById(R.id.equation);
            timestamp = itemView.findViewById(R.id.TimeStamp);
        }

    }

}