package com.example.internationalschooling.Student;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
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
import com.example.internationalschooling.Config;
import com.example.internationalschooling.LoginActivity;
import com.example.internationalschooling.R;
import com.example.internationalschooling.Validations;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class StudentRegistrationS1 extends AppCompatActivity {
    private Spinner CountrySpinner,GenderSpinner,StateSpinner,citySpinner,learningCenter,IsdSpinner,IsdSpinner1;
    List<String> list;
    ArrayAdapter<String> SpinnerAdapter;
    ArrayAdapter<String> GenderAdapter;
    ArrayAdapter<String> StateAdapter;
    private EditText editTextbday;
    private ProgressBar progressBar;
    String[] countries,gender;
    private JSONArray result;
    private JSONArray result1,result2,result3;
    private TextInputLayout email,firstname,middlename,lastname,phonenumber,alternatephonenumber;
    private CheckBox checkBox;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration_s1);
        CountrySpinner = (Spinner) findViewById(R.id.CountrySpinner);
        GenderSpinner = (Spinner) findViewById(R.id.GenderSpinner);
        editTextbday = (EditText) findViewById(R.id.Birthday);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        StateSpinner = (Spinner) findViewById(R.id.StateSpinner);
        citySpinner = (Spinner) findViewById(R.id.CitySpinner);
        learningCenter = (Spinner) findViewById(R.id.LearningCenterSpinner);
        IsdSpinner = (Spinner) findViewById(R.id.IsdSpinner);
        IsdSpinner1 = (Spinner) findViewById(R.id.IsdSpinner1);
        email = (TextInputLayout) findViewById(R.id.email);
        firstname = (TextInputLayout) findViewById(R.id.firstname);
        middlename = (TextInputLayout) findViewById(R.id.middlename);
        lastname = (TextInputLayout) findViewById(R.id.lastname);
        phonenumber = (TextInputLayout) findViewById(R.id.phoneno);
        alternatephonenumber = (TextInputLayout) findViewById(R.id.alternatephoneno);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        fetchCountryCenter();
        fetchIsdSpinner();
    }
    public void NextClick(View view) {

        String Url = Config.RegisterUrlStage2;
        String Email,FirstName,MiddleName,LastName,Gender,PhoneNumber,AlterNatePhoneNumber;
        String key = CountrySpinner.getSelectedItem().toString();
        Email = email.getEditText().getText().toString();
        FirstName = firstname.getEditText().getText().toString();
        MiddleName = middlename.getEditText().getText().toString();
        LastName = lastname.getEditText().getText().toString();
        PhoneNumber = phonenumber.getEditText().getText().toString();
        AlterNatePhoneNumber = alternatephonenumber.getEditText().getText().toString();

        Log.v("Email : ", Email);
        Log.v("firstname : ", FirstName);
        Log.v("middlename : ", MiddleName);
        Log.v("lastname : ", LastName);
        Log.v("phonenumber : ", PhoneNumber);
        Log.v("alternatephonenumber : ", AlterNatePhoneNumber);
        Log.v("country :",key);

         if(FirstName != null && !FirstName.isEmpty() && (MiddleName != null && !MiddleName.isEmpty()&&
           (LastName !=null && !LastName.isEmpty()&&(PhoneNumber !=null &&! PhoneNumber.isEmpty()
           &&(AlterNatePhoneNumber !=null &&!AlterNatePhoneNumber.isEmpty()&&(Validations.isValidEmail(Email)
        &&((key != null && !key.isEmpty())))))))) {
             Log.v("sucess : ", FirstName);
             Log.v("sucess : ", MiddleName);
             Log.v("sucess : ", LastName);
             Log.v("sucess : ", PhoneNumber);
             Log.v("sucess : ", AlterNatePhoneNumber);
             Log.v("sucess : ", Email);
             Log.v("sucess:", key);

             RequestQueue requestQueue = Volley.newRequestQueue(StudentRegistrationS1.this);

             String jsonBody = "{\n" +
                     "\t\"authentication\":{\n" +
                     "\t\t\"hash\":\"sladfjlkasldflsaj\",\n" +
                     "\t\t\"userType\":\"STUDENT\",\n" +
                     "\t\t\"userId\":\"135\",\n" +
                     "\t\t\"studentId\":\"\",\n" +
                     "\t\t\"parentId\":\"\"\n" +
                     "\t},\n" +
                     "\t\"requestData\":{\n" +
                     "\t\t\"signupStudentDTO\":{\n" +
                     "\t\t\t\"dob\":\"1978-09-30\",\n" +
                     "\t\t\t\"firstName\":\"" + FirstName + "\",\n" +
                     "\t\t\t\"middleName\":\"" + MiddleName + "\",\n" +
                     "\t\t\t\"lastName\":\"" + LastName + "\",\n" +
                     "\t\t\t\"studyCenter\":\"2\",\n" +
                     "\t\t\t\"gender\":\"Male\",\n" +
                     "\t\t\t\"countryId\":\"" + key + "\",\n" +
                     "\t\t\t\"stateId\":\"2\",\n" +
                     "\t\t\t\"cityId\":\"2\",\n" +
                     "\t\t\t\"communicationEmail\":\"" + Email + "\",\n" +
                     "\t\t\t\"countryCode\":\"9818934238\",\n" +
                     "\t\t\t\"contactNumber\":\"" + PhoneNumber + "\",\n" +
                     "\t\t\t\"countryCodeAlternate\":\"9818934240\",\n" +
                     "\t\t\t\"contactNumberAlternate\":\"" + AlterNatePhoneNumber + "\"\n" +
                     "\t\t}\n" +
                     "\t}\n" +
                     "}";
         }
//**************************************************************************************************
        //**************************************************************************************
        //**************************************************************************************
        //**************************************************************************************
//**************************************************************************************************
        //**************************************************************************************
        //*************************************************************************************
        //**
        //******************* fetch Country by postRequest
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = Config.CountrySpinnerdata;
        String jsonBody ="\n" +
                "{\n" +
                "\"authentication\":{\n" +
                "\"hash\":\"sladfjlkasldflsaj\",\n" +
                "\"userType\":\"ROLE_STUDENT\",\n" +
                "\"userId\":\"333\",\n" +
                "\"studentId\":\"117\",\n" +
                "\"parentId\":\"59\"\n" +
                "\n" +
                "},\n" +
                "\"requestData\":{\n" +
                "\"requestKey\":\"COUNTRIES-LIST\",\n" +
                "\"requestValue\":\"1info@internationalschooling.org\"\n" +
                "}\n" +
                "}";
        final String mRequestBody = jsonBody;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
        public void onResponse(String response) {
        JSONObject obj = null;
        Log.v("aaaaaaa",response);
        try {
        ArrayList<String> cs = new ArrayList<String>();
        obj = new JSONObject(response);
        String mastersData = obj.getString("mastersData");
        JSONObject obj1 = new JSONObject(mastersData);
         String country = obj1.getString("countries");
         result = obj1.getJSONArray("countries");
           for(int i=0;i<result.length();i++){
            try {
             //Getting json object
            JSONObject json = result.getJSONObject(i);
            //Adding the name of the student to array list
            cs.add(json.getString("value"));
            } catch (JSONException e) {
            e.printStackTrace();
            }
            }
            //set array to adapter and give colour to view
           CountrySpinner.setAdapter(new ArrayAdapter<String>(StudentRegistrationS1.this,
           android.R.layout.simple_spinner_dropdown_item, cs){
           public View getView(int position, View convertView,ViewGroup parent) {
           View v = super.getView(position, convertView, parent);
           ((TextView) v).setTextColor(Color.parseColor("#0B1996"));
           return v;
           }
          public View getDropDownView(int position, View convertView,ViewGroup parent) {
          View v = super.getDropDownView(position, convertView,parent);
          v.setBackgroundColor(Color.parseColor("#0B1996"));
          ((TextView) v).setTextColor(Color.parseColor("#ffffff"));
           return v;
            }
           });
             } catch (JSONException e) {
           e.printStackTrace();
            }
           CountrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String name =getName(position);
            String key =getkey(position);
            //fetch value of states
             fetchstate(name,key);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
             });
            }
            //**************************************************************************************************
            //**************************************************************************************//**************************************************************************************
