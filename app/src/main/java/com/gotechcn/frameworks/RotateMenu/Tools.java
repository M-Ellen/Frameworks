package com.gotechcn.frameworks.RotateMenu;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;

/**
 * user： pengzhimao   date： 2017/3/11
 */
public class Tools {

    public static void hideView(ViewGroup mLeve3) {
        hideView(mLeve3,0);
    }


    public static void hideView(ViewGroup view, int startOffSet) {
        RotateAnimation ra = new RotateAnimation(0, 180, view.getWidth()/2, view.getHeight());
        ra.setDuration(500);
        ra.setFillAfter(true);
        ra.setStartOffset(startOffSet);
        view.startAnimation(ra);
//        为了让隐藏的子视图不可用  第一种方法
        for (int i = 0; i < view.getChildCount(); i++){
            View children = view.getChildAt(i);
            children.setEnabled(false);
        }

        /**
         * 第2中方法，设置属性动画
         */
//        ObjectAnimator animtor = ObjectAnimator.ofFloat(view,"rotation",0,180);
//        animtor.setStartDelay(startOffSet);
//        animtor.setDuration(500);
//        animtor.start();
//        view.setPivotX(view.getWidth()/2);
//        view.setPivotY(view.getHeight());
    }

    public static void showView(ViewGroup mLeve3) {
        showView(mLeve3,0);

    }

    public static void showView(ViewGroup view, int startOffSet) {
        RotateAnimation ra = new RotateAnimation(180, 360, view.getWidth()/2, view.getHeight());
        ra.setDuration(500);
        ra.setFillAfter(true);
        ra.setStartOffset(startOffSet);
        view.startAnimation(ra);
        //为了让隐藏的子视图可用
        for (int i = 0; i < view.getChildCount(); i++){
            View children = view.getChildAt(i);
            children.setEnabled(true);
        }
//        view.setPivotX(view.getWidth()/2);
//        view.setPivotY(view.getHeight());
    }
}
