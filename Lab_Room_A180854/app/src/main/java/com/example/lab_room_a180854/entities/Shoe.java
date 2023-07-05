package com.example.lab_room_a180854.entities;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "shoes")
public class Shoe {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "shoe_id")
    int shoeID;

    @NonNull
    @ColumnInfo(name = "shoe_name")
    String shoeName;

    @NonNull
    @ColumnInfo(name = "shoe_price")
    float shoePrice;

    public Shoe(int shoeID) {
        this.shoeID = shoeID;
    }

    public Shoe(@NonNull String shoeName, float shoePrice) {
        this.shoeName = shoeName;
        this.shoePrice = shoePrice;
    }

    public Shoe(int shoeID, @NonNull String shoeName, float shoePrice) {
        this.shoeID = shoeID;
        this.shoeName = shoeName;
        this.shoePrice = shoePrice;
    }

    public Shoe() {

    }


    public int getShoeID() {
        return shoeID;
    }

    public void setShoeID(int shoeID) {
        this.shoeID = shoeID;
    }

    @NonNull
    public String getShoeName() {
        return shoeName;
    }

    public void setShoeName(@NonNull String shoeName) {
        this.shoeName = shoeName;
    }

    public float getShoePrice() {
        return shoePrice;
    }

    public void setShoePrice(float shoePrice) {
        this.shoePrice = shoePrice;
    }
}
