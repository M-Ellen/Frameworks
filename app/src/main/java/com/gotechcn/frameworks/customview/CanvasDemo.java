package com.gotechcn.frameworks.customview;

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

public class CanvasDemo extends View{

    private Paint mPaint = null;

    /**
     * 直接在xml中定义并显示，所以只实现该构造函数即可
     * @param context
     * @param attrs
     */
    public CanvasDemo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 第一次绘制：绘制矩形
         */
        canvas.drawRect(80,80,200,150,mPaint);//通过draw方法，将内容显示在屏幕上，此次画布的任务已经完成

        /**
         * 第二次绘制：先平移，再绘制圆
         */
        canvas.translate(300,300);//画布还是原来的画布，只是将它平移。内容是在屏幕上的，所以画布平移不影响第一次绘制的内容
        mPaint.setColor(Color.GREEN); //改变画笔的颜色
        canvas.drawCircle(50,50,100,mPaint);//通过draw方法，将内容显示在屏幕上，此次画布的任务已经完成。（这里的坐标多是相对画布而言）
    }
}
