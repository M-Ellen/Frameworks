package com.gotechcn.frameworks.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.gotechcn.frameworks.R;
import com.gotechcn.frameworks.mvp.view.UserLoginActivity;

/**
 * Created by Mk on 2017/2/20.
 */
public class OtherFragment extends BaseFragment {

    private static final String TAG = "OtherFragment";
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
                        Intent intent = new Intent(mContext,UserLoginActivity.class);
                        startActivity(intent);
                        break;
                    case 1 :

                        break;
                    case 2 :

                        break;
                    case 3 :

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
        mDatas = new String[]{"mvp"};
        //设置适配器
        mAdapter = new CommonFrameFragmentAdapter(mContext, mDatas);
        mListView.setAdapter(mAdapter);

    }
}
