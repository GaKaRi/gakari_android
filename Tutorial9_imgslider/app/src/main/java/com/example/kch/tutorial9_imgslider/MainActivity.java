package com.example.kch.tutorial9_imgslider;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Adapter adapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager)findViewById(R.id.view);
        //어댑터 선언후
        adapter = new Adapter(this);
        viewPager.setAdapter(adapter);//뷰페이저에 어댑터를 연결해줌
    }
}
