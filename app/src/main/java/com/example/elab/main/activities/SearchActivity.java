package com.example.elab.main.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.elab.R;
import com.example.elab.activities.utils.DetailActivity;
import com.example.elab.activities.utils.UserAdapter;
import com.example.elab.activities.utils.UserViewModel;
import com.example.elab.database.User;
import com.example.elab.activities.utils.VolleyActivity;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    UserViewModel users;
    public List<User> resultUsers;
    UserAdapter adapter;
    public String src;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        users = ViewModelProviders.of(this).get(UserViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        adapter = new UserAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        Intent timerToast = new Intent(getApplicationContext(), service.class);
//        getApplicationContext().startService(timerToast);

        goToWeb(null);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        goToWeb(null);
        doAction(null);

    }

    public void getAll(List<User> users) {
/*
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup table = findViewById(R.id.catalogueList);
        table.removeAllViews();
        for (Product actual:users) {z<
            Log.d("Hello","PRODUCTS NAME HERE! " + actual.user_tag);
            View row = inflater.inflate(R.layout.row_layout,table, false );
            TextView vw = row.findViewById(R.id.productName);
            vw.setText(actual.user_tag);
            table.addView(row);
        }
 */
    }

    public void doAction(View view) {
        src = "%" + ((EditText) findViewById(R.id.searchText)).getText() + "%";
        Log.d("SEARCH STRING!!!", "ENTERED: " + src);
        users.searchUsers(getApplicationContext(), src).observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> products) {
                //getAll(products);
                Log.d("SET resultProducts", "FOUND: " + products);
                resultUsers = products;
                adapter.setProducts(products);
                if(adapter.getItemCount() <= 0){
                    Toast.makeText(SearchActivity.this, "No hay usuarios, actualiza!", Toast.LENGTH_LONG).show();
                }
//                Log.d("Changed HERE!!!", "ENTERED: " + products);
            }
        });
        /*
        // version 2 to obtain products from database!
        DatabaseTask task = new DatabaseTask(getApplicationContext(), this);
        Product p = new Product();
        p.user_tag = "%"+((EditText)findViewById(R.id.searchText)).getText()+"%";
        task.execute(p);
        /*
        Log.d("CUSTOM","CLICK ON  ME!");
        TextView v = findViewById(R.id.editText);
        TextView vt = findViewById(R.id.viewTitleText);
        vt.setText(v.getText());
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(MESSAGE,""+v.getText());
        Log.d("TEXT " ,"" + v.getText());
        startActivity(intent);*/
    }

    public void goToWeb(View view) {
        Toast.makeText(SearchActivity.this, "Base de datos actualizada...", Toast.LENGTH_LONG).show();
        Intent goToNextActivity = new Intent(getApplicationContext(), VolleyActivity.class);
        startActivity(goToNextActivity);
    }

    public void goToNextScreen(final int adapterPosition) {
        Intent i = new Intent(this, DetailActivity.class);
        //Log.d("RESULT PRODUCTS!!!", "RESULT OF SEARCH: " + resultProducts);
        //Log.d("NAME PRODUCT", "RESULT OF SELECT: " + resultProducts.get(adapterPosition).user_tag);
        //Log.d("DESCRIPTION PRODUCT", "RESULT OF SELECT: " + resultProducts.get(adapterPosition).user_name);

        i.putExtra("user_id", resultUsers.get(adapterPosition).user_id);
        i.putExtra("profile_image", resultUsers.get(adapterPosition).profile_image);
        i.putExtra("user_tag", resultUsers.get(adapterPosition).user_tag);
        i.putExtra("user_name", resultUsers.get(adapterPosition).user_name);
        i.putExtra("last_name", resultUsers.get(adapterPosition).last_name);
        i.putExtra("status", resultUsers.get(adapterPosition).status);
        i.putExtra("user_type", resultUsers.get(adapterPosition).user_type);
        i.putExtra("bachelor_degree", resultUsers.get(adapterPosition).bachelor_degree);
        i.putExtra("ranking", ""+resultUsers.get(adapterPosition).ranking);

        Log.d("RATING ON DB", "STARTS: " + resultUsers.get(adapterPosition).ranking);
        startActivity(i);
    }

    public void showMeDBInfo(View view) {
        users.searchUsers(getApplicationContext(), "%%").observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> products) {
                //getAll(products);
                Log.d("DB INFO FOUND", "PRODUCTS: " + products);
            }
        });
    }
    
}
