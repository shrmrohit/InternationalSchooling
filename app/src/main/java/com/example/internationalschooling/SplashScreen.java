package com.example.internationalschooling;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread timer= new Thread()
        {
            public void run()
            {
                try
                {
                    //Display for 3 seconds
                    sleep(2000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent);

                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause()
    {
        // TODO Auto-generated method stub
        super.onPause();
        finish();

    }

    public void ClickOnRead(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(SplashScreen.this);


        builder.setTitle("View Terms and Policy");


        builder.setMessage("You can view full details of Terms and Privacy choose any of them");


        builder.setPositiveButton("Terms", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String url = "https://www.internationalschooling.org/terms-of-use.html";
                Intent int1 = new Intent(Intent.ACTION_VIEW);
                int1.setData(Uri.parse(url));
                startActivity(int1);

            }
        });

        builder.setNegativeButton("Privacy policy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String url = "https://www.internationalschooling.org/privacy-policy.html";
                Intent int2 = new Intent(Intent.ACTION_VIEW);
                int2.setData(Uri.parse(url));
                startActivity(int2);
                dialog.cancel();
            }
        });

        builder.setNeutralButton("Cancel",
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        AlertDialog diag = builder.create();
        diag.show();
    }

}


