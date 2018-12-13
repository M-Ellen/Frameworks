package com.gotechcn.frameworks.customview.Drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.gotechcn.frameworks.R;

/**
 * @author pzm on 2018/7/26
 */

public class TestMatrix extends View {


    private Paint mPaint;
    private Matrix mMatrix;
    private Bitmap mBitmap;

    public TestMatrix(Context context) {
        this(context, null);
    }

    public TestMatrix(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public TestMatrix(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.timg);
        mMatrix = new Matrix();
        mMatrix.setTranslate(200f,200f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.concat(mMatrix);
//        canvas.translate(200,200);

//        canvas.drawBitmap(mBitmap, mMatrix, mPaint);


        //        canvas.concat(mMatrix);

//        canvas.drawBitmap(mBitmap, 0, 0, mPaint );

        canvas.drawRect(0,0,100,100,mPaint);
    }

    private void testScale(Canvas canvas){

    }
    private void testScale1(Canvas canvas){

    }
    private void testScale2(Canvas canvas){

    }
    private void testScale3(Canvas canvas){

    }
}
