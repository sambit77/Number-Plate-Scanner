package com.google.android.gms.samples.vision.ocrreader;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLogin extends AppCompatActivity {
    EditText etun,etpw;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        etun = (EditText) findViewById(R.id.editTextUN);
        etpw = (EditText) findViewById(R.id.editTextPW);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();


    }
    public void btn_login_click(View v)
    {
        String username = etun.getText().toString().trim();
        String password = etpw.getText().toString().trim();

        if(username.isEmpty())
        {
            etun.setError("Username can't be empty");
            etun.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            etpw.setError("Please enter your password");
            etpw.requestFocus();
            return;
        }
        progressDialog.setMessage("Logging in");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
             if(task.isSuccessful())
             {
                 progressDialog.dismiss();
                 Intent myintent = new Intent(AdminLogin.this,RegisterActivity.class);
                 startActivity(myintent);
             }
             else
             {
                 progressDialog.dismiss();
                 Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_SHORT).show();
             }
            }
        });


    }
}
