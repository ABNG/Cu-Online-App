package com.example.gamabubakar.cuonlineapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

public class Data extends AppCompatActivity {
RecyclerView recyclerView;
SharedRef myref;
ArrayList<String> data=new ArrayList<>(); //we can make a class with objects and send it to recyclerview
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        myref=new SharedRef(this);
        getSupportActionBar().setTitle("Welcome "+myref.LoadData());
        data.add("wwe");
        data.add("yes");
        data.add("wwe");
        data.add("yes");
        data.add("wwe");
        data.add("yes");
        data.add("wwe");
        data.add("yes");
        data.add("wwe");
        data.add("yes");
        data.add("wwe");
        data.add("yes");
        data.add("wwe");
        recyclerView=findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecycleAdapter(this,data));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
