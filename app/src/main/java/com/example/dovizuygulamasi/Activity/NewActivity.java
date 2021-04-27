package com.example.dovizuygulamasi.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.example.dovizuygulamasi.R;

public class NewActivity extends AppCompatActivity {

    private TextView textName, textPrice;
    private EditText textDescription;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);


        textName = (TextView) findViewById(R.id.txtName);
        textPrice = (TextView) findViewById(R.id.txtPrice);
        textDescription = (EditText) findViewById(R.id.txtDescription);
        img = (ImageView) findViewById(R.id.img);

        Intent intent= getIntent();

        SvgLoader.pluck()
                .with(this)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(intent.getStringExtra("iconUrl"), img);

        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        String desc = intent.getStringExtra("description");
        String symbol = intent.getStringExtra("symbol");
        textDescription.setText(desc);
        textDescription.setEnabled(false);
        textName.setText(name);
        textPrice.setText(price);
        getSupportActionBar().setTitle(symbol);
    }
}
