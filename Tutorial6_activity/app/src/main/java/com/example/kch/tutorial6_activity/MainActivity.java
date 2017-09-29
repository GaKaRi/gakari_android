package com.example.kch.tutorial6_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText nameText = (EditText)findViewById(R.id.nameText1);
        Button nameButton = (Button)findViewById(R.id.nameButton);

        nameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameText.getText().toString();//EditText로부터 값을 가져옴

                //인텐트 생성
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                intent.putExtra("nameText", name);//값을 넣어줌
                startActivity(intent);//다음 액티비티로 이동
            }
        });

    }
}
