package com.example.fooddeliverymobileclient.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fooddeliverymobileclient.Adapter.MenuAdapter;
import com.example.fooddeliverymobileclient.Adapter.RecyclerItemClickListener;
import com.example.fooddeliverymobileclient.Domain.Client;
import com.example.fooddeliverymobileclient.Domain.Menu;
import com.example.fooddeliverymobileclient.Domain.Place;
import com.example.fooddeliverymobileclient.Domain.SubMenu;
import com.example.fooddeliverymobileclient.Domain.User;
import com.example.fooddeliverymobileclient.MainActivity;
import com.example.fooddeliverymobileclient.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private TextView login;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.login_email);
        password=findViewById(R.id.login_password);
        login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(email.getText().toString(),password.getText().toString());
                 RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                String url = "http://10.0.2.2:8181/rest/auth/login";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        response -> {
                    try{
                        JSONObject object1 = new JSONObject(response);
                        if(response.toString().equals("doesn't exist")){
                            Toast.makeText(LoginActivity.this, "Wrong Credentials",Toast.LENGTH_SHORT).show();
                        }else{
                            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                            SharedPreferences.Editor myEdit = sharedPreferences.edit();
                            myEdit.putString("id",String.valueOf(object1.getLong("id")));
                            myEdit.commit();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }
                    } catch (JSONException e) {
                    e.printStackTrace();
                }
                        }, error -> {
                    Log.e("err", error.getMessage());
                }){

                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        Gson gson = new Gson();
                        String json = gson.toJson(user);
                        Log.e("body",json);
                        return json.getBytes();
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String,String> headers =new HashMap<String, String>();;
                        headers.put("Content-Type", "application/json;charset=utf-8");
                        return headers != null ? headers : super.getHeaders();
                    }
                };
                queue.add(stringRequest);
            }
        });
    }
}