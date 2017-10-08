package com.example.kch.proj_manager_v2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText idText = (EditText)findViewById(R.id.idText);
        EditText passwordText = (EditText)findViewById(R.id.passwordText);
        EditText nameText = (EditText)findViewById(R.id.nameText);
        EditText ageText = (EditText)findViewById(R.id.ageText);

        Button regbtn = (Button)findViewById(R.id.registerbtn);
    }
}
