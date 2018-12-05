package com.gotechcn.frameworks.thirdparty;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gotechcn.frameworks.R;

/**
 * @author by pzm on 2018/12/3
 */
public class ThirdPartyAdapter extends RecyclerView.Adapter<ThirdPartyAdapter.ThirdPartyViewHolder> {

    Context mContext;
    String[] mDatas;
    OnItemClickListener mListener;

    public ThirdPartyAdapter(Context mContext, String[] mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public ThirdPartyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_third_party, parent, false);
        return new ThirdPartyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ThirdPartyViewHolder holder, final int position) {
        if(mDatas ==null || mDatas.length <= 0) {
            return;
        }
        String name = mDatas[position];
        holder.mNameTv.setText(name);
        holder.mNameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null) {
                    mListener.onItemClickListener(v, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.length;
    }

    public void setItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    class ThirdPartyViewHolder extends RecyclerView.ViewHolder{

        private TextView mNameTv;

        public ThirdPartyViewHolder(View itemView) {
            super(itemView);
            mNameTv = (TextView) itemView.findViewById(R.id.tv_third_party);
        }
    }

    public interface OnItemClickListener {
        void onItemClickListener(View view, int position);
    }
}
