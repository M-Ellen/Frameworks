package com.gotechcn.frameworks.customview.Drawable;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.gotechcn.frameworks.R;

/**
 * @author pzm on 2018/7/24
 */

public class TestColorMatrix extends View{


    private Paint mPaint;
    private ColorMatrix mColorMatrix;
    private Bitmap mBitmap;

    public TestColorMatrix(Context context) {
        this(context,null);
    }

    public TestColorMatrix(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
//        mBitmap = Bitmap.createBitmap(700, 900, Bitmap.Config.ARGB_8888);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.timg);
//        mCanvas = new Canvas(mBitmap);


        mColorMatrix = new ColorMatrix(new float[]{
                1,0,0,0,0,
                0,1,0,0,0,
                0,0,1,0,0,
                0,0,0,1,0,
        });

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 直接改变数值值
         */
//        initColorMatrix(canvas);

        /**
         * 通过ColorMatrix函数改变
         */
//        testColorMatrixFunction(canvas);


        /**
         * 一些常用的效果
         */
        testCommonEffects(canvas);

    }


    private void initColorMatrix(Canvas canvas){

        Rect rect = new Rect(0,0,300,300 * mBitmap.getHeight() / mBitmap.getWidth());
        /**
         *  默认
         */
        mColorMatrix = new ColorMatrix(new float[]{
                1,0,0,0,0,
                0,1,0,0,0,
                0,0,1,0,0,
                0,0,0,1,0,
        });

        mPaint.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
        canvas.drawBitmap(mBitmap, null, rect, mPaint );

        /**
         * 红色通道
         */
        mColorMatrix = new ColorMatrix(new float[]{
                1,0,0,0,0,
                0,0,0,0,0,
                0,0,0,0,0,
                0,0,0,1,0,
        });

        canvas.translate(320,0);
        mPaint.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
        canvas.drawBitmap(mBitmap, null, rect, mPaint );


        /**
         * 绿色通道
         */
        mColorMatrix = new ColorMatrix(new float[]{
                0,0,0,0,0,
                0,1,0,0,0,
                0,0,0,0,0,
                0,0,0,1,0,
        });

        canvas.translate(-320,320);
        mPaint.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
        canvas.drawBitmap(mBitmap, null, rect, mPaint );

        /**
         * 蓝色通道
         */
        mColorMatrix = new ColorMatrix(new float[]{
                0,0,0,0,0,
                0,0,0,0,0,
                0,0,1,0,0,
                0,0,0,1,0,
        });
        canvas.translate(320,0);
        mPaint.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
        canvas.drawBitmap(mBitmap, null, rect, mPaint );

        /**
         * 色彩透明度
         */
        mColorMatrix = new ColorMatrix(new float[]{
                1,0,0,0,0,
                0,1,0,0,0,
                0,0,1,0,0,
                0,0,0,0.5f,0,
        });

        canvas.translate(-320,320);
        mPaint.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
        canvas.drawBitmap(mBitmap, null, rect, mPaint );

        /**
         * 色彩偏移（饱和度）
         */
        mColorMatrix = new ColorMatrix(new float[]{
                1,0,0,0,0,
                0,1,0,0,80,
                0,0,1,0,0,
                0,0,0,1,0,
        });
        canvas.translate(320,0);
        mPaint.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
        canvas.drawBitmap(mBitmap, null, rect, mPaint );

        /**
         * 色彩缩放（亮度）
         */
        mColorMatrix = new ColorMatrix(new float[]{
                1.3f,0,0,0,0,
                0,1.3f,0,0,0,
                0,0,1.3f,0,0,
                0,0,0,1,0,
        });
        canvas.translate(-320,320);
        mPaint.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
        canvas.drawBitmap(mBitmap, null, rect, mPaint );

        /**
         * 色彩旋转（色调）
         */
        mColorMatrix = new ColorMatrix(new float[]{
                0.866f,0.5f,0,0,0,
                -0.5f,0.866f,0,0,0,
                0,0,1.3f,0,0,
                0,0,0,1,0,
        });

        canvas.translate(320,0);
        mPaint.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
        canvas.drawBitmap(mBitmap, null, rect, mPaint );

//        /**
//         * 色彩反相/反转
//         */
//        mColorMatrix = new ColorMatrix(new float[]{
//                -1,0,0,0,255,
//                0,-1,0,0,255,
//                0,0,-1,0,255,
//                0,0,0,1,0,
//        });


    }

    /**
     * 测试ColorMatrix类的函数
     */
    private void testColorMatrixFunction(Canvas canvas){

        /**
         * 初始化
         */
//        testInit();

        /**
         * 重置
         */
//        testReset();


        /**
         * 设置整体的饱和度
         */
        testSetSaturation(canvas);

        /**
         * 设置色彩缩放（亮度）
         */
//        testSetScale(canvas);

        /**
         * 设置色彩旋转（色调）
         */
//        testSetRotate(canvas);

        /**
         * 其他少用的函数
         */
//        testOther(canvas);

    }

