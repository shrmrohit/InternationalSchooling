package com.example.internationalschooling;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.support.annotation.NonNull;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.internationalschooling.Student.StudentRegistrationS1;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;
import com.example.internationalschooling.Student.StudentRegistrationS1;
import com.example.internationalschooling.Student.StudentRegistrationS2;
import com.example.internationalschooling.Student.StudentRegistrationS3;

import java.io.UnsupportedEncodingException;

public class LoginActivity extends AppCompatActivity {
    private ProgressBar pb;
    private Button btnlogin;
    private TextInputLayout email,password;
    private EditText captcha;
    private Button recaptcha;
    private CheckBox checkBox;
    private ImageView imageView, imageView1, imageView2;
    private TextView textView1, textView2;

    private static final String TAG = LoginActivity.class.getSimpleName();

    public static final String SITE_KEY = "6LcDWKwUAAAAAF28Wi6yaA06Lg71bdbYqs-_kn1B";
    public static final String SITE_SECRET_KEY = "6LcDWKwUAAAAABtBBcHuSu2dPtlIAYftBcOI5jaB";
    String userResponseToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (TextInputLayout) findViewById(R.id.editText3);
        password = (TextInputLayout) findViewById(R.id.editText4);
        checkBox=(CheckBox)findViewById(R.id.checkBox);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        btnlogin = (Button) findViewById(R.id.button10);
        pb = (ProgressBar) findViewById(R.id.progressBar);
        imageView = (ImageView) findViewById(R.id.imageView8);
        imageView2 = (ImageView) findViewById(R.id.imageView);

    }

    public void LoginClick(View view) {

        String Url = Config.LoginUrl;
        String Email,Pass;
        Email = email.getEditText().getText().toString();
        Pass = password.getEditText().getText().toString();
        Log.v("Pass : ", Pass);
        Log.v("Email : ", Email);
        if (Validations.isValidEmail(Email) && Validations.isValidPass(Pass)) {
            RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);

            String jsonBody = "{  \n" +
                    "  \"authentication\":{  \n" +
                    "     \"hash\":\"sladfjlkasldflsaj\",\n" +
                    "     \"userType\":\"STUDENT\",\n" +
                    "     \"userId\":\"\",\n" +
                    "     \"studentId\":\"\",\n" +
                    "     \"parentId\":\"\"\n" +
                    "  },\n" +
                    "  \"requestData\":{  \n" +
                    "     \"loginDTO\":{  \n" +
                    "        \"email\":\"" + Email + "\",\n" +
                    "        \"password\":\"" + Pass + "\",\n" +
                    "        \"captcha\":\"" + 123456 + "\"\n" +
                    "     }\n" +
                    "  }\n" +
                    "}";
            Log.v("jsonBody : ", jsonBody);
            final String mRequestBody = jsonBody;
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.v("response : ", response);
                            JSONObject obj = null;
                            try {
                                obj = new JSONObject(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                String status = obj.getString("status");
                                String message = obj.getString("message");
                                if ("0".equalsIgnoreCase(status)) {
                                    Toast.makeText(LoginActivity.this, message,Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent intent = new Intent(LoginActivity.this,StudentRegistrationS1.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                                    Log.v("message", message);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                String responseLoginDTO = obj.getString("responseLoginDTO");
                                JSONObject obj1 = new JSONObject(responseLoginDTO);
                                String userhash = obj1.getString("userLoginHash");
                                String usertype = obj1.getString("userType");
                                String loginstage = obj1.getString("lognStage");

                                Log.v("responseLoginDTO", responseLoginDTO);
                                Log.v("usertype", usertype);
                                Log.v("loginstage", loginstage);
                                if (usertype.equals("4")) {
                                    if (loginstage.equals("10")) {
                                        Intent intent = new Intent(LoginActivity.this, StudentRegistrationS1.class);
                                        startActivity(intent);
                                        finish();
                                    } else if (loginstage.equals("11")) {
                                        Intent intent = new Intent(LoginActivity.this, StudentRegistrationS2.class);
                                        startActivity(intent);
                                        finish();
                                    } else if (loginstage.equals("12")) {
                                        Intent intent = new Intent(LoginActivity.this, StudentRegistrationS3.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //  JSONObject obj2 = new JSONObject(responseData);
                            // result = obj2.getJSONArray("cities");
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

            if (Validations.isValidEmail(Email) && Validations.isValidPass(Pass)) {

                SafetyNet.getClient(this).verifyWithRecaptcha(SITE_KEY)
                        .addOnSuccessListener(this,
                                new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
                                    @Override
                                    public void onSuccess(SafetyNetApi.RecaptchaTokenResponse response) {
                                        // Indicates communication with reCAPTCHA service was
                                        // successful.
                                        userResponseToken = response.getTokenResult();
                                        if (!userResponseToken.isEmpty()) {
                                            // Validate the user response token using the
                                            // reCAPTCHA siteverify API.
                                            // new SendPostRequest().execute();
                                            sendRequest();
                                        }
                                    }
                                })
                        .addOnFailureListener(this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                if (e instanceof ApiException) {
                                    // An error occurred when communicating with the
                                    // reCAPTCHA service. Refer to the status code to
                                    // handle the error appropriately.
                                    ApiException apiException = (ApiException) e;
                                    int statusCode = apiException.getStatusCode();
                                    Log.d(TAG, "Error: " + CommonStatusCodes
                                            .getStatusCodeString(statusCode));
                                } else {
                                    // A different, unknown type of error occurred.
                                    Log.d(TAG, "Error: " + e.getMessage());
                                }
                            }
                        });
            }
        }
        else {
            Toast.makeText(LoginActivity.this, "Please Enter Email and Password", Toast.LENGTH_SHORT).show();
        }}

    public void sendRequest() {

        String url = "https://www.google.com/recaptcha/api/siteverify";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            //Toast.makeText(MainActivity.this, obj.getString("success"), Toast.LENGTH_LONG).show();
                            if (obj.getString("success").equals("true")) {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("secret", SITE_SECRET_KEY);
                params.put("response", userResponseToken);
                return params;
            }
        };
        AppController.getInstance(this).addToRequestQueue(stringRequest);

    }

}