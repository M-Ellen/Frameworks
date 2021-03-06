package com.gotechcn.frameworks.customview;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.gotechcn.frameworks.BaseFragment;
import com.gotechcn.frameworks.R;
import com.gotechcn.frameworks.customview.RotateMenu.RotateMenuActivity;
import com.gotechcn.frameworks.customview.Drawable.CustomViewActivity;
import com.gotechcn.frameworks.customview.demo.WaterRippleActivity;
import com.gotechcn.frameworks.customview.viewpager.ViewPagerActivity;
import com.gotechcn.frameworks.common.CommonFrameFragmentAdapter;

/**
 * Created by pengzhimao on 2017/2/20.
 * 自定义控件
 */
public class CustomFragment extends BaseFragment {
    private static final String TAG = "CustomFragment";
    private ListView mListView;

    private String[] mDatas;
    private CommonFrameFragmentAdapter mAdapter;

    @Override
    protected View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_common_frame, null);
        mListView = (ListView)view.findViewById(R.id.listview);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0 :
                        Intent intent2 = new Intent(mContext,CustomViewActivity.class);
                        startActivity(intent2);
                        break;
                    case 1 :
                        Intent intent = new Intent(mContext,ViewPagerActivity.class);
                        startActivity(intent);
                        break;
                    case 2 :
                        Intent intent1 = new Intent(mContext,RotateMenuActivity.class);
                        startActivity(intent1);
                        break;
                    case 3 :
                        Intent intent3 = new Intent(mContext,WaterRippleActivity.class);
                        startActivity(intent3);
                        break;
                    case 4:

                        break;
                    default:
                        break;
                }
                // 点击位置的显示
                Toast.makeText(mContext, "data==" + mDatas[position],Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        //准备数据
        mDatas = new String[]{"Canvas/Paint 基础","Viewpager","RotateMenu","WaterRipple 水波纹进度"};
        //设置适配器
        mAdapter = new CommonFrameFragmentAdapter(mContext, mDatas);
        mListView.setAdapter(mAdapter);

    }
}
