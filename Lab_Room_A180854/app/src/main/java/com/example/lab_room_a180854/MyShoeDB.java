package com.example.lab_room_a180854;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.lab_room_a180854.dao.ShoeDao;
import com.example.lab_room_a180854.entities.Shoe;

@Database(entities = {Shoe.class}, version = 1)
public abstract class MyShoeDB extends RoomDatabase {

    public abstract ShoeDao shoeDao();

}
