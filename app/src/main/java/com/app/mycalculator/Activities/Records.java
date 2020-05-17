package com.app.mycalculator.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.app.mycalculator.Adapter.RecordsAdapter;
import com.app.mycalculator.Dialog.DialogHandler;
import com.app.mycalculator.Firebase.FirebaseDatabaseHandler;
import com.app.mycalculator.Model.FireBaseRecordModel;
import com.app.mycalculator.R;
import com.app.mycalculator.Utils.ConnectionStatus;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;
import com.google.firebase.Timestamp;

public class Records extends AppCompatActivity {


    RecyclerView recylcerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);




        if(!ConnectionStatus.isAvailable(this))
        {
            DialogHandler.showNoInternetDialog(this);
            return;
        }

        setUpViews();
        FirebaseDatabaseHandler.getRecords(this,recylcerList);
    }


    private void setUpViews()
    {

        recylcerList=findViewById(R.id.recylcerList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recylcerList.setLayoutManager(mLayoutManager);
        recylcerList.setItemAnimator(new DefaultItemAnimator());

    }
}
