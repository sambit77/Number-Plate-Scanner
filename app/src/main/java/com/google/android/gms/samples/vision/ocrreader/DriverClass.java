package com.google.android.gms.samples.vision.ocrreader;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class DriverClass extends AppCompatActivity {

    EditText et1;
    Firebase myFirebase,myFirebase2;
    TextView tvname,tvnameshow,tvphone,tvphoneShow;
    ProgressDialog progressDialog;
    String myChildphone;
    String myChildname;
    String number;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_class);
        et1 =(EditText) findViewById(R.id.editTextd);
        tvname = (TextView) findViewById(R.id.textViewname);
        tvnameshow = (TextView) findViewById(R.id.textViewnameShoe);
        tvphone = (TextView) findViewById(R.id.textViewphone);
        tvphoneShow = (TextView) findViewById(R.id.textViewphoneShow);
        progressDialog = new ProgressDialog(this);



        Intent intent2 = getIntent();
        String receive = intent2.getStringExtra("5555");
        et1.setText(receive);
        Firebase.setAndroidContext(this);

    }
    public void btn_fetch(View v)
    {
        number = et1.getText().toString().trim();
        if(number.isEmpty())
        {
            et1.setError("Vehicle number missing");
            et1.requestFocus();
            return;
        }
        progressDialog.setMessage("Fetching data");
        progressDialog.show();

        String url = "https://number-plate-scanner-f1441.firebaseio.com/Registered-users/"+number+"/name";
        String url2 = "https://number-plate-scanner-f1441.firebaseio.com/Registered-users/"+number+"/phoneNo";
        myFirebase = new Firebase(url);
        myFirebase2 = new Firebase(url2);
        myFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                myChildname = dataSnapshot.getValue(String.class);
                progressDialog.dismiss();
                tvname.setVisibility(View.VISIBLE);
                tvnameshow.setText(myChildname);
                if(tvnameshow.getText().length()== 0)
                {
                    Toast.makeText(DriverClass.this,"Vehicle is not registered",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Error retrieving data",Toast.LENGTH_SHORT).show();

            }
        });
        myFirebase2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                myChildphone = dataSnapshot.getValue(String.class);
                tvphone.setVisibility(View.VISIBLE);
                tvphoneShow.setText(myChildphone);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Error retrieving value",Toast.LENGTH_SHORT).show();

            }
        });


    }
    public void btn_send(View v)
    {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
        else
        {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(myChildphone, null, "Hello"+"\t"+myChildname+"\t"+"your vehicle"+"\t"+number+"\t"+"Crossed the main gate right now", null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(myChildphone, null, "Hello"+"\t"+myChildname+"\t"+"your vehicle"+"\t"+number+"Crossed the main gate right now", null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }

    public void btn_home(View v)
    {
        Intent i = new Intent(DriverClass.this,HomeActivity.class);
        startActivity(i);
    }
}
