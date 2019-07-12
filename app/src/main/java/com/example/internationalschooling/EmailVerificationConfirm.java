package com.example.internationalschooling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EmailVerificationConfirm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification_confirm);
    }
    public void LoginNow(View view) {
        Intent intent =new Intent(EmailVerificationConfirm.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
