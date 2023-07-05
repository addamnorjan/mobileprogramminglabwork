package com.example.lab_room_a180854.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.lab_room_a180854.entities.Shoe;

import java.util.List;

@Dao
public interface ShoeDao {
    @Insert
    void insertShoe(Shoe shoe);

    @Update
    void updateShoe(Shoe shoe);

    @Delete
    void deleteShoe(Shoe shoe);

    @Query("Select * from shoes")
    List<Shoe> getAllShoes();
}
