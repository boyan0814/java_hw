package com.example.hw1_ble;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        Button btn = (Button)findViewById(R.id.btn_welcome);
        btn.setOnClickListener(btnListener);
    }

    private View.OnClickListener btnListener = new View.OnClickListener(){
        public void onClick(View v){
            Intent intent = new Intent();
            intent.setClass(Welcome.this,MainActivity.class);
            startActivity(intent);
        }
    };
}
