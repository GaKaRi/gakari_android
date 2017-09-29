package com.example.kch.tutorial6_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by kch on 2017. 9. 24..
 */

public class SubActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sub);
        TextView textView = (TextView)findViewById(R.id.nameText2);
        Intent intent = getIntent();
        textView.setText(intent.getStringExtra("nameText").toString());//가져온 값을 뿌려줌
    }
}
