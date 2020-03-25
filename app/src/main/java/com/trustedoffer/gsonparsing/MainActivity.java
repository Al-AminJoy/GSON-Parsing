package com.trustedoffer.gsonparsing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BestBowlingAdapter bestBowlingAdapter;
    public List<MostWicket> dataitem = new ArrayList();
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    public String url = "https://api.myjson.com/bins/p8t1z";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rvbestbowlingid);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        requestQueue = Volley.newRequestQueue(this);
        loadData();
    }
    private void loadData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson=new Gson();
                //For Add Data In Model Class
                BestBowlingModelClass data=gson.fromJson(response,BestBowlingModelClass.class);
                //This Process Also Can Be Done
               // dataitem.addAll(data.getMostWickets());
                dataitem=data.getMostWickets();
                bestBowlingAdapter = new BestBowlingAdapter(getApplicationContext(), dataitem);
                recyclerView.setAdapter(bestBowlingAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest);
    }
}
