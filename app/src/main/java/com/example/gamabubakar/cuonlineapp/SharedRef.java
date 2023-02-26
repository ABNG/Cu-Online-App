package com.example.gamabubakar.cuonlineapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedRef {
    SharedPreferences ShredRef;

    public SharedRef(Context context){
        ShredRef=context.getSharedPreferences("myRef",Context.MODE_PRIVATE);
    }

    public  void SaveData(String UserName,String Password){
        SharedPreferences.Editor editor=ShredRef.edit();
        editor.putString("UserName",UserName);
        editor.putString("Password",Password);
        editor.commit();
    }

    public String LoadData(){
        String FileContent=ShredRef.getString("UserName","No name");
        return FileContent;
    }

}