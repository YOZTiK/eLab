package com.example.elab.activities.utils;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.elab.database.User;
import com.example.elab.database.UserDatabase;

import java.util.List;

public class UserViewModel extends ViewModel {

    private LiveData<List<User>> products;

    public UserViewModel(){
        super();
    }

    public LiveData<List<User>> getProducts(Context ctx){
        UserDatabase db = Room.databaseBuilder(ctx,
                UserDatabase.class ,
                "catalogue-database").build();
        products = db.userDao().getAll();

        return products;
    }


    public LiveData<List<User>> searchUsers(Context ctx, String searchString){
        UserDatabase db = Room.databaseBuilder(ctx,
                UserDatabase.class ,
                "catalogue-database").build();
        products = db.userDao().search(searchString);

        return products;
    }
}
