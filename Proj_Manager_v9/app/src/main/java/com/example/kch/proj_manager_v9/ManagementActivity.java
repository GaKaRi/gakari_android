package com.example.kch.proj_manager_v9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.content.Intent;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ManagementActivity extends AppCompatActivity {

    private ListView listView;
    private UserListAdapter adapter;
    private List<User> userList;
    private List<User> saveList;//회원검색 기능용 복사본

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
        saveList = new ArrayList<User>();

        //어댑터 초기화부분 userList와 어댑터를 연결해준다.
        //회원 삭제 및 관리자 기능 아래 부분 수정됨
        //adapter = new UserListAdapter(getApplicationContext(), userList);
        adapter = new UserListAdapter(getApplicationContext(), userList, this, saveList);//로 수정됨
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

                if(!userID.equals("admin"))//관리자계정은 삭제하지않게 하기위해서 씀
                {
                    userList.add(user);//리스트뷰에 값을 추가해줍니다
                    saveList.add(user);//여기도 똑같이 값을 추가해줍니다. 회원검색기능용
                }
                count++;
            }


        }catch(Exception e){
            e.printStackTrace();
        }

        EditText search = (EditText)findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchUser(charSequence.toString());//회원 검색 기능용
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void searchUser(String search){
        userList.clear();
        for(int i = 0; i < saveList.size(); i++){
            if(saveList.get(i).getUserID().contains(search)){//contains메소드로 search 값이 있으면 true를 반환함
                userList.add(saveList.get(i));
            }
        }
        adapter.notifyDataSetChanged();//어댑터에 값일 바뀐것을 알려줌

    }
}
