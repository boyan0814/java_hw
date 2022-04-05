package com.example.phone_book;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    //private static final String TAG = "MainActivity";
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]
    //public static final int RC_SIGN_IN = 1;
    private FirebaseAuth.AuthStateListener authListener = null;
    TextView mainLabel = null;
    private Button btn,signOut;
    private int check_permission_times = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();

        mAuth = FirebaseAuth.getInstance();
        mainLabel = findViewById(R.id.main_textview);
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed – user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

        signOut = findViewById(R.id.btn_logout);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
            }
        });

        btn = (Button)findViewById(R.id.btn_welcome);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload(currentUser);
            mAuth.addAuthStateListener(authListener);
        } else {
            Toast.makeText(MainActivity.this, "User not login",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void checkPermission() {
        //check condition
        if (ActivityCompat.checkSelfPermission(MainActivity.this
                , Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_CONTACTS}, 100);
        }else {
            Toast.makeText(MainActivity.this,"Permisssion Get,", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100 && grantResults.length>0 && grantResults[0]
                == PackageManager.PERMISSION_GRANTED){
            //Toast.makeText(MainActivity.this,"Permisssion Get,", Toast.LENGTH_SHORT).show();
            check_permission_times = 0;
        }else {
            //Toast.makeText(MainActivity.this,"Permisssion Denied,", Toast.LENGTH_SHORT).show();
            check_permission_times++;
            if (check_permission_times <= 10){
                checkPermission();
            }
        }
    }

    private void sendEmailVerification() {
        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // Email sent
                    }
                });
        // [END send_email_verification]
    }

    private void reload(FirebaseUser currentUser) {
        mainLabel.setText(currentUser.getEmail());
    }
}