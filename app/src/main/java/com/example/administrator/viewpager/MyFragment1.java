package com.example.administrator.viewpager;

/**
 * Created by Administrator on 2017/6/4 0004.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.administrator.viewpager.R.layout.page1;


public class MyFragment1 extends Fragment
{ private View view,view2;
    private    String json ="";
    private Button btn1;
    private TextView aa1;
    private TextView aa0;
    private String ru;
    private String records;
    private sendsql ss;
    private Context mContext;
    private final OkHttpClient client = new OkHttpClient();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        view = inflater.inflate(page1, null);
        View view = inflater.inflate(page1, null);
        btn1 = (Button) view.findViewById(R.id.button1);
        aa1 = (TextView)view.findViewById(R.id.textView1);

        return view; }
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);


        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                Toast.makeText(getActivity(), "发送成功", Toast.LENGTH_SHORT).show();
                ss=new sendsql();
                ss.start();int i=0;
                do{i++;}while(ru==null||i<1000);//这坑迟早要填不然迟早药丸
                if(i<=1000)
//                    Toast.makeText(getActivity(), "连不上服务器，请检查网络", Toast.LENGTH_SHORT).show();
//.getText().toString()
                json=ru;
                Gson gson = new Gson();
                MyFragment1.DataListBean1 result = gson.fromJson(json, MyFragment1.DataListBean1.class);

                List<MyFragment1.DataListBean1.DataListBean> dataList= result.getDataList();
                String city = "id  品名 数量 品牌 存放地点 CASID  规格型号 单价 入库时间 "+"\n";
                for(MyFragment1.DataListBean1.DataListBean id : dataList ){
                    city +=id.getId()+"  "+id.getName()+"  "+id.getNumber()+"  "+id.getCompany()+"  "+id.getRetenloca()+"  "+id.getCasid()+"  "+id.getSpecification()+"  "+id.getPrice()+"  "+id.getStoragetime()+"\n";
                }
                aa1.setText(city);
            }
        });
    }

    private class sendsql extends Thread{
        @Override
        public void run() {
            try {
                run1();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void run1() throws Exception {
//        String sql2 = sql.getText().toString();
//        String postBody = sql2;
        String sql2="select * from  tb_managersystem";

        RequestBody formBody = new FormBody.Builder()
                .add("fname", sql2)
                .build();
        Request request = new Request.Builder()
                .url("http://35.185.137.125/123.php")
                .post(formBody)
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        ru=response.body().string();
    }

    public static class DataListBean1 {


        private List<DataListBean> dataList;

        public List<DataListBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListBean> dataList) {
            this.dataList = dataList;
        }

        public static class DataListBean {
            /**
             * id : 1
             * name : 硝酸钾
             * number : 2
             * company : 国药集团
             * retenloca : 4-R1
             * casid : 7757-79-1
             * specification : 500g
             * price : null
             * storagetime : null
             */

            private String id;
            private String name;
            private String number;
            private String company;
            private String retenloca;
            private String casid;
            private String specification;
            private Object price;
            private Object storagetime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public String getRetenloca() {
                return retenloca;
            }

            public void setRetenloca(String retenloca) {
                this.retenloca = retenloca;
            }

            public String getCasid() {
                return casid;
            }

            public void setCasid(String casid) {
                this.casid = casid;
            }

            public String getSpecification() {
                return specification;
            }

            public void setSpecification(String specification) {
                this.specification = specification;
            }

            public Object getPrice() {
                return price;
            }

            public void setPrice(Object price) {
                this.price = price;
            }

            public Object getStoragetime() {
                return storagetime;
            }

            public void setStoragetime(Object storagetime) {
                this.storagetime = storagetime;
            }
        }
    }}