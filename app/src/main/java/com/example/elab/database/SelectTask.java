package com.example.elab.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import java.util.List;

public class SelectTask extends AsyncTask<User,Void, List<User>> {
    UserDatabase db;
    DatabaseReceiver receiver;
    public SelectTask(Context ctx , DatabaseReceiver receiver){
        this.receiver = receiver;
        db = Room.databaseBuilder(ctx,
                UserDatabase.class ,
                "catalogue-database").fallbackToDestructiveMigration().build();
    }

    @Override
    public void onPreExecute(){
    }
    @Override
    protected List<User> doInBackground(User... params) {

        //If the database is empty, insert all the products.
        //db.userDao().deleteProduct();
        if(db.userDao().getDatabaseCount() <= 0){
            db.userDao().insertUser(params);
        }else{
            //Else, update everything except the ranking, which is local.
            for(int i = 0; i< params.length; i++){
                db.userDao().updateUser(params[i].user_id, params[i].profile_image, params[i].user_tag, params[i].user_name, params[i].last_name, params[i].status, params[i].user_type, params[i].bachelor_degree);
            }
        }
        return null;
    }
    @Override
    public void onPostExecute(List<User> result){
        // do something on ui!
        // receiver.getAll(result);
    }
}