package com.example.elab.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Room;

import java.util.List;

public class UpdateTask extends AsyncTask<User,Void, List<User>> {
    UserDatabase db;
    DatabaseReceiver receiver;

    String id;
    int ranking;

    public UpdateTask(Context ctx , DatabaseReceiver receiver, String comId, int comRanking){
        this.receiver = receiver;
        db = Room.databaseBuilder(ctx,
                UserDatabase.class ,
                "catalogue-database").fallbackToDestructiveMigration().build();

        id = comId;
        ranking = comRanking;
    }

    @Override
    public void onPreExecute(){
    }
    @Override
    protected List<User> doInBackground(User... params) {
        Log.d("UPDATING RATING", "id: "+id+" ranking: "+ranking);
        db.userDao().updateRanking(id, ranking);

        return null;
        /*if( params[0].user_tag.equals(""))
            return db.productDao().getAll();
        return db.productDao().search(params[0].user_tag);
         */
    }
    @Override
    public void onPostExecute(List<User> result){
        // do something on ui!
        // receiver.getAll(result);
    }
}
