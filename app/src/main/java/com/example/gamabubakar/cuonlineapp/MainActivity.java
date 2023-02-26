package com.example.gamabubakar.cuonlineapp;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    AppCompatEditText username;
    AppCompatEditText password;
    TextInputLayout userlayout;
    TextInputLayout passwordlayout;
    RelativeLayout rl;
    AppCompatButton Raised;
    Snackbar snack;
    SharedRef myref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myref=new SharedRef(this);
        if(!(myref.LoadData().equalsIgnoreCase("No name"))){
            finish();
            Intent intent = new Intent(MainActivity.this, Data.class);
            startActivity(intent);

        }
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        userlayout = findViewById(R.id.username_inputtextlayout);
        passwordlayout = findViewById(R.id.password_inputtextlayout);
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(password.hasFocus()==false)
                    passwordlayout.setErrorEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (username.getText().toString().isEmpty()) {
                    userlayout.setErrorEnabled(true);
                    userlayout.setError("Please Enter user name");
                } else {
                    userlayout.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        passwordlayout.setCounterEnabled(true);
        passwordlayout.setCounterMaxLength(8);

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(username.hasFocus()==false)
                    userlayout.setErrorEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (password.getText().toString().isEmpty()) {

                    passwordlayout.setErrorEnabled(true);
                    passwordlayout.setError("Please Enter password");
                } else {
                    passwordlayout.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        rl = findViewById(R.id.RL);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.hasFocus()==false)
                    userlayout.setErrorEnabled(false);
                if(password.hasFocus()==false)
                    passwordlayout.setErrorEnabled(false);
            }
        });  //click empty screen to disable focus
        Raised = findViewById(R.id.raised);

        Raised.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: THESE TWO LINES USE TO HIDE KEYBOARD
                InputMethodManager mgr= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.hideSoftInputFromWindow(password.getWindowToken(),0);

                if((username.getText().toString().length()>0) && (password.getText().toString().length()>0)
                        && (password.getText().toString().length()<=8)) {
                    myref.SaveData(username.getText().toString(),password.getText().toString());
                    finish();
                    Intent intent = new Intent(MainActivity.this, Data.class);
                    startActivity(intent);
                }
                else {
                    snack=Snackbar.make(v,"Please enter username and password",Snackbar.LENGTH_INDEFINITE);
                    snack.setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snack.dismiss();
                        }
                    });
                    snack.setActionTextColor(getResources().getColor(R.color.snackbaractioncolor));
                    View sbview=snack.getView();
                    sbview.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                    TextView text=sbview.findViewById(android.support.design.R.id.snackbar_text);
                    text.setTextColor(getResources().getColor(R.color.snackbartextcolor));
                    text.setTextSize(18);
                    snack.show();
                }
            }
        });
    }
}
