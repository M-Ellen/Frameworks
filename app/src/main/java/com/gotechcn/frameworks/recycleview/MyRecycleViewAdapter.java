package com.gotechcn.frameworks.recycleview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.gotechcn.frameworks.R;

import java.util.ArrayList;

/**
 * Created by Mk on 2017/2/16.
 */

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> {


    private Context mContext;
    private ArrayList<String> mDatas;

    public MyRecycleViewAdapter(Context context, ArrayList<String> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_recycle_view,null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        runEnterAnimation(holder.itemView, position);
        holder.tvTitle.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    //添加数据
    public void addData(int position){
        mDatas.add(position,"New content");
        notifyItemInserted(position);
    }

    //删除数据
    public void removeData(int position){
        mDatas.remove(position);
        notifyItemRemoved(position);

    }

    /**
     * 添加自定义的监听方法
     */
    public interface onItemClickListener{

        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    private onItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(onItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

    /**
     * 添加加载的动画
     */
    private boolean animationsLocked = false;
    private int lastAnimatedPosition=-1;
    private boolean delayEnterAnimation = true;
    private void runEnterAnimation(View view, int position) {

        if (animationsLocked) return;//animationsLocked是布尔类型变量，一开始为false，确保仅屏幕一开始能够显示的item项才开启动画

        if (position > lastAnimatedPosition) {//lastAnimatedPosition是int类型变量，一开始为-1，这两行代码确保了recycleview滚动式回收利用视图时不会出现不连续的效果
            lastAnimatedPosition = position;
            view.setTranslationY(500);//相对于原始位置下方400
            view.setAlpha(0.f);//完全透明
            //每个item项两个动画，从透明到不透明，从下方移动到原来的位置
            //并且根据item的位置设置延迟的时间，达到一个接着一个的效果
            view.animate()
                    .translationY(0).alpha(1.f)
                    .setStartDelay(delayEnterAnimation ? 20 * (position) : 0)//根据item的位置设置延迟时间，达到依次动画一个接一个进行的效果
                    .setInterpolator(new DecelerateInterpolator(0.5f))//设置动画效果为在动画开始的地方快然后慢
                    .setDuration(700)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            animationsLocked = true;//确保仅屏幕一开始能够显示的item项才开启动画，也就是说屏幕下方还没有显示的item项滑动时是没有动画效果
                        }
                    })
                    .start();
        }
    }


    /**
     *   内部类
     */

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTitle;
        private ImageView ivIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);

            //添加监听
            if(mOnItemClickListener != null) {

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(v,getLayoutPosition());

                    }
                });

                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        mOnItemClickListener.onItemLongClick(v,getLayoutPosition());
                        return false;
                    }
                });
            }
        }
    }
}
