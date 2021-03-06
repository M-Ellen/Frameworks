package com.gotechcn.frameworks.common;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.gotechcn.frameworks.BaseFragment;
import com.gotechcn.frameworks.R;
import com.gotechcn.frameworks.common.eventbus.EventBusActivity;
import com.gotechcn.frameworks.common.fresco.FrescoActivity;
import com.gotechcn.frameworks.common.json.FastJsonActivity;
import com.gotechcn.frameworks.common.json.GsonActivity;
import com.gotechcn.frameworks.common.json.JsonActivity;
import com.gotechcn.frameworks.common.logger.LoggerActivity;
import com.gotechcn.frameworks.common.okhttp.OkHttpActivity;
import com.gotechcn.frameworks.common.pulltorefresh.samples.RefreshActivity;
import com.gotechcn.frameworks.common.recycleview.RecycleViewActivity;
import com.gotechcn.frameworks.common.rxjava.RxJavaActivity;
import com.gotechcn.frameworks.common.volley.VolleyActivity;

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
                else if (data.toLowerCase().equals("netroid")) {
                    Intent intent = new Intent(mContext, EventBusActivity.class);
                    mContext.startActivity(intent);
                 }
                else if (data.toLowerCase().equals("fresco")) {
                    Intent intent = new Intent(mContext, FrescoActivity.class);
                    mContext.startActivity(intent);
                 }
                else if (data.toLowerCase().equals("pulltorefresh")) {
                    Intent intent = new Intent(mContext, RefreshActivity.class);
                    mContext.startActivity(intent);
                 }
                else if (data.toLowerCase().equals("rxjava")) {
                    Intent intent = new Intent(mContext, RxJavaActivity.class);
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

        //准备数据  "Afinal","Picasso","greenDao","jcvideoplayer", "UniversalVideoView",
        mDatas = new String[]{"Volley", "Json", "Gson", "FastJson","RecyclerView", "OkHttp", "Logger",
                "Eventbus","Netroid", "Butterknife","Imageloader","Retrofit2", "Fresco", "Glide",
                "RxJava","pulltorefresh", "Expandablelistview", "....."};

        //设置适配器
        adapter = new CommonFrameFragmentAdapter(mContext, mDatas);
        mListView.setAdapter(adapter);
    }
}
