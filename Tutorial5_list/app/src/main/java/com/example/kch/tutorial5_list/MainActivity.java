package com.example.kch.tutorial5_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String [] items = {"망고쥬스", "토마토쥬스", "포도쥬스"};

        //리스트뷰의 내용을 만들기 위한 adapter를 만듬(Adapter패턴 사용)
        ListAdapter adapter = new ImageAdapter(this, items);
        ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);//만든 adapter를 리스트뷰에 설정해줌

        //리스트뷰 리스너 설정
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                // ItemClick/ItemSelect Code
                String item = String.valueOf(parent.getItemAtPosition(i));//사용자가 터치한 아이템을 문자로 받아서 item객체에 저장함
                Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();//터치한 아이템을 토스트메시지로 보여줌

            }
        });

    }
}
