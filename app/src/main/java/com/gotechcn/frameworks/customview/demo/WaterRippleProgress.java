package com.gotechcn.frameworks.customview.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author pzm on 2018/7/24
 */


public class WaterRippleProgress extends View {

    private int mWithd;
    private int mHeight;

    public WaterRippleProgress(Context context) {
        this(context,null);
    }

    public WaterRippleProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WaterRippleProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWithd = measure(widthMeasureSpec);
        mHeight = measure(heightMeasureSpec);
        setMeasuredDimension(mWithd, mHeight);
    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }



    private int measure(int measureSpec){
        int result = 0;
        int measureSpecMode = MeasureSpec.getMode(measureSpec);
        int measureSpecSize = MeasureSpec.getSize(measureSpec);
        if(measureSpecMode == MeasureSpec.EXACTLY) {
            result = measureSpecSize;
        }else {
            result = 200;
            if(measureSpecMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, measureSpecSize);
            }
        }
        return result;
    }
}
