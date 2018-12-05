package com.gotechcn.frameworks.thirdparty;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gotechcn.frameworks.R;
import com.gotechcn.frameworks.fragment.BaseFragment;
import com.gotechcn.frameworks.recycleview.DividerListItemDecoration;
import com.gotechcn.frameworks.thirdparty.lottie.LottieActivity;

/**
 * Created by Mk on 2017/2/20.
 * 第三方
 */
public class ThirdPartyFragment extends BaseFragment implements ThirdPartyAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private String [] mDatas = new String[]{"lottie", "....."};
    ThirdPartyAdapter mAdapter;
    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_third_party, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_third);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ThirdPartyAdapter(getContext(),mDatas);
        mAdapter.setItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerListItemDecoration(getContext(), DividerListItemDecoration.VERTICAL_LIST));
        return view;
    }

    @Override
    public void onItemClickListener(View view, int position) {
        switch (position) {
            case 0:
                Intent lottieIntent = new Intent(getActivity(), LottieActivity.class);
                startActivity(lottieIntent);
                break;
            default:
                break;
        }
    }

}
