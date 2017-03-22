package com.gotechcn.frameworks.okhttp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gotechcn.frameworks.R;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpActivity extends Activity implements View.OnClickListener {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final int HTTP_GET = 1;
    private static final int HTTP_POST = 2;
    private Button mGet;
    private Button mPost;
    private TextView mResult;
    private TextView mtitle;
    private OkHttpClient mClient;
    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what) {
                case  HTTP_GET://get
                    mResult.setText(msg.obj.toString());
                    break;
                case HTTP_POST ://post
                    mResult.setText(msg.obj.toString());
                    break;
                default:
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        initView();
        initData();
        setListener();

    }

    private void setListener() {
        mGet.setOnClickListener(this);
        mPost.setOnClickListener(this);
    }

    private void initData() {
        mtitle.setText("OkHttp的使用");
        mClient = new OkHttpClient();
    }

    private void initView() {
        mtitle = (TextView) findViewById(R.id.tv_title);
        mGet = (Button) findViewById(R.id.btn_okhttp_get);
        mPost = (Button) findViewById(R.id.btn_okhttp_post);
        mResult = (TextView) findViewById(R.id.tv_okhttp_result);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_okhttp_get:
                getDataFromget();
                break;
            case R.id.btn_okhttp_post:
                getDataFrompost();
                break;
            default:
                break;
        }
    }

    private void getDataFromget() {
        new Thread(){
            public void run(){
                try {
                    String result = getUrl("http://api.m.mtime.cn/PageSubArea/TrailerList.api");
                    Message msg = new Message();
                    msg.what = HTTP_GET;
                    msg.obj = result;
                    mHandler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }.start();
    }
    private void getDataFrompost() {
        new Thread(){
            public void run(){
                try {
                    String result = post("http://api.m.mtime.cn/PageSubArea/TrailerList.api","");
                    Message msg = new Message();
                    msg.what = HTTP_POST;
                    msg.obj = result;
                    mHandler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }.start();
    }

    /**
     * 原生get 请求 （不能在主线程执行请求，否则有 NetworkOnMainThreadException 异常）
     */
    private String getUrl(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = mClient.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 原生post 请求
     */
    private String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = mClient.newCall(request).execute();
        return response.body().string();
    }
}
