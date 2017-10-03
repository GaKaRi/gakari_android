package com.example.kch.tutorial9_imgslider;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by kch on 2017. 10. 2..
 */

public class Adapter extends PagerAdapter {

    private int [] imgs = {R.drawable.click, R.drawable.hover, R.drawable.normal};
    private LayoutInflater inflater;
    private Context context;

    public Adapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return imgs.length;//총 이미지 갯수를 반환 해야됨
    }

    //instantiateItem() 메소드에서 리턴된 Ojbect가 View가  맞는지 확인하는 메소드
    //instantiateItem 에서 생성한 객체를 사용할지 여부를 판단 합니다 .
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout)object);
    }

    //ViewPager에 사용할 View 를 생성하고 등록 합니다.
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //인플레이터 사용을 위한 초기화 부분
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate메소드를 이용한 뷰를 만들어줌
        View v = inflater.inflate(R.layout.slider, container, false);

        //slider.xml에 있는 뷰들을 가져오고 세팅함
        ImageView imageView = (ImageView)v.findViewById(R.id.imageView);
        TextView textView = (TextView)v.findViewById(R.id.textView);

        //이미지 소스도 지정해줌.
        imageView.setImageResource(imgs[position]);
        textView.setText((position+1) + "번째 이미지입니다.");

        //컨테이너에 뷰를 넣어준뒤
        container.addView(v);

        return v;//뷰를 반환함
    }


    //화면에 보이지 않는 뷰는 없애서 메모리 관리를 함.
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.invalidate();
    }
}
