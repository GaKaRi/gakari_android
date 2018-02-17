package com.example.kch.proj_manager_v6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ManagementActivity extends AppCompatActivity {

    private ListView listView;
    private UserListAdapter adapter;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
/*        TextView userListTextView = (TextView)findViewById(R.id.listView);

        //ManagementActivity는 MainActivity에서 호출되므로 호출시 넘겨준 데이터를 뿌려주는 역할을 한다
        Intent intent = getIntent();
        //intent.putExtra("userList", result); 에서 userList에 저장했으므로 아래와 같이 쓰게됨
        userListTextView.setText(intent.getStringExtra("userList"));*/

        Intent intent = getIntent();

        listView = (ListView)findViewById(R.id.listView);
        userList = new ArrayList<User>();

        //어댑터 초기화부분 userList와 어댑터를 연결해준다.
        adapter = new UserListAdapter(getApplicationContext(), userList);
        listView.setAdapter(adapter);

        try{
            //intent로 값을 가져옵니다 이때 JSONObject타입으로 가져옵니다
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("userList"));


            //List.php 웹페이지에서 response라는 변수명으로 JSON 배열을 만들었음..
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;

            String userID, userPassword, userName, userAge;

            //JSON 배열 길이만큼 반복문을 실행
            while(count < jsonArray.length()){
                //count는 배열의 인덱스를 의미
                JSONObject object = jsonArray.getJSONObject(count);

                userID = object.getString("userID");//여기서 ID가 대문자임을 유의
                userPassword = object.getString("userPassword");
                userName = object.getString("userName");
                userAge = object.getString("userAge");

                //값들을 User클래스에 묶어줍니다
                User user = new User(userID, userPassword, userName, userAge);
                userList.add(user);//리스트뷰에 값을 추가해줍니다
                count++;
            }


        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
