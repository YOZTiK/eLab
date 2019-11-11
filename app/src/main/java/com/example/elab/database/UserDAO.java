package com.example.elab.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long[] insertUser(User... users);

    @Update
    public void update(User... users);

    @Query ("UPDATE User SET user_tag=:profile_image, user_name=:user_tag, profile_image=:user_name, profile_image=:last_name, profile_image=:status, profile_image=:user_type, profile_image=:bachelor_degree WHERE user_id=:user_id")
    public void updateUser(String user_id, String profile_image, String user_tag, String user_name, String last_name, String status, String user_type, String bachelor_degree);

    @Delete
    public void deleteProduct(User... users);

    @Query("SELECT * FROM User")
    public LiveData<List<User>> getAll();

    @Query("SELECT * FROM User WHERE user_tag LIKE :searchName")
    public LiveData<List<User>> search(String searchName);

    @Query("UPDATE User SET ranking=:ranking WHERE user_id LIKE :id")
    public void updateRanking(String id, int ranking);

    @Query("SELECT count(*) FROM User")
    public int getDatabaseCount();

}