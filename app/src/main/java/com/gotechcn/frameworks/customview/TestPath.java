package com.gotechcn.frameworks.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by pzm on 2017/10/16
 * 测试Canvas的API
 */

public class TestPath extends View{

    private Paint mPaint = null;
    private Path mPath = null;

    /**
     * 直接在xml中定义，所以实现该操作函数即可
     * @param context
     * @param attrs
     */
    public TestPath(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();


    }

    /**
     * 初始化
     */
    private void init(){
        mPaint = new Paint();
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        testOffset(canvas);
    }

    private void testLine(Canvas canvas){

        mPaint.setColor(Color.GREEN);
        Path path1 = new Path();
        path1.lineTo(300,300);
        canvas.drawPath(path1,mPaint);

        /**
         * 设置开始点
         */
        mPaint.setColor(Color.RED);
        Path path2 = new Path();
        path2.moveTo(300,100);
        path2.lineTo(600,400);
        canvas.drawPath(path2,mPaint);

        /**
         * 改变结束点的位置
         */
        mPaint.setColor(Color.BLUE);
        Path path3 = new Path();
        path3.lineTo(300,300);
        path3.setLastPoint(200,400);
        canvas.drawPath(path3,mPaint);

        /**
         * 闭环
         */
        mPaint.setColor(Color.BLACK);
        Path path4 = new Path();
        path4.moveTo(200,600);
        path4.lineTo(300,800);
//        path4.setLastPoint(200,400);
        path4.lineTo(600,900);
        path4.close();
        canvas.drawPath(path4,mPaint);
    }

    /**
     * 测试圆角矩形：自定义每个圆角
     * @param canvas
     */
    private void testRoundRect(Canvas canvas){
        RectF rectF = new RectF(100,100,400,300);
        mPaint.setColor(Color.BLACK);
        mPath.addRoundRect(rectF, 20, 20, Path.Direction.CW);
        canvas.drawPath(mPath,mPaint);

        RectF rectF1 = new RectF(100,400,400,600);
        mPaint.setColor(Color.RED);
        mPath.addRoundRect(rectF1, new float[]{20,20, 30,30, 40,40, 50,50}, Path.Direction.CW);
        canvas.drawPath(mPath,mPaint);
    }

    /**
     * 测试圆弧
     * @param canvas
     */
    private void testArc(Canvas canvas){
        mPaint.setColor(Color.BLACK);
        RectF rectF1 = new RectF(100,400,400,600);
        mPath.lineTo(200,200);
        mPath.arcTo(rectF1, 30,90, false);
        canvas.drawPath(mPath,mPaint);
    }

    /**
     * 测试Offset 方法
     * @param canvas
     */
    private void testOffset(Canvas canvas){
        RectF rectF1 = new RectF(100,400,400,600);


        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        Path path = new Path();
        path.lineTo(200,200);
        path.arcTo(rectF1, 30,90, false);
        canvas.drawPath(path,paint);

        mPaint.setColor(Color.BLACK);
        mPath.lineTo(200,200);
        mPath.arcTo(rectF1, 30,90, false);
//        mPath.offset(300,200); 实现平移
//        mPath.offset(300,200,null); 实现平移，同上
        mPath.offset(300,200,path);//未实现平移，将平移保存在path中
        canvas.drawPath(mPath,mPaint);
//        canvas.drawPath(path,mPaint);
    }


    /**
     * 测试填充的方法
     * @param canvas
     */
    private void testFillType(Canvas canvas){
        mPaint.setColor(Color.BLACK);

        mPath.setFillType(Path.FillType.EVEN_ODD);
        canvas.drawPath(mPath,mPaint);
    }

    /**
     * 测试布尔运算
     * @param canvas
     */
    private void testOp(Canvas canvas){
        mPaint.setColor(Color.BLACK);

        mPath.setFillType(Path.FillType.EVEN_ODD);
        canvas.drawPath(mPath,mPaint);
    }



}
