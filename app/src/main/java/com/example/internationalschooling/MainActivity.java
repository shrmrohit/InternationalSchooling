package com.example.internationalschooling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void LoginClick(View view) {
        Intent intent= new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
    }

        public void RegisterClick(View view) {
        Intent intent= new Intent(MainActivity.this,RegisterSelect.class);
        startActivity(intent);

    }
    }