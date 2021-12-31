package com.shariaty.translatorfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class translation extends AppCompatActivity {

    private TextView yourEdt,resultEdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation);


        yourEdt= findViewById(R.id.YourEdt);
        resultEdt=findViewById(R.id.ResultEdt);
        Intent intent=getIntent();
        Bundle extras=intent.getExtras();
        yourEdt.setText(extras.getString("word"));
        resultEdt.setText(extras.getString("result"));
    }
}