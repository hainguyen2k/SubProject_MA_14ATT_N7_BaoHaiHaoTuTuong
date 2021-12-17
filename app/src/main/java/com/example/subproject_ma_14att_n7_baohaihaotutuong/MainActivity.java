package com.example.subproject_ma_14att_n7_baohaihaotutuong;


import static android.service.controls.ControlsProviderService.TAG;

import android.accounts.AccountManagerFuture;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.core.motion.utils.Utils;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.subproject_ma_14att_n7_baohaihaotutuong.model.User;
import com.example.subproject_ma_14att_n7_baohaihaotutuong.remote.APICall;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
//import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String BASE_URL = "https://appchat-server.herokuapp.com/";
    public static final String MY_PREFRENCE = "myPrefs";
    public static final String TOKEN = "myToken";
    private Retrofit retrofit;
    private Context context;

    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    private int RC_SIGN_IN = 1;
    private GoogleSignInClient mGoogleSignInClient;
    EditText emailSI, passwordSI;
    Button btnLogin;
    //    String url = "http://192.168.1.13:8080/login";
    String url = "https://appchat-server.herokuapp.com/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        emailSI = findViewById(R.id.editEmailSI);
        passwordSI = findViewById(R.id.edtPasswordSI);
        btnLogin = findViewById(R.id.btnLogin);

        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.btnSignup2).setOnClickListener(this);
        findViewById(R.id.btnLogin).setOnClickListener(this);
    }

    private void updateUI(GoogleSignInAccount account) {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_button:
                signInGoogle();
                break;
            case R.id.btnSignup2:
                signUp();
                break;
            case R.id.btnLogin:
                Signin(url);
                break;
        }
    }

//    private void Signin() {
//        build();
//        APICall apiCall = retrofit.create(APICall.class);
//
//        String email = emailSI.getText().toString();
//        String password = passwordSI.getText().toString();
//
//        User user = new User(email, password);
//
//        Call<User> call = apiCall.userLogin(user);
//
//        call.enqueue(new Callback<User>(){
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//
////                if (response.isSuccessful()) {
//                    User data = response.body();
////                    String email = data.getEmail();
//                    String token = data.getToken();
//                    Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
//
//                    SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFRENCE, context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString(TOKEN, token);
//                    editor.putString("email", email);
//                    editor.apply();
//
//                    startActivity(new Intent(MainActivity.this, ListConservation.class));
//                    finish();
////                }else {
////                    Log.d("TAG", response.message());
////                    Toast.makeText(MainActivity.this, "Invalid email or password, please try again", Toast.LENGTH_SHORT).show();
////                }
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                Log.d("TAG", t.getMessage());
//            }
//        });
//    }
//
//    private void build() {
//        retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void signInGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signUp() {
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    //    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
//        try {
//            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
//            updateUI(account);
//            Intent intent = new Intent(MainActivity.this, ListConservation.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//            finish();
//        }catch (ApiException e){
//            Log.w(TAG, "signInResult:faild code="+ e.getStatusCode());
//            updateUI(null);
//        }
//
//    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            startActivity(new Intent(MainActivity.this, ListConservation.class));
            // Signed in successfully, show authenticated UI.


        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("message", e.toString());
        }
    }


    private void openProfile() {
        Intent intent = new Intent(this, ListConservation.class);
        intent.putExtra(KEY_EMAIL, emailSI.getText().toString());
        startActivity(intent);
    }

    private void Signin(String url) {
        JSONObject js = new JSONObject();
        try {
            js.put(KEY_EMAIL, emailSI.getText().toString());
            js.put(KEY_PASSWORD, passwordSI.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Make request for JSONObject
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, url, js,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d(ContentValues.TAG, response.toString() + " i am queen");

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(ContentValues.TAG, "Error: " + error.getMessage());
            }
        }) {

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                String credentials = "email" + ":" + "password";
                String base64EncodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                System.out.println(base64EncodedCredentials);
                headers.put("Authorization", "Basic " + base64EncodedCredentials);
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

        };

        // Adding request to request queue
        Volley.newRequestQueue(this).add(jsonObjReq);
        openProfile();
//        startActivity(new Intent(MainActivity.this, ProfileActivity.class));

    }
}

