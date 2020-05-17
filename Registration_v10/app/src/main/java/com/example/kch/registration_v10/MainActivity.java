package com.example.kch.registration_v10;

import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //7강때 추가된 부분
    private ListView noticeListView;
    private NoticeListAdapter adapter;
    private List<Notice> noticedList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //7강때 추가된 부분
        noticeListView = (ListView)findViewById(R.id.noticeListView);
        noticedList = new ArrayList<Notice>();
        //8강때 삭제됨
       /* noticedList.add(new Notice("공지사항", "gakari", "2018-09-09"));
        noticedList.add(new Notice("공지사항", "gakari", "2018-09-10"));
        noticedList.add(new Notice("공지사항", "gakari", "2018-09-11"));
        noticedList.add(new Notice("공지사항", "gakari", "2018-09-12"));
        noticedList.add(new Notice("공지사항", "gakari", "2018-09-13"));
        noticedList.add(new Notice("공지사항", "gakari", "2018-09-14"));
        noticedList.add(new Notice("공지사항", "gakari", "2018-09-15"));
        noticedList.add(new Notice("공지사항", "gakari", "2018-09-16"));
        noticedList.add(new Notice("공지사항", "gakari", "2018-09-17"));
        noticedList.add(new Notice("공지사항", "gakari", "2018-09-18"));
        noticedList.add(new Notice("공지사항", "gakari", "2018-09-19"));
        noticedList.add(new Notice("공지사항", "gakari", "2018-09-20"));*/
        adapter = new NoticeListAdapter(getApplicationContext(), noticedList);
        noticeListView.setAdapter(adapter);


        final Button courseButton = (Button)findViewById(R.id.courseButton);
        final Button statisticsButton = (Button)findViewById(R.id.statisticsButton);
        final Button scheduleButton = (Button)findViewById(R.id.scheduleButton);
        final LinearLayout notice = (LinearLayout)findViewById(R.id.notice);

        courseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notice.setVisibility(View.GONE);//공지사항이 안보임
                courseButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                statisticsButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new CourseFragment());//프래그먼트를 바꿔줌
                fragmentTransaction.commit();
            }
        });

        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notice.setVisibility(View.GONE);//공지사항이 안보임
                courseButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                statisticsButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new StatisticsFragment());//프래그먼트를 바꿔줌
                fragmentTransaction.commit();
            }
        });

        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notice.setVisibility(View.GONE);//공지사항이 안보임
                courseButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                statisticsButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new ScheduleFragment());//프래그먼트를 바꿔줌
                fragmentTransaction.commit();
            }
        });

        //8강때 추가된부분
        //AsyncTask 실행 부분
        new BackgroundTask().execute();

    }

    //PHP서버에 접속해서 JSON타입으로 데이터를 가져옴
    class BackgroundTask extends AsyncTask<Void, Void, String>{
        String target;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            target = "http://10.0.2.2:8080/registration/NoticeList.php";

        }

        //실제 데이터를 가져오는 부분임
        @Override
        protected String doInBackground(Void... voids) {
            try{

                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;//결과 값을 여기에 저장함
                StringBuilder stringBuilder = new StringBuilder();

                //버퍼생성후 한줄씩 가져옴
                while((temp = bufferedReader.readLine()) != null){
                    stringBuilder.append(temp + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();//결과값이 여기에 리턴되면 이 값이 onPostExcute의 파라미터로 넘어감

            }catch(Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        //여기서는 가져온 데이터를 Notice객체에 넣은뒤 리스트뷰 출력을 위한 List객체에 넣어주는 부분
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String noticeContent, noticeName, noticeDate;


                //json타입의 값을 하나씩 빼서 Notice 객체에 저장후 리스트에 추가하는 부분
                while(count < jsonArray.length()){
                    JSONObject object = jsonArray.getJSONObject(count);
                    noticeContent = object.getString("noticeContent");
                    noticeName = object.getString("noticeName");
                    noticeDate = object.getString("noticeDate");
                    Notice notice = new Notice(noticeContent, noticeName, noticeDate);
                    noticedList.add(notice);
                    count++;
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }


    //9강때 추가된부분 백버튼을 두번 누르면 앱이 종료되게 함.
    private long pressedTime;

    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        //백버튼이 눌리고 1.5초안에 또 눌리면 종료가됨

        if ( pressedTime == 0 ) {
            Toast.makeText(MainActivity.this, " 한 번 더 누르면 종료됩니다." , Toast.LENGTH_LONG).show();
            pressedTime = System.currentTimeMillis();
        }
        else {
            int seconds = (int) (System.currentTimeMillis() - pressedTime);

            if ( seconds > 1500 ) {
                Toast.makeText(MainActivity.this, " 한 번 더 누르면 종료됩니다." , Toast.LENGTH_LONG).show();
                pressedTime = 0 ;
            }
            else {
                super.onBackPressed();//이걸 따라가다보면 결국 finish()를 실행 시키는 부분이 나옴
                //출처 : http://itpangpang.xyz/135
//                finish(); // app 종료 시키기
            }
        }




    }
}
