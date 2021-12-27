package com.shariaty.translatorfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class SecondActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private Spinner spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        
        spinner=findViewById(R.id.spinner1);
        spinner2=findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence>spinneradapter=ArrayAdapter.createFromResource(this,R.array.Lang_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence>spinneradapter2=ArrayAdapter.createFromResource(this,R.array.Lang_array, android.R.layout.simple_spinner_item);
        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneradapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinneradapter);
        spinner2.setAdapter(spinneradapter2);

        spinner.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(),choice,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}