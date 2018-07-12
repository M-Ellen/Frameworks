package com.gotechcn.frameworks.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by pzm on 2017/10/16
 */

public class testColorFilter extends View{

    private Paint mPaint = null;



    /**
     * 直接在xml中定义并显示，所以只实现该构造函数即可
     * @param context
     * @param attrs
     */
    public testColorFilter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColorFilter(new LightingColorFilter(100,100));
    }
}
