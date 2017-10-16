package com.gotechcn.frameworks.Drawables;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by pzm on 2017/10/16
 * 测试Canvas的API
 */

public class TestCanvasView extends View{

    private Paint mPaint = null;

    public TestCanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 画圆
         * 构造函数：drawCircle(float cx, float cy, float radius, @NonNull Paint paint)
         * cx 圆心在X轴方法的坐标
         * cy 圆心在Y轴方法的坐标
         * radius 圆的半径
         * paint  画笔
         */
        canvas.drawCircle(getWidth() / 10 + 10, getWidth() / 10 + 10, getWidth() / 10, mPaint);

        /**
         *画圆角矩形
         *构造函数：drawOval(float left, float top, float right, float bottom, @NonNull Paint paint)
         *
         */
        canvas.drawOval(10, getWidth() / 5 + 20, getWidth() / 5 + 10, getWidth() * 2 / 5 + 20, mPaint);

        /**
         * 画矩形
         *
         * 构造函数：drawRect(float left, float top, float right, float bottom, @NonNull Paint paint)
         * left
         * top
         * right
         * bottom
         *  paint  画笔
         */
        canvas.drawRect(10, getWidth() / 5 + 20, getWidth() / 5 + 10, getWidth() * 2 / 5 + 20, mPaint);


    }

    private void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//设置抗锯齿
        mPaint.setDither(true);   //设置防抖动
        mPaint.setStrokeWidth(4);  //设置画笔的笔触宽度
        mPaint.setColor(Color.GREEN); //设置画笔颜色
        mPaint.setStyle(Paint.Style.STROKE); //设置样式

    }
}
