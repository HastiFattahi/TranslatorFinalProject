package com.shariaty.translatorfinalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SecondActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner1;
    private Spinner spinner2;
    private EditText searchedittxt;
    private ImageButton searchimg;
    String language1,word,language2;
    RecyclerView myRec;
    MyAdapter myAdapter;
    ArrayList<String> words ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        RequestQueue queue = Volley.newRequestQueue(this);
        words= new ArrayList<>();
        myRec=findViewById(R.id.recentword);

        String token="68513.u377vGrD7FKvc91OuO9hPHGTjPGmuiNlHqT4eV7M";




        spinner1=findViewById(R.id.spinner1);
        spinner2=findViewById(R.id.spinner2);
        searchedittxt=findViewById(R.id.searchedittxt);
        searchimg=findViewById(R.id.searchimg);

        //Spinner adapter
        ArrayAdapter<CharSequence>spinneradapter=ArrayAdapter.createFromResource(this,R.array.Lang_array, android.R.layout.simple_spinner_item);
        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(spinneradapter);
        spinner2.setAdapter(spinneradapter);

        spinner1.setOnItemSelectedListener(this);


        searchimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                word = searchedittxt.getText().toString();
                language1 = spinner1.getSelectedItem().toString();
                language2 = spinner2.getSelectedItem().toString();
                String chosenlanguage="";
                if(language1.equals("English")){ chosenlanguage="en2fa";}
                else
                {chosenlanguage="dehkhoda";}

                StringRequest stringRequest = new StringRequest(Request.Method.GET, GetURL(token,word,chosenlanguage),
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try{
                                    JSONObject body=new JSONObject(response);
                                    JSONObject a=new JSONObject(body.getString("data"));
                                    JSONArray results=a.getJSONArray("results");
                                    String result="";
                                    for(int i=0;i<results.length();i++){
                                        JSONObject jo=results.getJSONObject(i);
                                        result=jo.getString("text");
                                    }

                                    words.add(word);
                                    myRec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                    myAdapter = new MyAdapter(getApplicationContext(), words);

                                    myRec.setAdapter(myAdapter);
                                    myAdapter.notifyDataSetChanged();
                                    Intent intent=new Intent(getApplicationContext(),translation.class);
                                    intent.putExtra("result",result);
                                    intent.putExtra("word",word);
                                    startActivity(intent);


                                }catch (JSONException j){searchedittxt.setText(j.toString());}


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        textView.setText("That didn't work!");
                        Toast.makeText(getApplicationContext(),""+error,Toast.LENGTH_LONG).show();
                    }
                });



                queue.add(stringRequest);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        language1= spinner1.getSelectedItem().toString();
        language2= spinner2.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public String GetURL(String Token,String Word,String language){

            return "http://api.vajehyab.com/v3/search?token="+Token+"&q="+Word+"&type=exact&filter="+language;

    }

}