//**************************************************************************************************
            //*************************************************************************************
            //************************************************************************************
//**************************************************************************************************
            //**************************************************************************************
            //*************************************************************************************
            //**
            // METHORD TO FETCH State DATA
            private void fetchstate(final String name, String key) {
            RequestQueue requestQueue = Volley.newRequestQueue(StudentRegistrationS1.this);
            String CountryUrl = Config.CountrySpinnerdata;
                // JSONObject jsonBody1 = new JSONObject();
                //  jsonBody.put("authentication", "firstvalue");
                //  jsonBody.put("requestData", "secondobject");
            String jsonBody ="{\n" +
                        "\"authentication\":{\n" +
                        "\t\t\t\"hash\":\"sdfsdfsfdsdff\"\n" +
                        "},\n" +
                        "\"requestData\":{\n" +
                        "\t\"requestKey\":\"STATES-LIST\",\n" +
                        "\t\"requestValue\":\""+key+"\"\n" +
                        "\t\n" +
                        "}\n" +
                        "}";
             final String mRequestBody = jsonBody;
             StringRequest stringRequest = new StringRequest(Request.Method.POST, CountryUrl,
                  new Response.Listener<String>() {
             @Override
             public void onResponse(String response) {
             JSONObject obj = null;
            try {
             ArrayList<String> cs1 = new ArrayList<String>();
             obj = new JSONObject(response);
             String mastersData = obj.getString("mastersData");
             JSONObject obj1 = new JSONObject(mastersData);
              result1 = obj1.getJSONArray("states");
              for(int i=0;i<result1.length();i++) {
              try {
              //Getting json object
              JSONObject json = result1.getJSONObject(i);
               //Adding the name of the student to array list
               cs1.add(json.getString("value"));
               } catch (JSONException e) {
               e.printStackTrace();
               }
               }
               StateSpinner.setAdapter(new ArrayAdapter<String>(StudentRegistrationS1.this,
                                            android.R.layout.simple_spinner_dropdown_item, cs1){
               public View getView(int position, View convertView,ViewGroup parent) {
               View v = super.getView(position, convertView, parent);
               ((TextView) v).setTextColor(Color.parseColor("#0B1996"));
               return v;
               }
               public View getDropDownView(int position, View convertView,ViewGroup parent) {
               View v = super.getDropDownView(position, convertView,parent);
              v.setBackgroundColor(Color.parseColor("#0B1996"));
              ((TextView) v).setTextColor(Color.parseColor("#ffffff"));
              return v;
                }
                });
              } catch (JSONException e) {
              e.printStackTrace();
                }
               StateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              String namestate =getStateName(position);
              String keystate =getStateKey(position);
              fetchcity(namestate,keystate);
               }
               public void onNothingSelected(AdapterView<?> parent) {
               }
              });
               }
    //**************************************************************************************************
    //**************************************************************************************
    //**************************************************************************************
    //**************************************************************************************
    //**************************************************************************************************
    //*************************************************************************************
    //**************************************************************************************************
    //**************************************************************************************
    //*************************************************************************************
   //**
    //  METHORD TO FETCH City DATA
  private void fetchcity(String namestate, String keystate) {
   RequestQueue requestQueue = Volley.newRequestQueue(StudentRegistrationS1.this);
   String CountryUrl = Config.CountrySpinnerdata;
    // JSONObject jsonBody1 = new JSONObject();
    //  jsonBody.put("authentication", "firstvalue");
    //  jsonBody.put("requestData", "secondobject");
     String jsonBody ="{\n" +
     "\t\"authentication\":{\n" +
     "\t\"hash\":\"sdfsdfsfdsdff\"\n" +
     "\t},\n" +
      "\t\"requestData\":{\n" +
      "\t\t\"requestKey\":\"CITIES-LIST\",\n" +
      "\t\t\"requestValue\":\""+keystate+"\"\n" +
      "\t}\n" +
      "}";

     final String mRequestBody = jsonBody;
     StringRequest stringRequest = new StringRequest(Request.Method.POST, CountryUrl,
     new Response.Listener<String>() {
     @Override
     public void onResponse(String response) {
     JSONObject obj = null;
     try {
      ArrayList<String> cs2 = new ArrayList<String>();
      obj = new JSONObject(response);
      String mastersData = obj.getString("mastersData");
      JSONObject obj2 = new JSONObject(mastersData);
      result2 = obj2.getJSONArray("cities");
      Log.v("TEST",result2.toString());
      for(int i=0;i<result2.length();i++) {
        try {
        //Getting json object
        JSONObject json = result2.getJSONObject(i);
        //Adding the name of the student to array list
         cs2.add(json.getString("value"));
         }
        catch (JSONException e) {
        e.printStackTrace();
        }
        }
        citySpinner.setAdapter(new ArrayAdapter<String>(StudentRegistrationS1.this,
        android.R.layout.simple_spinner_dropdown_item, cs2){
        public View getView(int position, View convertView,ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        ((TextView) v).setTextColor(Color.parseColor("#0B1996"));
        return v;
          }
        public View getDropDownView(int position, View convertView,
        ViewGroup parent) {
        View v = super.getDropDownView(position, convertView,parent);
         v.setBackgroundColor(Color.parseColor("#0B1996"));
         ((TextView) v).setTextColor(Color.parseColor("#ffffff"));
         return v;
          }
          });
          //set spinner adapter
          } catch (JSONException e) {
           e.printStackTrace();
            }
          //onclick
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
          }};
          requestQueue.add(stringRequest);
           }
    //******************************************************************************************
    //******************************************************************************************
    //**************************************************************************************
    //**************************************************************************************
    //**************************************************************************************
    //*************************************************************************************
    //************************************************************************************
    //**************************************************************************************
    //*************************************************************************************
    //**
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
    //**************************************************************************************************
    //**************************************************************************************
    //**************************************************************************************
    //**************************************************************************************************
    //*************************************************************************************
    //**************************************************************************************
    //**************************************************************************************************
    //**************************************************************************************
    //*************************************************************************************
    //**
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
        //************************************************************************************
        // set gender Spinner
        gender= getResources().getStringArray(R.array.gender);
        SpinnerAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,gender ){
         public View getView(int position, View convertView,ViewGroup parent) {
         View v = super.getView(position, convertView, parent);
        ((TextView) v).setTextColor(Color.parseColor("#0B1996"));
         return v;
         }
          public View getDropDownView(int position, View convertView,ViewGroup parent) {
          View v = super.getDropDownView(position, convertView,parent);
           v.setBackgroundColor(Color.parseColor("#0B1996"));
           ((TextView) v).setTextColor(Color.parseColor("#ffffff"));
           return v;
            }
        };
        SpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Set Adapter in the spinner
        GenderSpinner.setAdapter(SpinnerAdapter);
        //************************************************************************************
        //**************************************************************************************************
        //************************************************************************************
        //**************************************************************************************
        //**************************************************************************************************
        //**************************************************************************************
        //*************************************************************************************
        //**
    }
    private void fetchCountryCenter() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String Url = Config.CountrySpinnerdata;
        String jsonBody ="{\n" +
                "\"authentication\":{\n" +
                "\t\t\t\"hash\":\"sdfsdfsfdsdff\"\n" +
                "},\n" +
                "\"requestData\":{\n" +
                "\t\"requestKey\":\"SCHOOLS-LIST\",\n" +
                "\t\"requestValue\":\"1\"\n" +
                "\t\n" +
                "}\n" +
                "}";

        final String mRequestBody = jsonBody;
        StringRequest stringRequest = new StringRequest(Request.Method.POST,Url,
        new Response.Listener<String>() {
         @Override
          public void onResponse(String response) {
          JSONObject  obj = null;
          try {
          obj = new JSONObject(response);
          } catch (JSONException e) {
           e.printStackTrace();
           }
           String master = null;
           try {
           master = obj.getString("mastersData");
           } catch (JSONException e) {
           e.printStackTrace();
           }
           JSONObject obj1 = null;
           try {
           obj1 = new JSONObject(master);
           } catch (JSONException e) {
           e.printStackTrace();
           }
           try {
           String Schools=obj1.getString("schools");
           Log.v("Schools is" ,Schools);
           } catch (JSONException e) {
           e.printStackTrace();
           }
           ArrayList<String> cs2 = new ArrayList<String>();
            try {
            result3 = obj1.getJSONArray("schools");
             } catch (JSONException e) {
             e.printStackTrace();
            }
            for(int i=0;i<result3.length();i++) {
             try {
              //Getting json object
             JSONObject json = result3.getJSONObject(i);
            //Adding the name of the student to array list
             cs2.add(json.getString("value"));
             } catch (JSONException e) {
             e.printStackTrace();
             }}
             learningCenter.setAdapter(new ArrayAdapter<String>(StudentRegistrationS1.this,
             android.R.layout.simple_spinner_dropdown_item, cs2){
              public View getView(int position, View convertView,ViewGroup parent) {
              View v = super.getView(position, convertView, parent);
              ((TextView) v).setTextColor(Color.parseColor("#0B1996"));
              return v;
               }
              public View getDropDownView(int position, View convertView,ViewGroup parent) {
              View v = super.getDropDownView(position, convertView,parent);
              v.setBackgroundColor(Color.parseColor("#0B1996"));
              ((TextView) v).setTextColor(Color.parseColor("#ffffff"));
              return v;
               }
               });
              //set spinner adapter
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
                }}
        };
        requestQueue.add(stringRequest);
    }
    //**************************************************************************************************
    //**************************************************************************************
    //**************************************************************************************
    //**************************************************************************************
