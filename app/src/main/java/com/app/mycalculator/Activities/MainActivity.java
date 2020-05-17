package com.app.mycalculator.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.mycalculator.Dialog.DialogHandler;
import com.app.mycalculator.EquationHandler.EquationCalculator;
import com.app.mycalculator.Firebase.FirebaseDatabaseHandler;
import com.app.mycalculator.Model.ResultModel;
import com.app.mycalculator.R;
import com.app.mycalculator.Utils.ConnectionStatus;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    TextView txtScreen;
    ImageView imgMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init()
    {

        txtScreen=findViewById(R.id.txtScreen);
        imgMenu=findViewById(R.id.imgMenu);
        imgMenu.setOnClickListener(this);


        if(!ConnectionStatus.isAvailable(this))
        {
            DialogHandler.showNoInternetDialog(this);
        }
    }

    public void buttonclick(View view)
    {


            if(view.getId()==R.id.btndel)
            {
                String s = txtScreen.getText().toString();
                if(!TextUtils.isEmpty(s))
                {
                    txtScreen.setText(s.substring(0, s.length() - 1));
                }
            }
            else
            if (view.getId()==R.id.btnC)
            {


                txtScreen.setText(null);
            }
            else
            if(view.getId()==R.id.btnequal)
            {

                ResultModel model= EquationCalculator.calculate(txtScreen.getText().toString());
                 DialogHandler.showResultDialog(this,model);

                 if(!model.getStatus().equalsIgnoreCase("Error"))
                 {
                     if(ConnectionStatus.isAvailable(this))
                     {

                         FirebaseDatabaseHandler.saveToFirebaseDatabase(this,txtScreen.getText().toString()+" = "+model.getResult());

                     }

                 }
            }
            else
            {

                Button btn= (Button) view;
                String  chartoAdd = btn.getText().toString();
                if(view.getId()==R.id.btnsqrt)
                {
                    chartoAdd="sqrt";
                }
                if(EquationCalculator.shouldAdd(txtScreen.getText().toString(),btn.getText().toString()))
                {
                    txtScreen.setText(String.format("%s%s", txtScreen.getText().toString(), chartoAdd));
                }
                else
                {

                    String s = txtScreen.getText().toString();
                    txtScreen.setText(s.substring(0, s.length() - 1));
                    txtScreen.setText(String.format("%s%s", txtScreen.getText().toString(), chartoAdd));
                }


            }




    }


    @Override
    public void onClick(View v)
    {

        if(v==imgMenu)
        {

            DialogHandler.showMenuDialog(this);

        }


    }
}
