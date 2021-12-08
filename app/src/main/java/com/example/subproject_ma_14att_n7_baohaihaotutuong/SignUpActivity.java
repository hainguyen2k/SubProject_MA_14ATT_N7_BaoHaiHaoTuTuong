package com.example.subproject_ma_14att_n7_baohaihaotutuong;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    EditText email, password, username;
    Button btnsignup;
    //    String url = "http://192.168.1.13:8080/register";
    String url = "https://appchat-server.herokuapp.com/register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mappingID();
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                String txt_username = username.getText().toString();
                if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password) || TextUtils.isEmpty(txt_username)) {
                    Toast.makeText(SignUpActivity.this, "TextField is empty", Toast.LENGTH_SHORT).show();
                } else if (txt_password.length() < 6) {
                    Toast.makeText(SignUpActivity.this, "Password at least 6 character", Toast.LENGTH_SHORT).show();
                } else {
                    signup(url);
                }
            }
        });
    }

    //    private void signup(String url){
//        StringRequest stringRequest = new StringRequest(
//                Request.Method.POST,
//                url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Toast.makeText(SignUpActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                        if (error == null || error.networkResponse == null) {
//                            return;
//                        }
//
//                        String body;
//                        //get status code here
//                        final String statusCode = String.valueOf(error.networkResponse.statusCode);
//                        //get response body and parse with appropriate encoding
//                        try {
//                            body = new String(error.networkResponse.data,"UTF-8");
//                        } catch (UnsupportedEncodingException e) {
//                            // exception
//                        }
//                    }
//                })
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                HashMap<String, String>
//                        params = new HashMap<>();
//                params.put("username", "XuanHai");
//                params.put("password", "1234567");
//
//                return super.getParams();
//            }
//        };
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//    }
    private void signup(String url) {
        JSONObject js = new JSONObject();
        try {
            js.put("email", email.getText().toString());
            js.put("password", password.getText().toString());
            js.put("username", username.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Make request for JSONObject
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, url, js,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString() + " i am queen");

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }) {

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

        };

        // Adding request to request queue
        Volley.newRequestQueue(this).add(jsonObjReq);
        startActivity(new Intent(SignUpActivity.this, MainActivity.class));

    }

    private void mappingID() {

        email = findViewById(R.id.edtemail);
        password = findViewById(R.id.edtpassword);
        username = findViewById(R.id.edtusername);
        btnsignup = findViewById(R.id.btnSignup);
    }
}