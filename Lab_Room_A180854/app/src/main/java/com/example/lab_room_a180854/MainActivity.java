package com.example.lab_room_a180854;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab_room_a180854.entities.Shoe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnSave, btnUpdate;
    EditText etName, etPrice;
    TextView tvShoeId;
    ListView lvShoe;

    ArrayAdapter<String> shoeListAdapter;
    ArrayList<String> shoesArray;
    ArrayList<Integer> shoesID;

    public static MyShoeDB myShoeDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = findViewById(R.id.btn_save);
        btnUpdate = findViewById(R.id.btn_update);
        etName = findViewById(R.id.et_name);
        etPrice = findViewById(R.id.et_price);
        tvShoeId = findViewById(R.id.tv_shoe_id);
        lvShoe = findViewById(R.id.lv_main);

        shoeListAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1);
        shoesArray = new ArrayList<String>();
        shoesID = new ArrayList<Integer>();

        myShoeDB = Room.databaseBuilder(MainActivity.this,MyShoeDB.class,"shoeDB").build();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextIsEmpty())
                    return;
                saveShoe();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextIsEmpty())
                    return;
                updateShoe();
            }
        });

        lvShoe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tvShoeId.setText(shoesID.get(i).toString());
            }
        });

        lvShoe.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setTitle("Remove Shoe");
                alertDialogBuilder.setMessage("Are you sure you want to remove the shoe?" + shoesID.get(position));
                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        toast(getApplicationContext(), "Action Cancelled");
                    }
                });

                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Shoe shoe = new Shoe(Integer.parseInt(shoesID.get(position).toString()));
                        deleteShoe(shoe);
                    }
                });

                alertDialogBuilder.show();

                return true;
            }
        });

    }

    public void saveShoe() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Shoe shoe = new Shoe(etName.getText().toString(), Float.parseFloat(etPrice.getText().toString()));
                myShoeDB.shoeDao().insertShoe(shoe);
                toast(getApplicationContext(),"Shoe Added");
                getAllShoes();
            }
        }).start();

    }

    public void updateShoe() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Shoe shoe = new Shoe(Integer.parseInt(tvShoeId.getText().toString()),etName.getText().toString(),Float.parseFloat(etPrice.getText().toString()));
                myShoeDB.shoeDao().updateShoe(shoe);

                toast(getApplicationContext(),"Shoe Updated");
                getAllShoes();
            }
        }).start();


    }

    public void deleteShoe(Shoe shoe) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                myShoeDB.shoeDao().deleteShoe(shoe);
                toast(getApplicationContext(),"Beverage Removed");
                getAllShoes();
            }
        }).start();
    }

    public void getAllShoes() {

        shoesID.clear();
        shoesArray.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Shoe> shoes = myShoeDB.shoeDao().getAllShoes();
                String shoeInfo;
                for (Shoe shoe:shoes) {
                    shoeInfo = "ID: " + shoe.getShoeID() +
                            "\nName: " + shoe.getShoeName() +
                            "\nPrice: RM" + shoe.getShoePrice();
                    shoesArray.add(shoeInfo);
                    shoesID.add(shoe.getShoeID());
                }

                showDataInListView();
            }
        }).start();



    }

    public void showDataInListView() {

        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                shoeListAdapter.clear();
                shoeListAdapter.addAll(shoesArray);
                lvShoe.setAdapter(shoeListAdapter);
            }
        });



    }

    private boolean editTextIsEmpty() {
        if (TextUtils.isEmpty(etName.getText().toString())) {
            etName.setError("Cannot be empty");
        }

        if (TextUtils.isEmpty(etPrice.getText().toString())) {
            etPrice.setError("Cannot be empty");
        }

        if (TextUtils.isEmpty(etName.getText().toString()) || TextUtils.isEmpty(etPrice.getText().toString())) {
            return true;
        } else
            return false;


    }

    public void toast (final Context context, final String text) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        getAllShoes();
    }
}