package com.example.elab.activities.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.elab.R;
import com.example.elab.database.SelectTask;
import com.example.elab.database.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VolleyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("onCreate Method", "From MainActivity ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        getJsonRequest();
    }

    public void getJsonRequest() {
        Log.d("getJsonRequest", "Started");

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://10.0.2.2:8000";
//        String url = "http://192.168.1.72:8000";

        Log.d("URL to connect with svr", "String: "+url);
        final SelectTask databaseTask = new SelectTask(getApplicationContext(), null);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("users");
                            Log.d("Response", "onResponse: "+response);

                            User[] users = new User[jsonArray.length()];

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject empresa = jsonArray.getJSONObject(i);

                                String user_id = empresa.getString("user_id");
                                String profile_image = empresa.getString("profile_image");
                                String user_tag = empresa.getString("user_tag");
                                String user_name = empresa.getString("name");
                                String last_name = empresa.getString("last_name");
                                String status = empresa.getString("status");
                                String user_type = empresa.getString("user_type");
                                String bachelor_degree = empresa.getString("bachelor_degree");

                                User e = new User();
                                e.user_id = user_id;
                                e.profile_image = profile_image;
                                e.user_tag = user_tag;
                                e.user_name = user_name;
                                e.last_name = last_name;
                                e.status = status;
                                e.user_type = user_type;
                                e.bachelor_degree = bachelor_degree;
                                e.ranking = 0;

                                users[i] = e;
                            }

                            databaseTask.execute(users);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(request);
    }
}
