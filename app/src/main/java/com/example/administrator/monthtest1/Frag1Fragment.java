package com.example.administrator.monthtest1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class Frag1Fragment extends Fragment {
    private String path = "http://baobab.kaiyanapp.com/api/v4/discovery/hot?start=0&num=20&udid=f4cbbcd2e9444b09a73bf9f3de46c0ec6392c2ba&vc=183&vn=3.5.1&deviceModel=Redmi%20Note%204&first_channel=eyepetizer_xiaomi_market&last_channel=eyepetizer_xiaomi_market&system_version_code=23";
    private XListView mLv1;
    private int i=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag1, container, false);
        //自定义的AsyncTask
        new MyAsyncTask().execute();
        initView(view);
        return view;
    }

    private void initView(View view) {
        mLv1 = (XListView) view.findViewById(R.id.lv1);
        mLv1.setPullLoadEnable(true);
    }

    private class MyAsyncTask extends AsyncTask<Void, Integer, String> {

        private String textFromStream;

        //这个方法运行在主线程,在doInBackground之前运行,我们一般做初始化
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(path).openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(6000);
                connection.setReadTimeout(6000);
                int code = connection.getResponseCode();
                if (code == 200) {
                    InputStream inputStream = connection.getInputStream();
                    textFromStream = Tools.getTextFromStream(inputStream);
                }
                return textFromStream;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        //运行在主线程,这个方法在doInBackground执行之后执行.我们一般做从网络拿到数据,使用的数据的操作
        @Override
        protected void onPostExecute(String s) {
            ////////////////////拿到处理后的数据,更新UI///////////////////////////////
            super.onPostExecute(s);
            Gson gson = new Gson();
            Bean bean = gson.fromJson(s, Bean.class);

            ListViewAdapter listViewAdapter = new ListViewAdapter(getContext(), bean);
            mLv1.setAdapter(listViewAdapter);
        }
    }

}
