package com.example.lab_ui_intent_a180854;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnMinus, btnCheckout;
    TextView tvQuantity;
    EditText etName;

    int quantity;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btn_add);
        btnMinus = findViewById(R.id.btn_minus);
        btnCheckout = findViewById(R.id.btn_check_out);
        tvQuantity = findViewById(R.id.tv_quantity);
        etName = findViewById(R.id.et_name);

        quantity = 1;

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity++;
                tvQuantity.setText(""+quantity);
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity--;
                tvQuantity.setText(""+quantity);
            }
        });

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            String name;

            @Override
            public void onClick(View view) {
                name = etName.getText().toString();
                Toast.makeText(MainActivity.this, "Thank you " + name + " for your order: " + quantity + " latte", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(MainActivity.this,OrderDetailActivity.class);
                intent.putExtra("quantity", quantity);
                intent.putExtra("name", name);
                startActivity(intent);

                etName.setText("");
                tvQuantity.setText("1");
                quantity = 1;
            }
        });
    }
}