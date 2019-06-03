package com.google.android.gms.samples.vision.ocrreader;

import android.Manifest;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText etph,etname,etvn;
     String phoneNo,name,vno;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etph = (EditText) findViewById(R.id.etphn);
        etname= (EditText) findViewById(R.id.etnm);
        etvn = (EditText) findViewById(R.id.etvn);
        databaseReference = FirebaseDatabase.getInstance().getReference("/Registered-users");
        progressDialog = new ProgressDialog(this);
    }

     public void btn_register_click(View v)
     {


        phoneNo = etph.getText().toString();
        name = etname.getText().toString();
        vno = etvn.getText().toString();
         if(vno.isEmpty())
         {
             etvn.setError("Vehicle number can not be empty");
             etvn.requestFocus();
             return;
         }

        if(name.isEmpty())
        {
            etname.setError("Please enter your name");
            etname.requestFocus();
            return;
        }
         if(phoneNo.isEmpty())
         {
             etph.setError("Please enter your phone no");
             etph.requestFocus();
             return;
         }

        progressDialog.setMessage("Registering User");
        progressDialog.show();
        saveUserData(vno,name,phoneNo);




     }

     private void saveUserData(String vno,String name,String phoneNo)
     {
         final String phoneNo1 = phoneNo;
         final String name1 = name;
         final String vno1 = vno;
         Vehicles obj = new Vehicles(vno,name,phoneNo);

         databaseReference.child(vno).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
             @Override
             public void onComplete(@NonNull Task<Void> task) {
                 progressDialog.dismiss();
                 Toast.makeText(getApplicationContext(),"Stored Successfully",Toast.LENGTH_SHORT).show();


             }
         });

     }


}

