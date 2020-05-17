package com.app.mycalculator.Firebase;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.mycalculator.Activities.Records;
import com.app.mycalculator.Adapter.RecordsAdapter;
import com.app.mycalculator.Model.FireBaseRecordModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class FirebaseDatabaseHandler
{




    public   static  void saveToFirebaseDatabase(final Context mcontext, String equation)
    {

        FireBaseRecordModel obj=new FireBaseRecordModel();
        obj.setEquation(equation);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Records")
                .add(obj)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference)
                    {
                        Toast.makeText(mcontext,"Record Saved Successfully",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(mcontext,"Failed To Save Record",Toast.LENGTH_SHORT).show();
                    }
                });


    }


    public  static  void getRecords(final Context mcontext, final RecyclerView recylcerList)
    {


        final ArrayList<FireBaseRecordModel> list=new ArrayList<>();
        final ProgressDialog pb=new ProgressDialog(mcontext);
        pb.setMessage("Getting Records..");
        pb.setCancelable(false);
        pb.show();



        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Records")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        pb.dismiss();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult())
                            {
                                FireBaseRecordModel obj=new FireBaseRecordModel();
                                Map<String, Object> data = document.getData();
                                obj.setEquation(data.get("equation").toString());
                                obj.setTimeStamp((Timestamp) data.get("timeStamp"));
                                list.add(obj);
                            }

                            if(list.size()<1)
                            {
                                Toast.makeText(mcontext,"No Record Found",Toast.LENGTH_SHORT).show();
                                return;
                            }

                            recylcerList.setAdapter(new RecordsAdapter(mcontext,list));
                        }
                        else
                        {

                            Toast.makeText(mcontext,"Failed To Get Records",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

}
