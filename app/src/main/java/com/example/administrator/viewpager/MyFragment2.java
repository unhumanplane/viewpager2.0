package com.example.administrator.viewpager;

/**
 * Created by Administrator on 2017/6/4 0004.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;


import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MyFragment2 extends Fragment

{private    String id;
    private    String json ="{\"dataList\":[{\"id\":\"1\",\"name\":\"\\u785d\\u9178\\u94be\",\"number\":\"2\",\"company\":\"\\u56fd\\u836f\\u96c6\\u56e2\",\"retenloca\":\"4-R1\",\"casid\":\"7757-79-1\",\"specification\":\"500g\",\"price\":null,\"storagetime\":null},{\"id\":\"2\",\"name\":\"\\u786b\\u4ee3\\u786b\\u9178\\u94a0\",\"number\":\"1\",\"company\":\"\\u56fd\\u836f\\u96c6\\u56e2\",\"retenloca\":\"4-R3\",\"casid\":\"7772-98-7\",\"specification\":\"500g\",\"price\":null,\"storagetime\":null},{\"id\":\"3\",\"name\":\"\\u82ef\\u915a\",\"number\":\"1\",\"company\":\"Aldrich\",\"retenloca\":\"4-R3\",\"casid\":\"108-95-2\",\"specification\":\"100g\",\"price\":null,\"storagetime\":null}]}";
    private View view,view2;


    private sendsql2 ss2;
    private final OkHttpClient client = new OkHttpClient();//网络功能

    private TextView textViewid;
    private EditText editTextid;
    private EditText editTextnumber;
    private EditText editTextcompany;
    private EditText editTextretenloca;
    private EditText editTextcasid;
    private EditText editTextspecification;
    private EditText editTextprice;
    private EditText editTextstoragetime;
    private TextView textViewnumber;
    private TextView textViewcompany;
    private TextView textViewretenloca;
    private TextView textViewcasid;
    private TextView textViewspecification;
    private TextView textViewprice;
    private TextView textViewstoragetime;
    private TextView textView2;
    private TextView textView3;
    private EditText editTextname;
    private Button button;
    private  String sql2="INSERT INTO  tb_managersystem VALUES ('8','氧化氢','3','水务局','****','****','500g','','');";



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // TODO Auto-generated method stub
        View view2 = inflater.inflate(R.layout.page2, container, false);
        textViewid = (TextView)view2. findViewById(R.id.textViewid);
        editTextid = (EditText) view2.findViewById(R.id.editTextid);
        editTextnumber = (EditText) view2.findViewById(R.id.editTextnumber);
        editTextcompany = (EditText) view2.findViewById(R.id.editTextcompany);
        editTextretenloca = (EditText) view2.findViewById(R.id.editTextretenloca);
        editTextcasid = (EditText)view2. findViewById(R.id.editTextcasid);
        editTextspecification = (EditText) view2.findViewById(R.id.editTextspecification);
        editTextprice = (EditText) view2.findViewById(R.id.editTextprice);
        editTextstoragetime = (EditText) view2.findViewById(R.id.editTextstoragetime);
        textViewnumber = (TextView) view2.findViewById(R.id.textViewnumber);
        textViewcompany = (TextView) view2.findViewById(R.id.textViewcompany);
        textViewretenloca = (TextView) view2.findViewById(R.id.textViewretenloca);
        textViewcasid = (TextView) view2.findViewById(R.id.textViewcasid);
        textViewspecification = (TextView) view2.findViewById(R.id.textViewspecification);
        textViewprice = (TextView) view2.findViewById(R.id.textViewprice);
        textViewstoragetime = (TextView) view2.findViewById(R.id.textViewstoragetime);

//        textView3 = (TextView) view2.findViewById(R.id.textView3);

        button = (Button)view2. findViewById(R.id.button);

    return view2;
     }

    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                Toast.makeText(getActivity(), "发送成功", Toast.LENGTH_SHORT).show();
//.getText().toString()
                ss2=new sendsql2();
                ss2.start();



     }
        });
    }

    private class sendsql2 extends Thread{
        @Override
        public void run() {
            try {
                run2();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void run2() throws Exception {
    String na = editTextid.getText().toString();//注意id和name两个其实是换过来了，这坑TO-DO了
  String id= editTextname.getText().toString();
        String num = editTextnumber.getText().toString();
        String com= editTextcompany.getText().toString();
        String rl = editTextretenloca .getText().toString();
        String ca = editTextcasid.getText().toString();
        String sp = editTextspecification.getText().toString();
        String pr =editTextprice.getText().toString();
        String st =editTextstoragetime .getText().toString();

//        String postBody = sql2;

            String sql3="INSERT INTO  tb_managersystem  (name,number,company,retenloca,casid,specification,price,storagetime) \n " +
             "VALUES ('"+na+"','"+num+"','"+com+"','"+rl+"','"+ca+"','"+sp+"','"+pr+"','"+st+"');";
        RequestBody formBody = new FormBody.Builder()
                .add("fname", sql3)
                .build();
        Request request = new Request.Builder()
                .url("http://35.185.137.125/123.php")
                .post(formBody)
                .build();
      Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        json=response.body().string();

    }



}