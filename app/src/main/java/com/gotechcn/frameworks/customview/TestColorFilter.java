package com.gotechcn.frameworks.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.gotechcn.frameworks.R;

/**
 * Created by pzm on 2017/10/16
 */

public class TestColorFilter extends View{

    private Paint mPaint = null;



    /**
     * 直接在xml中定义并显示，所以只实现该构造函数即可
     * @param context
     * @param attrs
     */
    public TestColorFilter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        testLightingColorFilter(canvas);
//        testPorterDuffColorFilter(canvas);
        testColorMatrixColorFilter(canvas);
    }





    private void testLightingColorFilter(Canvas canvas){

//        Bitmap bitmapA = BitmapFactory.decodeResource(getResources(), R.mipmap.d);
//        mPaint.setColorFilter(new LightingColorFilter(0xff8800,0x00000));//过滤 B，削弱G
//        canvas.drawBitmap(bitmapA, 0, 0, mPaint);

        Bitmap bitmapA = BitmapFactory.decodeResource(getResources(), R.mipmap.button_blue);
        canvas.drawBitmap(bitmapA, 20, 20, mPaint);

        canvas.translate(20,200);

        mPaint.setColorFilter(new LightingColorFilter(0xffffff,0xff0000)); //增强红色
        canvas.drawBitmap(bitmapA, 0, 0, mPaint);

        canvas.translate(0,200);
        mPaint.setColorFilter(new LightingColorFilter(0xffff00,0x000000)); //过滤蓝色
        canvas.drawBitmap(bitmapA, 0, 0, mPaint);
    }


    private void testPorterDuffColorFilter(Canvas canvas){

        Bitmap bitmapA = BitmapFactory.decodeResource(getResources(), R.mipmap.composeshader_bg);
        canvas.drawBitmap(bitmapA, 20, 20, mPaint);
        canvas.translate(20,bitmapA.getHeight()+100);

        mPaint.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.ADD));//叠加红色
        canvas.drawBitmap(bitmapA, 0, 0, mPaint);


    }
    private void testColorMatrixColorFilter(Canvas canvas){

        Bitmap bitmapA = BitmapFactory.decodeResource(getResources(), R.mipmap.composeshader_bg);
        canvas.drawBitmap(bitmapA, 20, 20, mPaint);
        canvas.translate(20,bitmapA.getHeight()+100);


        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                1, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 0.5f, 0,
        });
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmapA, 0, 0, mPaint);

    }
}
