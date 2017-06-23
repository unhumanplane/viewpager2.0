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

public class MyFragment3 extends Fragment
{private sendsql3 ss3;
private EditText editTextshanchuid;
    private Button button2;
    private TextView textView3;
    private final OkHttpClient client = new OkHttpClient();//网络功能
    private void assignViews() {

    }

public View onCreateView(
        LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // TODO Auto-generated method stub
    View view = inflater.inflate(R.layout.page3, null);
    editTextshanchuid = (EditText) view. findViewById(R.id.editTextshanchuid);
    button2 = (Button) view.findViewById(R.id.button2);
    textView3 = (TextView) view.findViewById(R.id.textView3);
    return view;
}
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);


        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "发送成功", Toast.LENGTH_SHORT).show();
//.getText().toString()
                ss3=new sendsql3();
                ss3.start();



            }
        });
    }
    private class sendsql3 extends Thread{
        @Override
        public void run() {
            try {
                run3();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void run3() throws Exception {
       String shanchuid= editTextshanchuid. getText().toString();

//        String postBody = sql2;

        String sql2="DELETE FROM tb_managersystem  " +
                "WHERE id='"+shanchuid+"';";
        RequestBody formBody = new FormBody.Builder()
                .add("fname", sql2)
                .build();
        Request request = new Request.Builder()
                .url("http://35.185.137.125/123.php")
                .post(formBody)
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);




    }

}
