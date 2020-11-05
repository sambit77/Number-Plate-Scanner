package com.google.android.gms.samples.vision.ocrreader;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage, txtvname, tvcontact, tvfeedback,tvloginfo,tvinfo;
    private ImageView iv2, iv3;
    private Button b5, buttonContact, buttonFeedback,adlogin;
    //private ImageSwitcher imgswch;
    //private Button bnext, bprev;
    //int[] imageIDs = {R.drawable.aman, R.drawable.ashutosh, R.drawable.biswajit, R.drawable.samarth, R.drawable.sambit, R.drawable.manish};
    //String[] imagenames = {"Aman Sharma", "Ashutosh Pradhan", "Biswajit Mangaraj", "Samarth Mishra", "Sambit Pradhan", "Manish Swain"};
    //int count = imageIDs.length;
    //int currentIndex = 0, indexPrevious = imageIDs.length;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:


                    //imgswch = (ImageSwitcher) findViewById(R.id.myimageview);
                    //bnext.setVisibility(View.INVISIBLE);
                    //bprev.setVisibility(View.INVISIBLE);
                    //imgswch.setVisibility(View.INVISIBLE);
                   // txtvname.setVisibility(View.INVISIBLE);
                    tvinfo.setVisibility(View.INVISIBLE);
                    tvloginfo.setVisibility(View.INVISIBLE);
                    buttonFeedback.setVisibility(View.INVISIBLE);
                    //buttonContact.setVisibility(View.INVISIBLE);
                    //tvcontact.setVisibility(View.INVISIBLE);
                    tvfeedback.setVisibility(View.INVISIBLE);
                    adlogin.setVisibility(View.INVISIBLE);

                    mTextMessage.setText("Press Scan To Begin");
                    mTextMessage.setVisibility(View.VISIBLE);
                    iv2.setVisibility(View.VISIBLE);
                    iv3.setVisibility(View.VISIBLE);
                    b5.setVisibility(View.VISIBLE);


                    return true;
                case R.id.navigation_dashboard:

                    adlogin.setVisibility(View.VISIBLE);
                    iv2.setVisibility(View.VISIBLE);
                    iv3.setVisibility(View.VISIBLE);
                    mTextMessage.setText("Use Default Admin Credentials To Check Functionality");
                    mTextMessage.setVisibility(View.VISIBLE);
                    tvloginfo.setVisibility(View.VISIBLE);

                    buttonFeedback.setVisibility(View.INVISIBLE);
                    tvinfo.setVisibility(View.INVISIBLE);
                    //buttonContact.setVisibility(View.INVISIBLE);
                    //tvcontact.setVisibility(View.INVISIBLE);
                    tvfeedback.setVisibility(View.INVISIBLE);
                    b5.setVisibility(View.INVISIBLE);

                    /*Intent j = new Intent(HomeActivity.this, AdminLogin.class);
                    startActivity(j);*/


                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setVisibility(View.INVISIBLE);
                    iv2.setVisibility(View.VISIBLE);
                    iv3.setVisibility(View.INVISIBLE);
                    b5.setVisibility(View.INVISIBLE);
                    adlogin.setVisibility(View.INVISIBLE);
                    tvloginfo.setVisibility(View.INVISIBLE);
                    //bnext.setVisibility(View.VISIBLE);
                    //bprev.setVisibility(View.VISIBLE);
                    //imgswch.setVisibility(View.VISIBLE);
                   // txtvname.setVisibility(View.VISIBLE);
                    tvinfo.setVisibility(View.VISIBLE);
                    buttonFeedback.setVisibility(View.VISIBLE);
                    //buttonContact.setVisibility(View.VISIBLE);
                    //tvcontact.setVisibility(View.VISIBLE);
                    tvfeedback.setVisibility(View.VISIBLE);


                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTextMessage = (TextView) findViewById(R.id.textView5);
        iv2 = (ImageView) findViewById(R.id.imageView2);
        iv3 = (ImageView) findViewById(R.id.imageView3);
        b5 = (Button) findViewById(R.id.button5);
        adlogin = (Button) findViewById(R.id.adlogin);
        tvloginfo = (TextView) findViewById(R.id.tvloginfo);
        //bnext = (Button) findViewById(R.id.buttonNext);
        //bprev = (Button) findViewById(R.id.buttonPrev);
        //imgswch = (ImageSwitcher) findViewById(R.id.myimageview);
        //txtvname = (TextView) findViewById(R.id.textViewName);
        //buttonContact = (Button) findViewById(R.id.buttonContact);
        buttonFeedback = (Button) findViewById(R.id.buttonFeedback);
        //tvcontact = (TextView) findViewById(R.id.textViewcontact);
        tvfeedback = (TextView) findViewById(R.id.textViewfeedback);
        tvinfo = (TextView) findViewById(R.id.tvinfo);
        tvinfo.setVisibility(View.INVISIBLE);
        tvloginfo.setVisibility(View.INVISIBLE);
        buttonFeedback.setVisibility(View.INVISIBLE);
