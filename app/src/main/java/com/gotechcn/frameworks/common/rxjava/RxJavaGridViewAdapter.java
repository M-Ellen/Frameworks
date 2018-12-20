package com.gotechcn.frameworks.common.rxjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gotechcn.frameworks.R;

import java.util.List;

public class RxJavaGridViewAdapter extends BaseAdapter {
    private Context mContext = null;
    private LayoutInflater mInflater = null;
    private List<RxJavaDatas> mDataList = null;


    private ViewHolder mViewHolder = null;


    public RxJavaGridViewAdapter(Context context, List<RxJavaDatas> dataList) {
        mContext = context;
        mDataList = dataList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public Object getItem(int positon) {
        return mDataList == null ? null : mDataList.get(positon);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            mViewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.rxjava_gird_item, null);
            mViewHolder.mNameTv = convertView.findViewById(R.id.name_tv);
            mViewHolder.mDesTv = convertView.findViewById(R.id.des_tv);
            convertView.setTag(mViewHolder);

        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        RxJavaDatas itemData = (RxJavaDatas) getItem(position);

        mViewHolder.mNameTv.setText(itemData.getFunctionName());
        mViewHolder.mDesTv.setText(itemData.getFunctionDes());

        return convertView;
    }

    private class ViewHolder {
        private TextView mNameTv = null;
        private TextView mDesTv = null;
    }
}
