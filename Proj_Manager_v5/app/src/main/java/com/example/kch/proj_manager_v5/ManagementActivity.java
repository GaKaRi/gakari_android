package com.example.kch.proj_manager_v5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;

public class ManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
        TextView userListTextView = (TextView)findViewById(R.id.userListTextView);

        //ManagementActivity는 MainActivity에서 호출되므로 호출시 넘겨준 데이터를 뿌려주는 역할을 한다
        Intent intent = getIntent();
        //intent.putExtra("userList", result); 에서 userList에 저장했으므로 아래와 같이 쓰게됨
        userListTextView.setText(intent.getStringExtra("userList"));
    }
}
