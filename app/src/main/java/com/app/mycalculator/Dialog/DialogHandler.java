package com.app.mycalculator.Dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.app.mycalculator.Activities.KmtoMiles;
import com.app.mycalculator.Activities.MilesToKm;
import com.app.mycalculator.Activities.Records;
import com.app.mycalculator.Model.ResultModel;
import com.app.mycalculator.R;

import org.w3c.dom.Text;

public class DialogHandler
{








    public static  void showMenuDialog(Context context)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View child = getactivity(context).getLayoutInflater().inflate(R.layout.popup, null);
        builder.setView(child);

        builder.setCancelable(true);
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
        initDialgViews(context,child,alertDialog);
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

    }
    public static  void showNoInternetDialog(Context context)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View child = getactivity(context).getLayoutInflater().inflate(R.layout.nointernet, null);
        builder.setView(child);
        builder.setCancelable(true);
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

    }

    private static void initDialgViews(final Context mcontext, View child, final AlertDialog alertDialog)
    {


        final TextView txtMilesToKm=child.findViewById(R.id.txtMilesToKm);
        final TextView txtKmToMiles=child.findViewById(R.id.txtKmToMiles);
        final TextView txtHistory=child.findViewById(R.id.txtHistory);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();
                if(v==txtMilesToKm)
                {
                    mcontext.startActivity(new Intent(mcontext, MilesToKm.class));

                }
                if(v==txtKmToMiles)
                {
                    mcontext.startActivity(new Intent(mcontext, KmtoMiles.class));

                }
                if(v==txtHistory)
                {
                    mcontext.startActivity(new Intent(mcontext, Records.class));
                }

            }
        };


        txtMilesToKm.setOnClickListener(listener);
        txtKmToMiles.setOnClickListener(listener);
        txtHistory.setOnClickListener(listener);


    }


    // CAST CONTEXT TO ACTIVITY
    private static Activity getactivity(Context context)
    {
        return (Activity) context;
    }


    /// DIALOG TO SHOW RESULT OF CALCULATION
    public static  void showResultDialog(Context context, ResultModel model)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(model.getStatus());
        builder.setMessage(model.getResult());
        builder.setCancelable(true);
        builder.setPositiveButton("Ok",null);
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }
}