//**************************************************************************************************
    //**************************************************************************************
    //*************************************************************************************
    //**
    //  METHORD TO fetchIsdSpinner DATA
    private void fetchIsdSpinner() {

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
                            cs.add(json.getString("value")+"  "+json.getString("extra1"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    IsdSpinner.setAdapter(new ArrayAdapter<String>(StudentRegistrationS1.this,
                            android.R.layout.simple_spinner_dropdown_item, cs));
                    IsdSpinner1.setAdapter(new ArrayAdapter<String>(StudentRegistrationS1.this,
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
    }
          public void datePick(View view) {
              final Calendar myCalendar = Calendar.getInstance();
              final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                  @Override
                  public void onDateSet(DatePicker view, int year, int monthOfYear,
                                        int dayOfMonth) {
                      // TODO Auto-generated method stub
                      myCalendar.set(Calendar.YEAR, year);
                      myCalendar.set(Calendar.MONTH, monthOfYear);
                      myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                      String myFormat = "MM/dd/yy"; //In which you need put here
                      SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                      editTextbday.setText(sdf.format(myCalendar.getTime()));
                  }
              };
              editTextbday.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      new DatePickerDialog(StudentRegistrationS1.this, date, myCalendar
                              .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                              myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                  }
              });
          }
    public void ClickNext(View view) {}
    private String getName(int position){
        String name="";
        try {
            //Getting object of given index
            JSONObject json = result.getJSONObject(position);

            //Fetching name from that object
            name = json.getString("value");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return name;
    }
    private String getkey(int position){
        String key="";
        try {
            //Getting object of given index
            JSONObject json = result.getJSONObject(position);
            //Fetching name from that object
            key = json.getString("key");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return key;
    }
    private String getStateName(int position){
        String name="";
        try {
            //Getting object of given index
            JSONObject json = result1.getJSONObject(position);
            //Fetching name from that object
            name = json.getString("value");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return name;
    }
    private String getStateKey(int position){
        String key="";
        try {
            //Getting object of given index
            JSONObject json = result1.getJSONObject(position);
            //Fetching name from that object
            key = json.getString("key");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return key;
    }
}