//        buttonContact.setVisibility(View.INVISIBLE);
  //      tvcontact.setVisibility(View.INVISIBLE);
        tvfeedback.setVisibility(View.INVISIBLE);
        adlogin.setVisibility(View.INVISIBLE);

//        txtvname.setVisibility(View.INVISIBLE);

        //bnext.setVisibility(View.INVISIBLE);
        //bprev.setVisibility(View.INVISIBLE);
        //imgswch.setVisibility(View.INVISIBLE);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        /*imgswch.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                // TODO Auto-generated method stub

                // Create a new ImageView and set it's properties
                ImageView imageView = new ImageView(getApplicationContext());
                // set Scale type of ImageView to Fit Center
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                // set the Height And Width of ImageView To FIll PARENT
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams
                        (LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
                return imageView;
            }
        });*/

        //imgswch.setImageResource(imageIDs[0]);
        //txtvname.setText(imagenames[0]);


        /* bnext.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                imgswch.setInAnimation(HomeActivity.this,R.anim.in_from_left);
                imgswch.setOutAnimation(HomeActivity.this,R.anim.out_to_right);
                currentIndex++;
                //  imageSwitcher.showNext();
                //  Check If index reaches maximum then reset it
                if (currentIndex == count)
                    currentIndex = 0;
                imgswch.setImageResource(imageIDs[currentIndex]); // set the image in ImageSwitcher
                txtvname.setText(imagenames[currentIndex]);
            }
        });

        bprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgswch.setInAnimation(HomeActivity.this,R.anim.in_from_right);
                imgswch.setOutAnimation(HomeActivity.this,R.anim.out_to_left);
                //   imageSwitcher.showPrevious();
                --currentIndex;
                //  Check If index reaches maximum then reset it
                if (currentIndex < 0)
                    currentIndex = imageIDs.length-1;
                imgswch.setImageResource(imageIDs[currentIndex]); // set the image in ImageSwitcher
                txtvname.setText(imagenames[currentIndex]);
            }
        });
*/

    }

    public void btn_scan(View v) {
        Intent i = new Intent(HomeActivity.this, OcrCaptureActivity.class);
        startActivity(i);
    }
    public  void adlogincall(View v)
    {
        Intent j = new Intent(HomeActivity.this, AdminLogin.class);
        startActivity(j);
    }

    public void btn_contact(View v) {
//        Toast.makeText(this,"Calling code here",Toast.LENGTH_SHORT).show();


        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CALL_PHONE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        0);
            }
        } else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:8763964692"));
            startActivity(callIntent);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:8763964692"));
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(callIntent);
                }

                 else {
                    Toast.makeText(getApplicationContext(),
                            "Call faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }
    public void btn_feedback(View v)
    {
//        Toast.makeText(this,"Mailing code here",Toast.LENGTH_SHORT).show();
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{ "sambitgulu@gmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
        email.putExtra(Intent.EXTRA_TEXT, "write your valuable feedback / suggestions here...");

//need this to prompts email client only
        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(1);
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());

    }
}
