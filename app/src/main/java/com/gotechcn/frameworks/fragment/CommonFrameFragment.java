package com.gotechcn.frameworks.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.gotechcn.frameworks.R;
import com.gotechcn.frameworks.eventbus.EventBusActivity;
import com.gotechcn.frameworks.json.FastJsonActivity;
import com.gotechcn.frameworks.json.GsonActivity;
import com.gotechcn.frameworks.json.JsonActivity;
import com.gotechcn.frameworks.logger.LoggerActivity;
import com.gotechcn.frameworks.okhttp.OkHttpActivity;
import com.gotechcn.frameworks.recycleview.RecycleViewActivity;
import com.gotechcn.frameworks.volley.VolleyActivity;

/**
 * Created by Mk on 2017/2/20.
 */
public class CommonFrameFragment extends BaseFragment {

    private static final String TAG = "CommonFrameFragment";
    private ListView mListView;

    private String[] mDatas;

    private CommonFrameFragmentAdapter adapter;

    @Override
    protected View initView() {
        Log.e(TAG, "常用框架Fragment页面被初始化了...");

        View view = View.inflate(mContext, R.layout.fragment_common_frame, null);
        mListView = (ListView) view.findViewById(R.id.listview);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = mDatas[position];

                if (data.toLowerCase().equals("volley")) {
                    // 点击条目跳转到volley页面
                    Intent intent = new Intent(mContext, VolleyActivity.class);
                    mContext.startActivity(intent);

                }
                else if (data.toLowerCase().equals("recyclerview")) {
                    // 点击条目跳转到手动recyclerview页面
                    Intent intent = new Intent(mContext, RecycleViewActivity.class);
                    mContext.startActivity(intent);
                 }
                else if (data.toLowerCase().equals("json")) {
                    // 点击条目跳转到手动JSON解析页面
                    Intent intent = new Intent(mContext, JsonActivity.class);
                    mContext.startActivity(intent);
                 }
                else if (data.toLowerCase().equals("gson")) {
                    // 点击条目跳转到手动JSON解析页面
                    Intent intent = new Intent(mContext, GsonActivity.class);
                    mContext.startActivity(intent);
                 }
                else if (data.toLowerCase().equals("fastjson")) {
                    Intent intent = new Intent(mContext, FastJsonActivity.class);
                    mContext.startActivity(intent);
                 }
                else if (data.toLowerCase().equals("okhttp")) {
                    Intent intent = new Intent(mContext, OkHttpActivity.class);
                    mContext.startActivity(intent);
                 }
                else if (data.toLowerCase().equals("logger")) {
                    Intent intent = new Intent(mContext, LoggerActivity.class);
                    mContext.startActivity(intent);
                 }
                else if (data.toLowerCase().equals("eventbus")) {
                    Intent intent = new Intent(mContext, EventBusActivity.class);
                    mContext.startActivity(intent);
                 }

                // 点击位置的显示
                Toast.makeText(mContext, "data==" + data, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


    @Override
    protected void initData() {
        super.initData();

        Log.e(TAG, "常用框架Fragment数据被初始化了...");

        //准备数据
        mDatas = new String[]{"Volley", "Json", "Gson", "FastJson","RecyclerView", "OkHttp", "Logger", "Afinal",
                "Eventbus","Butterknife","Imageloader","Picasso","Retrofit2", "Fresco", "Glide", "greenDao",
                "RxJava","jcvideoplayer", "pulltorefresh", "Expandablelistview", "UniversalVideoView", "....."};

        //设置适配器
        adapter = new CommonFrameFragmentAdapter(mContext, mDatas);
        mListView.setAdapter(adapter);
    }
}
