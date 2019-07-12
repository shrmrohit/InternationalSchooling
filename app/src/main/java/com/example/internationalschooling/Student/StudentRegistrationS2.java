package com.example.internationalschooling.Student;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.internationalschooling.Config;
import com.example.internationalschooling.DashBoard;
import com.example.internationalschooling.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import java.io.UnsupportedEncodingException;

public class StudentRegistrationS2 extends AppCompatActivity {
    private Spinner IsdSpinner,IsdSpinner1,GenderSpinner,GenderSpinner1,RelationSpinner;
    private JSONArray result;
    private JSONArray result1,result2,result3;
    String[] countries,gender,gender1,relationspinner;
    ArrayAdapter<String> SpinnerAdapter,SpinnerAdapter1,Spinner;
    ArrayAdapter<String> GenderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration_s2);
        GenderSpinner = (Spinner) findViewById(R.id.genderSpinner);
        GenderSpinner1 = (Spinner) findViewById(R.id.GenderSpinner1);
        IsdSpinner1=(Spinner)findViewById(R.id.IsdSpinner1);
        RelationSpinner=(Spinner)findViewById(R.id.RelationSpinner);
        IsdSpinner = (Spinner) findViewById(R.id.IsdSpinner);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = Config.CountrySpinnerdata;
        String jsonBody = "{\n" +
                "\t\"authentication\":{\n" +
                "\t\"hash\":\"sdfsdfsfdsdff\"\n" +
                "\t},\n" +
                "\t\"requestData\":{\n" +
                "\t\t\"requestKey\":\"COUNTRIES-LIST\",\n" +
                "\t\t\"requestValue\":\"0\"\n" +
                "\t}\n" +
                "}";

        final String mRequestBody = jsonBody;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject obj = null;
                        try {
                            ArrayList<String> cs = new ArrayList<String>();
                            obj = new JSONObject(response);
                            String mastersData = obj.getString("mastersData");
                            JSONObject obj1 = new JSONObject(mastersData);
                            String country = obj1.getString("countries");
                            JSONArray result;
                            result = obj1.getJSONArray("countries");
                            for (int i = 0; i < result.length(); i++) {
                                try {
                                    //Getting json object
                                    JSONObject json = result.getJSONObject(i);
                                    //Adding the name of the student to array list
                                    cs.add(json.getString("value") + "  " + json.getString("extra1"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            IsdSpinner.setAdapter(new ArrayAdapter<String>(StudentRegistrationS2.this,
                                    android.R.layout.simple_spinner_dropdown_item, cs));
                            IsdSpinner1.setAdapter(new ArrayAdapter<String>(StudentRegistrationS2.this,
                                    android.R.layout.simple_spinner_dropdown_item, cs));
                        } catch (JSONException e) {
                            e.printStackTrace();
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
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                    return null;
                }
            }

        };
        requestQueue.add(stringRequest);
//************************************************************************************
        //**************************************************************************************************
        //*************************************************************************************
        //************************************************************************************
        //**************************************************************************************
//**************************************************************************************************
        //**************************************************************************************
        //*************************************************************************************
        //**
        relationspinner = getResources().getStringArray(R.array.RelationSpinner);
        Spinner = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, relationspinner)
        {
            public View getView ( int position, View convertView,
                                  ViewGroup parent){
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTextColor(Color.parseColor("#0B1996"));
                return v;
            }
            public View getDropDownView ( int position, View convertView,
                                          ViewGroup parent){
                View v = super.getDropDownView(position, convertView,
                        parent);
                v.setBackgroundColor(Color.parseColor("#0B1996"));
                ((TextView) v).setTextColor(Color.parseColor("#ffffff"));
                return v;
            }
        } ;
       Spinner.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        // Set Adapter in the spinner
        RelationSpinner.setAdapter(Spinner);
//************************************************************************************
        //**************************************************************************************************
        //*************************************************************************************
        //************************************************************************************
        //**************************************************************************************
//**************************************************************************************************
        //**************************************************************************************
        //*************************************************************************************
        //**

        gender = getResources().getStringArray(R.array.gender);
        SpinnerAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, gender) {
            public View getView(int position, View convertView,
                                ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTextColor(Color.parseColor("#0B1996"));
                return v;
            }
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View v = super.getDropDownView(position, convertView,
                        parent);
                v.setBackgroundColor(Color.parseColor("#0B1996"));
                ((TextView) v).setTextColor(Color.parseColor("#ffffff"));
                return v;
            }
        };
        SpinnerAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        // Set Adapter in the spinner
        GenderSpinner.setAdapter(SpinnerAdapter);
//************************************************************************************
        //**************************************************************************************************
        //*************************************************************************************
        //************************************************************************************
        //**************************************************************************************
//**************************************************************************************************
        //**************************************************************************************
        //*************************************************************************************
        //**

        gender1 = getResources().getStringArray(R.array.Gender1);
        SpinnerAdapter1 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, gender1)
        {
            public View getView ( int position, View convertView,
                                  ViewGroup parent){
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTextColor(Color.parseColor("#0B1996"));
                return v;
            }
            public View getDropDownView ( int position, View convertView,
                                          ViewGroup parent){
                View v = super.getDropDownView(position, convertView,
                        parent);
                v.setBackgroundColor(Color.parseColor("#0B1996"));
                ((TextView) v).setTextColor(Color.parseColor("#ffffff"));
                return v;
            }
        } ;
        SpinnerAdapter1.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        // Set Adapter in the spinner
        GenderSpinner1.setAdapter(SpinnerAdapter1);
    }
    //************************************************************************************
    //**************************************************************************************************
    //*************************************************************************************
    //************************************************************************************
    //**************************************************************************************
//**************************************************************************************************
    //**************************************************************************************
    //*************************************************************************************
    //**
    public void NextClick(View view) {
        Intent intent = new Intent(this, DashBoard.class);
        startActivity(intent);
    }
}
