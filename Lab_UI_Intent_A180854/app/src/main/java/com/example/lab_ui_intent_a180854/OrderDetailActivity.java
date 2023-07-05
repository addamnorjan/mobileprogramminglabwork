package com.example.lab_ui_intent_a180854;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

public class OrderDetailActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton imgBtnCall, imgBtnWeb, imgBtnEmail, imgBtnLocation;
    TextView tvName, tvQuantity;
    String name;
    int quantity;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        imgBtnCall = findViewById(R. id. img_btn_call_order_act);
        imgBtnWeb = findViewById(R. id. img_btn_web_order_act);
        imgBtnEmail = findViewById(R. id. img_btn_email_order_act);
        imgBtnLocation = findViewById(R. id. img_btn_location_order_act);

        tvName = findViewById(R. id. tv_name_order_act);
        tvQuantity = findViewById(R. id. tv_quantity_order_act);

        Intent intent = getIntent();
        quantity = intent.getIntExtra("quantity", 0);
        name = intent.getStringExtra("name");

        tvName.setText(name);
        tvQuantity.setText(""+quantity);

        imgBtnCall.setOnClickListener(this);
        imgBtnWeb.setOnClickListener(this);
        imgBtnEmail.setOnClickListener(this);
        imgBtnLocation.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R. id. img_btn_call_order_act:
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel: 0142688906"));

                try {
                    startActivity(callIntent);
                }
                catch (ActivityNotFoundException e) {
                    Toast.makeText(OrderDetailActivity.this, "Sorry, no app can handle this action and data.", Toast.LENGTH_SHORT).show();
                }

                break;
            case R. id. img_btn_web_order_act:
                Uri webpage = Uri.parse("http://www.google.com");
                Intent webIntent = new Intent(Intent.ACTION_VIEW,webpage);

                try {
                    startActivity(webIntent);
                }
                catch (ActivityNotFoundException e) {
                    Toast.makeText(OrderDetailActivity.this, "Sorry, no app can handle this action and data.", Toast.LENGTH_SHORT).show();
                }

                break;
            case R. id. img_btn_email_order_act:
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your order from MyCafeApp");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message: Information about order.");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"mycafeapp@company.com"});

                try {
                    startActivity(emailIntent);
                }
                catch (ActivityNotFoundException e) {
                    Toast.makeText(OrderDetailActivity.this, "Sorry, no app can handle this action and data.", Toast.LENGTH_SHORT).show();
                }

                break;
            case R. id. img_btn_location_order_act:
                Intent locationIntent = new Intent(Intent.ACTION_VIEW);
                locationIntent.setData(Uri.parse("geo:0,0?q=Jumpa+Cafe+FTSM+UKM+Bangi"));

                try {
                    startActivity(locationIntent);
                }
                catch (ActivityNotFoundException e) {
                    Toast.makeText(OrderDetailActivity.this, "Sorry, no app can handle this action and data.", Toast.LENGTH_SHORT).show();
                }

                break;


            }


        }
    }
