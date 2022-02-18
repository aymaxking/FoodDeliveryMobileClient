package com.example.fooddeliverymobileclient.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fooddeliverymobileclient.Adapter.MenuAdapter;
import com.example.fooddeliverymobileclient.Adapter.RecyclerItemClickListener;
import com.example.fooddeliverymobileclient.Domain.Client;
import com.example.fooddeliverymobileclient.Domain.Menu;
import com.example.fooddeliverymobileclient.Domain.Place;
import com.example.fooddeliverymobileclient.Domain.SubMenu;
import com.example.fooddeliverymobileclient.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    private TextView signup;
    private EditText email;
    private EditText fullname;
    private EditText number;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup=findViewById(R.id.signup);
        email=findViewById(R.id.username_input);
        fullname=findViewById(R.id.name);
        number=findViewById(R.id.number);
        password=findViewById(R.id.pass);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Client client = new Client(email.getText().toString(),password.getText().toString(),fullname.getText().toString(),number.getText().toString());
                RequestQueue queue = Volley.newRequestQueue(SignupActivity.this);
                String url = "http://10.0.2.2:8181/rest/clients";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Error",error.getMessage());
                            }
                        }){
                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        Gson gson = new Gson();
                        String json = gson.toJson(client);
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