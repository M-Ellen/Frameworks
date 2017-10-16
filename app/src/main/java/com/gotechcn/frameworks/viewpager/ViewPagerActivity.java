package com.gotechcn.frameworks.viewpager;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gotechcn.frameworks.R;

import java.util.ArrayList;

public class ViewPagerActivity extends Activity {

    private ViewPager mViewPager;
    private LinearLayout mGroupPoint;
    private TextView mName;

    int [] ids =  {R.mipmap.a,R.mipmap.b,R.mipmap.c,R.mipmap.d,R.mipmap.e};
    String [] names = {"女神1","女神2","女神3","女神4","女神5"};

    ArrayList<ImageView> mImageViews ;
    /**
     * 保存之前的位置
     */
    private int mBeforeIndex = 0;
    /**
     * activity是否运行
     */
    private boolean isRuning  = true;

    /**
     * 实现自动滑动的效果
     */
    private  Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0) {
                int item = mViewPager.getCurrentItem() +1 ;
                mViewPager.setCurrentItem(item);
                //如果循环滑动，则要注释
//                if(isRuning) {
//                    handler.sendEmptyMessageDelayed(0, 2500);
//                }

            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        isRuning = true;
        initView();

        /**
         * ListView的使用
         * 1.在布局文件定义
         * 2.准备数据-集合
         * 3.设置适配器
         * 4.实现getView 和getCount();
         * 5.实现getView()-item.xml
         *
         * ViewPager的使用和ListView类似
         * 1.在布局文件定义
         * 2.准备数据-集合
         * 3.设置适配器-PagerAdapter
         * 4.有四个方法一定要实现的
         */
        mViewPager.setAdapter(new MyAdapter());
//        int item = Integer.MAX_VALUE/2 - Integer.MAX_VALUE/2 % mImageViews.size();
//        mViewPager.setCurrentItem(item);
        mViewPager.setCurrentItem(mImageViews.size() * 2000);
        mViewPager.addOnPageChangeListener(new MyPageChangeListener());
        handler.sendEmptyMessageDelayed(0,2000);

    }

    /**
     * 初始化
     */
    private void initView() {

        mViewPager = (ViewPager)findViewById(R.id.viewPager);
        mGroupPoint = (LinearLayout)findViewById(R.id.ll_group_point);
        mName = (TextView)findViewById(R.id.tv_name);
        mName.setText(names[0]);

        mImageViews = new ArrayList<ImageView>();
        for (int i = 0; i < ids.length; i ++ ){
            /**
             * 建立图片栏
             */
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(ids[i]);
            mImageViews.add(imageView);

            /**
             * 动态建立point
             */
            ImageView ivPoint = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            if(i != 0) {
                params.leftMargin = 10;
            }
            ivPoint.setLayoutParams(params);
            ivPoint.setBackgroundResource(R.drawable.point_selector);
            if(i == 0) {
                ivPoint.setEnabled(true);

            }else {
                ivPoint.setEnabled(false);
            }

            mGroupPoint.addView(ivPoint);

        }
    }

    /**
     * 设配器
     */
    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
//            return mImageViews.size();
            //实现无限循环
            return Integer.MAX_VALUE;
        }

        /**
         * 相当于listView的getItem（）
         * @param container
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = mImageViews.get(position % mImageViews.size());
            container.addView(imageView);
            return imageView;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
//            super.destroyItem(container, position, object);
        }

    }


    /**
     * 监听器
     */
    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        /**
         * 当页面滚动了的时候回调这个方法(必须掌握)
         * @param position 滚动页面的位置
         * @param positionOffset 当前滑动页面的百分比，例如滑动一半是0.5
         * @param positionOffsetPixels 当前页面滑动的像素
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            System.out.println("onPageScrolled++position:" + position + ",++positionOffset:" + positionOffset + ",++positionOffsetPixels:" + positionOffsetPixels);
        }

        /**
         * 当页面改变了的时候回调这个方法
         * @param position 当前被选中页面的位置
         */
        @Override
        public void onPageSelected(int position) {
            position = position % mImageViews.size();
            mName.setText(names[position]);
            mGroupPoint.getChildAt(mBeforeIndex).setEnabled(false);
            mGroupPoint.getChildAt(position).setEnabled(true);
            mBeforeIndex = position;

        }

        /**
         * 当页面状态发送变化的时候回调这个方法
         * 静止到滑动
         * 滑动到静止
         * @param state
         */
        @Override
        public void onPageScrollStateChanged(int state) {

            switch (state) {
                case ViewPager.SCROLL_STATE_DRAGGING://手指滑动状态
                    System.out.println("onPageScrollStateChanged==SCROLL_STATE_DRAGGING==正在滑动");
//                    handler.removeCallbacksAndMessages(null);
                    handler.removeMessages(0);
                    break;
                case ViewPager.SCROLL_STATE_SETTLING://滑动停止状态
                    System.out.println("onPageScrollStateChanged==SCROLL_STATE_SETTLING==自然沉降");
                    break;
                case ViewPager.SCROLL_STATE_IDLE://空闲状态
                    System.out.println("onPageScrollStateChanged==SCROLL_STATE_IDLE==空闲状态");
                    if(isRuning) {
                        handler.sendEmptyMessageDelayed(0, 2000);
                    }
                    break;

                default:
                    break;
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRuning = false;
        handler.removeMessages(0);
    }
}
