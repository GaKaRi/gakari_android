package com.example.kch.janggiwang_v1;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText nameText = (EditText)findViewById(R.id.nameText);

        Button regbtn = (Button)findViewById(R.id.registerbtn);

        regbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String userName = nameText.getText().toString();

                //Toast.makeText(getApplicationContext(), "ID"+userID+"PW"+userPassword, Toast.LENGTH_SHORT).show();

                //4. 콜백 처리부분(volley 사용을 위한 ResponseListener 구현 부분)
                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    //서버로부터 여기서 데이터를 받음
                    @Override
                    public void onResponse(String response) {
                        try {
                            //서버로부터 받는 데이터는 JSON타입의 객체
                            JSONObject jsonResponse = new JSONObject(response);

                            //그중 Key값이 "success"인 것을 가져온다.
                            boolean success = jsonResponse.getBoolean("success");

                            //회원 가입 성공시 success값이 true임
                            if(success){

                                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();

                                //알림상자를 만들어서 보여줌
                             /*   AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("register success!!")
                                .setPositiveButton("ok", null)
                                .create()
                                .show();*/

                                finish();//회원 삭제때 추가됨 아래는 주석처리함
                                //그리고 첫화면으로 돌아감
                                /*Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);*/

                            }
                            //회원 가입 실패시 success값이 false임
                            else{
                                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();

                                //알림상자를 만들어서 보여줌
                            /*    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("register fail!!")
                                        .setNegativeButton("ok", null)
                                        .create()
                                        .show();*/

                            }


                        }catch(JSONException e){
                            e.printStackTrace();
                        }

                    }
                };//responseListener 끝

                //volley 사용법
                //1. RequestObject를 생성한다. 이때 서버로부터 데이터를 받을 responseListener를 반드시 넘겨준다.
                RegisterRequest registerRequest = new RegisterRequest(userName, responseListener);
                //2. RequestQueue를 생성한다.
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                //3. RequestQueue에 RequestObject를 넘겨준다.
                queue.add(registerRequest);

            }
        });

    }
}
