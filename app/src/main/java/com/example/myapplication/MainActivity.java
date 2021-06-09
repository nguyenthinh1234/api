package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ArrayList List;
    ListView lv;
    String url = "https://60c0399db8d36700175548df.mockapi.io/";
    Button btnPost, btnDelete, btnPut;
    EditText edtID, edtName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = (EditText) findViewById(R.id.tvName);
        edtID = (EditText) findViewById(R.id.tvID);
        btnPut = (Button) findViewById(R.id.btnPut);
        btnPost = (Button) findViewById(R.id.btnPost);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostApi(url);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteApi(url);
            }
        });
        btnPut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PutApi(url);
            }
        });
        lv = findViewById(R.id.listV);
        List = new ArrayList<>();
        String getAll = url + "User";
        getData(getAll);
    }
    private void getData(String url) {
        JsonArrayRequest request = new JsonArrayRequest(url+"user", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(MainActivity.this, "True", Toast.LENGTH_SHORT).show();
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = (JSONObject) response.get(i);
                        List.add(obj.getString("id"));
                        List.add(obj.getString("name"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                lv.setAdapter(new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, List));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error make by API server !", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
    private void PutApi(String url) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.PUT, url + "user/:id/" + edtID.getText().toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();

                params.put("name", edtName.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void DeleteApi(String url) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.DELETE, url + "user/:id:" + edtID.getText().toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void PostApi(String url) {
        StringRequest stringRequest = new StringRequest( Request.Method.POST,
                url+"user",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("name", edtName.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}