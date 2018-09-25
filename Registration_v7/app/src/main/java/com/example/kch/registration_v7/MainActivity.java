package com.example.kch.registration_v7;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

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
        noticedList.add(new Notice("공지사항", "gakari", "2018-09-09"));
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
        noticedList.add(new Notice("공지사항", "gakari", "2018-09-20"));
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

    }
}
