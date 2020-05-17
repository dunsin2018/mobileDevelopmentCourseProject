package com.app.mycalculator.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.mycalculator.Dialog.DialogHandler;
import com.app.mycalculator.EquationHandler.EquationCalculator;
import com.app.mycalculator.Firebase.FirebaseDatabaseHandler;
import com.app.mycalculator.Model.ResultModel;
import com.app.mycalculator.R;
import com.app.mycalculator.Utils.ConnectionStatus;

public class MilesToKm extends AppCompatActivity implements View.OnClickListener {



    EditText editText;
    Button btnConvert;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miles_to_km);

        init();
    }

    private void init()
    {

        editText=findViewById(R.id.edittextMiles);
        btnConvert=findViewById(R.id.btnConvert);

        btnConvert.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {

        if(v.getId()==R.id.btnConvert)
        {

            if(!TextUtils.isEmpty(editText.getText()))
            {


                ResultModel result=EquationCalculator.ConvertMilesToKm(editText.getText().toString());
                DialogHandler.showResultDialog(this,result);
                if(!result.getStatus().equalsIgnoreCase("Error"))
                {
                    if(ConnectionStatus.isAvailable(this))
                    {

                        FirebaseDatabaseHandler.saveToFirebaseDatabase(this,editText.getText().toString()+" Miles  = "+result.getResult()+" Kms");

                    }

                }
            }

        }

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
