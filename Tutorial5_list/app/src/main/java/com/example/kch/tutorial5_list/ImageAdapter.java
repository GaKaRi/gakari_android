package com.example.kch.tutorial5_list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by kch on 2017. 9. 23..
 */

public class ImageAdapter extends ArrayAdapter<String>{
    ImageAdapter(Context context, String [] items){
        super(context, R.layout.image_layout, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater imageInflater = LayoutInflater.from(getContext());

        //image_layout.xml을 View객체에 저장함
        View view = imageInflater.inflate(R.layout.image_layout, parent, false);

        //리스트뷰의 갯수대로 실행하게 되는데 생성자를 이용해서 만들었으므로 각각 position에 맞는 데이터를 일단 item에 저장함
        String item = getItem(position);
        TextView textView = (TextView)view.findViewById(R.id.textView);
        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);

        //TextView에 설정 해주는 부분
        textView.setText(item);//가져온 item을 텍스트뷰에 뿌려줌
        imageView.setImageResource(R.mipmap.heart);//리소스에 저장해둔 이미지도 뿌려줌

        return view;//뷰를 반환함
    }
}