    private void testInit(Canvas canvas){
        mColorMatrix = new ColorMatrix();
        mColorMatrix.set(new float[]{
                1,0,0,0,0,
                0,1,0,0,0,
                0,0,1,0,0,
                0,0,0,1,0,
        });
        mPaint.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
        canvas.drawBitmap(mBitmap, 0, 0, mPaint );
    }
    private void testReset(Canvas canvas){
        mColorMatrix.reset();
        mPaint.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
        canvas.drawBitmap(mBitmap, 0, 0, mPaint );
    }

    private void testSetSaturation(Canvas canvas){
        mColorMatrix.reset();
        mColorMatrix.setSaturation(5);
        mPaint.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
        canvas.drawBitmap(mBitmap, 0, 0, mPaint );
    }


    private void testSetScale(Canvas canvas){

        mColorMatrix.reset();
        float scale = 1.2f;
        mColorMatrix.setScale(scale, scale, scale, 1);
        mPaint.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
        canvas.drawBitmap(mBitmap, 0, 0, mPaint );
    }


    private void testSetRotate(Canvas canvas){

        mColorMatrix.reset();
        mColorMatrix.setRotate(1,30);
        mPaint.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
        canvas.drawBitmap(mBitmap, 0, 0, mPaint );
    }

    private void testOther(Canvas canvas){
        ColorMatrix colorMatrixA = new ColorMatrix(new float[]{
                1,0,0,0,0,
                0,1,0,0,0,
                0,0,1,0,0,
                0,0,0,1,0,
        });

        ColorMatrix colorMatrixB = new ColorMatrix(new float[]{
                1,0,0,0,0,
                0,1,0,0,0,
                0,0,1,0,0,
                0,0,0,1,0,
        });

        mColorMatrix.setConcat(colorMatrixA, colorMatrixB);
//
//        mColorMatrix.preConcat(colorMatrixA);
//
//        mColorMatrix.postConcat(colorMatrixA);


        /**
         * 格式转换
         */

//        mColorMatrix.reset();
//        mColorMatrix.setRGB2YUV();
//        mColorMatrix.setYUV2RGB();
    }


    /**
     * 一些常用的效果
     */

    private void testCommonEffects(Canvas canvas){

        Rect rect = new Rect(0,0,300,300 * mBitmap.getHeight() / mBitmap.getWidth());
        /**
         *  默认
         */
        mColorMatrix = new ColorMatrix(new float[]{
                1,0,0,0,0,
                0,1,0,0,0,
                0,0,1,0,0,
                0,0,0,1,0,
        });

        mPaint.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
        canvas.drawBitmap(mBitmap, null, rect, mPaint );

        /**
         * 灰白效果
         */
        mColorMatrix = new ColorMatrix(new float[]{
                0.33f,0.59f,0.11f,0,0,
                0.33f,0.59f,0.11f,0,0,
                0.33f,0.59f,0.11f,0,0,
                0.33f,0.59f,0.11f,0,0,
        });

        canvas.translate(320,0);
        mPaint.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
        canvas.drawBitmap(mBitmap, null, rect, mPaint );


        /**
         *反转效果
         */
        mColorMatrix = new ColorMatrix(new float[]{
                -1,0,0,0,255,
                0,-1,0,0,255,
                0,0,-1,0,255,
                0,0,0,1,0,
        });

        canvas.translate(-320,320);
        mPaint.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
        canvas.drawBitmap(mBitmap, null, rect, mPaint );

        /**
         * 怀旧效果
         */
        mColorMatrix = new ColorMatrix(new float[]{
                1/2f,1/2f,1/2f,0,0,
                1/3f,1/3f,1/3f,0,0,
                1/4f,1/4f,1/4f,0,0,
                0,0,0,1,0,
        });
        canvas.translate(320,0);
        mPaint.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
        canvas.drawBitmap(mBitmap, null, rect, mPaint );

        /**
         * 去色效果
         */
        mColorMatrix = new ColorMatrix(new float[]{
                1.5f,1.5f,1.5f,0,-1,
                1.5f,1.5f,1.5f,0,-1,
                1.5f,1.5f,1.5f,0,-1,
                0,0,0,1,0,
        });

        canvas.translate(-320,320);
        mPaint.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
        canvas.drawBitmap(mBitmap, null, rect, mPaint );

        /**
         * 反色效果 （红 绿交换）
         */
        mColorMatrix = new ColorMatrix(new float[]{
                0,1,0,0,0,
                1,0,0,0,0,
                0,0,1,0,0,
                0,0,0,1,0,
        });
        canvas.translate(320,0);
        mPaint.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
        canvas.drawBitmap(mBitmap, null, rect, mPaint );

    }

}
