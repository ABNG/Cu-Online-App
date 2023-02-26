package com.example.gamabubakar.cuonlineapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class SharedElement extends AppCompatActivity {
    Button btn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS); // use for transition animation
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_element);
        tv=findViewById(R.id.textView2);
        Bundle b=getIntent().getExtras();
        String name=b.getString("name");
        tv.setText(name);
        init();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finishAfterTransition();
        return true;
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Shared Transiton");
        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAfterTransition();
            }
        });
    }
}
