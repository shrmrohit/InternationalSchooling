package com.example.internationalschooling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RegisterSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_select);

    }
    public void StudentRegistration(View view) {
        Intent intent = new Intent(RegisterSelect.this,RegisterUser.class);
        intent.putExtra("role","teacher");
        startActivity(intent);
        finish();
    }

    public void TeacherRegistration(View view) {
        Intent intent = new Intent(RegisterSelect.this,RegisterUser.class);
        intent.putExtra("role","student");
        startActivity(intent);
        finish();
    }

    public void SchoolRegistration(View view) {
        Intent intent = new Intent(RegisterSelect.this,RegisterUser.class);
        intent.putExtra("role","student");
        startActivity(intent);
        finish();
    }

}
