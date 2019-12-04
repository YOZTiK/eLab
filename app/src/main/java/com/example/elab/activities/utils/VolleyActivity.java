package com.example.elab.activities.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.elab.R;
import com.example.elab.database.SelectTask;
import com.example.elab.database.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VolleyActivity extends AppCompatActivity {

    public Boolean updatedSuccessfully = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("onCreate Method", "From MainActivity ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        //getJsonRequest();
        getDataFromFirebase();
    }

    public void getJsonRequest() {
        Log.d("getJsonRequest", "Started");

        RequestQueue queue = Volley.newRequestQueue(this);

        //String url = "http://10.0.2.2:8000";
        String url = "http://10.25.235.12:8000";

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
                            updatedSuccessfully = false;
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                updatedSuccessfully = false;
                error.printStackTrace();
            }
        });

        queue.add(request);
        super.onBackPressed();
    }

    private void getDataFromFirebase(){
        final SelectTask databaseTask = new SelectTask(getApplicationContext(), null);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference gacmersRef = rootRef.child("gacmers");

        final ArrayList<User> userList = new ArrayList<User>();

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    String bachelor_degree = ds.child("bachelor_degree").getValue(String.class);
                    String last_name = ds.child("last_name").getValue(String.class);
                    String name = ds.child("user_name").getValue(String.class);
                    String profile_image = ds.child("profile_image").getValue(String.class);
                    int ranking = ds.child("ranking").getValue(Integer.class);
                    String status = ds.child("status").getValue(String.class);
                    String user_id = ds.child("user_id").getValue(String.class);
                    String user_tag = ds.child("user_tag").getValue(String.class);
                    String user_type = ds.child("user_type").getValue(String.class);

                    System.out.println("Adding: " + user_id + "\n" + profile_image + "\n" +
                            user_tag + "\n" + name + "\n" + last_name + "\n" + status + "\n" + user_type + "\n" + bachelor_degree + "\n" + ranking);

                    User e = new User();
                    e.user_id = user_id;
                    e.profile_image = profile_image;
                    e.user_tag = user_tag;
                    e.user_name = name;
                    System.out.println("NAME RECEIVED : "+e.user_name);
                    e.last_name = last_name;
                    e.status = status;
                    e.user_type = user_type;
                    e.bachelor_degree = bachelor_degree;
                    e.ranking = ranking;

                    userList.add(e);

                }

                User[] array = userList.toArray(new User[userList.size()]);
                databaseTask.execute(array);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}

        };
        gacmersRef.addListenerForSingleValueEvent(eventListener);
        super.onBackPressed();

    }
}
