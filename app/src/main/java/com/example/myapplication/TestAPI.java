package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TestAPI extends AppCompatActivity {
    Button btnclick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api);

        btnclick = (Button) findViewById(R.id.btntest);
        btnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click();
            }
        });
    }

    private void click() {
        Intent i=new Intent(TestAPI.this,MainActivity.class);
        startActivity(i);
    }
}
