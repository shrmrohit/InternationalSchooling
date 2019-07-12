package com.example.internationalschooling;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.internationalschooling.Student.StudentHome;
import com.example.internationalschooling.Student.StudentRegistrationS1;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class RegisterUser extends AppCompatActivity {
    private TextInputLayout em1,em2,pw1,pw2;
    private CheckBox checkBox;
    String email,confirm_email,password,confirm_password;
    String ROLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        em1=(TextInputLayout) findViewById(R.id.editText3);
        em2=(TextInputLayout) findViewById(R.id.editText4);
        pw1=(TextInputLayout) findViewById(R.id.editText5);
        pw2=(TextInputLayout) findViewById(R.id.editText6);
        checkBox =(CheckBox)findViewById(R.id.checkBox);

        ROLE = getIntent().getExtras().getString("ROLE");

    }

    public void ClickOnRead(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterUser.this);


        builder.setTitle("View Terms and Policy");


        builder.setMessage("you can view full details of Terms and Privacy choose any of them");


        //Button One : Yes
        builder.setPositiveButton("Terms", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String url = "https://www.internationalschooling.org/terms-of-use.html";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


        //Button Two : No
        builder.setNegativeButton("Privacy policy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String url = "https://www.internationalschooling.org/privacy-policy.html";
                Intent i1 = new Intent(Intent.ACTION_VIEW);
                i1.setData(Uri.parse(url));
                startActivity(i1);
                dialog.cancel();
            }
        });

        //Button Three : Neutral
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        AlertDialog diag = builder.create();
        diag.show();
    }

    public void Register(View view) {

        email=em1.getEditText().getText().toString().trim();
        confirm_email=em2.getEditText().getText().toString().trim();
        password=pw1.getEditText().getText().toString().trim();
        confirm_password=pw2.getEditText().getText().toString().trim();

        Log.v("confirm_email : ",confirm_email);
        Log.v("email : ", email);
        Log.v("password : ", password);
        Log.v("confirm_password : ",confirm_password);
        if(Validations.isValidEmail(email)){
         if(Validations.isValidPass(password)){

         if(email.equals(confirm_email) && password.equals(confirm_password)){

         if(checkBox.isChecked()){

         userRegister();
          }
         else{
         Toast.makeText(RegisterUser.this,"Accept terms and Condition",Toast.LENGTH_SHORT).show();
           }
         }

         else
         {
         Toast.makeText(RegisterUser.this,"Email or password mismatch",Toast.LENGTH_SHORT).show();
         }
         }
         else
         {
         Toast.makeText(RegisterUser.this,"Password atleast 6 digits",Toast.LENGTH_SHORT).show();
         }
         }
         else
         {
         Toast.makeText(RegisterUser.this,"Email format not valid",Toast.LENGTH_SHORT).show();
         }



    }

    private void userRegister() {
        email=em1.getEditText().getText().toString().trim();
        confirm_email=em2.getEditText().getText().toString().trim();
        password=pw1.getEditText().getText().toString().trim();
        confirm_password=pw2.getEditText().getText().toString().trim();

        RequestQueue requestQueue = Volley.newRequestQueue(RegisterUser.this);

        String Url = Config.RegisterUrlStage1;

        String jsonBody ="{\n" +
                "\"authentication\":{\n" +
                "\"hash\":\"sladfjlkasldflsaj\",\n" +
                "\"userType\":\"STUDENT\",\n" +
                "\"userId\":\"\",\n" +
                "\"studentId\":\"\",\n" +
                "\"parentId\":\"\"\n" +
                "},\n" +
                "\"requestData\":{\n" +
                "\"signupDTO\":{\n" +
                "\"email\":\""+email+"\",\n" +
                "\"confirmEmail\":\""+confirm_email+"\",\n" +
                "\"password\":\""+password+"\",\n" +
                "\"confirmPassword\":\""+confirm_password+"\",\n" +
                "\"captcha\":\"123456\"\n" +
                "}\n" +
                "}\n" +
                "}";


        final String mRequestBody = jsonBody;
        StringRequest stringRequest = new StringRequest(Request.Method.POST,Url,
        new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
        Log.v("response",response);
        Log.v("helo","helo");
            Intent intent = new Intent(RegisterUser.this, StudentRegistrationS1.class);
            startActivity(intent);
            finish();
            JSONObject  obj = null;
       try {
        obj = new JSONObject(response);
       } catch (JSONException e) {
       e.printStackTrace();
       }
       String statusCode = null;
      try {
      statusCode = obj.getString("statusCode");
      } catch (JSONException e) {
      e.printStackTrace();
      }
      String message = null;
      try {
      message = obj.getString("message");
      }  catch (JSONException e) {
      e.printStackTrace();
      }
      if(message.trim().
      equals("You are already registered with International Schooling. Please Login to continue.")){
      Toast.makeText(RegisterUser.this,message,Toast.LENGTH_SHORT).show();
      Intent intent1 = new Intent(RegisterUser.this,UserAlreadyExist.class);
      startActivity(intent1);
      finish();
                        }
     else if(message.trim().
     equals("SIGNUP STAGE 1 IS SUCCESSFULL")){
     Toast.makeText(RegisterUser.this,"Email Sent Success",Toast.LENGTH_SHORT).show();
     Intent intent2 = new Intent(RegisterUser.this,EmailVerificationConfirm.class);
     startActivity(intent2);
     finish();
     }
     }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                            mRequestBody, "utf-8");
                    return null;
                }
            }

        };
        requestQueue.add(stringRequest);

    }
}
