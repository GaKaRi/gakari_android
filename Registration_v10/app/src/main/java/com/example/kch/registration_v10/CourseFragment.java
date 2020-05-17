package com.example.kch.registration_v10;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CourseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseFragment newInstance(String param1, String param2) {
        CourseFragment fragment = new CourseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    //9강에 추가된 부분
    private ArrayAdapter yearAdapter;
    private Spinner yearSpinner;
    private ArrayAdapter termAdapter;

    private Spinner termSpinner;
    private ArrayAdapter areaAdapter;
    private Spinner areaSpinner;

    //10강때 추가된 부분
    private ArrayAdapter majorAdapter;
    private Spinner majorSpinner;



    private String courseUniversity = "";
    private String courseYear = "";
    private String courseTerm = "";
    private String courseArea = "";


    //9강에 추가된 부분
    //Life Cycle 상 onCreate다음에 onActivityCreated가 실행됨
    //Activity의 모든 View가 만들어지고 난 다음이기 때문에 View를 변경하는 등의 작업을 할 수 있다.

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        final RadioGroup courseUniversityGroup = (RadioGroup)getView().findViewById(R.id.courseUniversityGroup);
        yearSpinner = (Spinner)getView().findViewById(R.id.yearSpinner);
        termSpinner = (Spinner)getView().findViewById(R.id.termSpinner);
        areaSpinner = (Spinner)getView().findViewById(R.id.areaSpinner);

        //10강때 추가된 부분
        majorSpinner = (Spinner)getView().findViewById(R.id.majorSpinner);


        //라디오 버튼에 따라서 달라짐
        courseUniversityGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //라디오 버튼 그룹 선언
                RadioButton courseButton = (RadioButton)getView().findViewById(checkedId);

                //현재 라디오 버튼이 눌린 값의 text를 가져옴
                courseUniversity = courseButton.getText().toString();

                //arrays.xml의 내용을 이용해서 단순한 스피너를 만드는 부분
                yearAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.year, android.R.layout.simple_spinner_dropdown_item);
                yearSpinner.setAdapter(yearAdapter);//여기서 스피너뷰에 어댑터패턴을 이용해서 데이터를 연결해줌


                //위와 동일
                termAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.term, android.R.layout.simple_spinner_dropdown_item);
                termSpinner.setAdapter(termAdapter);

                //라디오버튼의 상태에 따라서 학부와 대학원으로 나눔
                if(courseUniversity.equals("undergraduate")){
                    areaAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.universityArea, android.R.layout.simple_spinner_dropdown_item);
                    areaSpinner.setAdapter(areaAdapter);

                    //학부선택시 교양 및 기타 항목의 모든 원소가 나오게함
                    majorAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.universityMajor, android.R.layout.simple_spinner_dropdown_item);
                    majorSpinner.setAdapter(majorAdapter);
                }else{
                    areaAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.graduateArea, android.R.layout.simple_spinner_dropdown_item);
                    areaSpinner.setAdapter(areaAdapter);

                    majorAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.graduateMajor, android.R.layout.simple_spinner_dropdown_item);
                    majorSpinner.setAdapter(majorAdapter);
                }

            }
        });

        //10강때 추가됨
        areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //스피너가 선택 됬을때 해야될 액션을 정의함
                if(areaSpinner.getSelectedItem().equals("학부 교양과목")){
                    majorAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.universityRefinementMajor, android.R.layout.simple_spinner_dropdown_item);
                    majorSpinner.setAdapter(majorAdapter);
                }

                if(areaSpinner.getSelectedItem().equals("학부 전공과목")){
                    majorAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.universityMajor, android.R.layout.simple_spinner_dropdown_item);
                    majorSpinner.setAdapter(majorAdapter);
                }

                if(areaSpinner.getSelectedItem().equals("대학원 전공과목")){
                    majorAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.graduateMajor, android.R.layout.simple_spinner_dropdown_item);
                    majorSpinner.setAdapter(majorAdapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //10강때 추가됨
        //서치버튼을 누르면 모든 강의 정보를 가져옴
        Button searchButton = (Button)getView().findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BackgroundTask().execute();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

강의에서 삭제함    */

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    //10강때 추가된 부분
    //PHP서버에 접속해서 JSON타입으로 데이터를 가져옴
    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            //다음은 스피너로 선택된 자료들을 GET타입으로 보내주는 방식임.
            try {
                target = "http://10.0.2.2:8080/registration/CourseList.php?courseUniversity=" + URLEncoder.encode(courseUniversity, "UTF-8")
                        + "&courseYear=" + URLEncoder.encode(yearSpinner.getSelectedItem().toString().substring(0, 4), "UTF-8")
                        + "&courseTerm=" + URLEncoder.encode(termSpinner.getSelectedItem().toString(), "UTF-8")
                        + "&courseArea=" + URLEncoder.encode(areaSpinner.getSelectedItem().toString(), "UTF-8") + "&courseMajor="
                        + URLEncoder.encode(majorSpinner.getSelectedItem().toString(), "UTF-8");

            }catch(Exception e){
                e.printStackTrace();
            }
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


        //10강 변경된 부분 PHP서버에서 보낸 스트링 데이터를 다이얼로그로 보여주는 부분임.
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try{


                AlertDialog dialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(CourseFragment.this.getContext());
                dialog = builder.setMessage(result)
                        .setPositiveButton("확인", null)
                        .create();

                dialog.show();

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }


}
