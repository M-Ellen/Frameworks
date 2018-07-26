package com.gotechcn.frameworks.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by pzm on 2017/10/16
 */

public class TestPathEffec extends View{

    private Paint mPaint = null;
    private PathEffect mPathEffect = null;
    private Path mPath = null;
    private float mPhase = 0;


    public TestPathEffec(Context context) {
        this(context, null);
    }

    /**
     * 直接在xml中定义并显示，所以只实现该构造函数即可
     * @param context
     * @param attrs
     */
    public TestPathEffec(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        mPath = makeFollowPath();

    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //改变偏移量，是路径流动起来
        mPhase += 2;
        postInvalidate();

//        canvas.translate(0,20);
//        defulatPath(canvas);
//
//        canvas.translate(0,100);
//        testCornerPathEffect(canvas);

        canvas.translate(0,100);
        testDashPathEffect(canvas);
//        canvas.translate(0,200);
//        testDashPathEffect1(canvas);

        canvas.translate(0,100);
        testPathDashPathEffect(canvas);
//        canvas.translate(0,100);
//        testPathDashPathEffectStyle(canvas);

//        canvas.translate(0,100);
//        testDiscretePathEffect(canvas);
//
//        canvas.translate(0,100);
//        testSumPathEffect(canvas);
//
//        canvas.translate(0,100);
//        testComposePathEffect(canvas);


    }

    /**
     * 默认路径
     * @param canvas
     */
    private void defulatPath(Canvas canvas){
        mPaint.setColor(Color.BLACK);
        mPaint.setPathEffect(null);
        canvas.drawPath(mPath, mPaint);
    }

    /**
     * CornerPathEffect 圆角路径
     * @param canvas
     */
    private void testCornerPathEffect (Canvas canvas){
        mPathEffect = new CornerPathEffect(30);
        mPaint.setColor(Color.RED);
        mPaint.setPathEffect(mPathEffect);
        canvas.drawPath(mPath, mPaint);
    }


    /**
     * DashPathEffect 虚线路径
     * @param canvas
     */
    private void testDashPathEffect (Canvas canvas){
        mPathEffect = new DashPathEffect(new float[]{20f,10f,40f,15f},mPhase);
        mPaint.setColor(Color.BLUE);
        mPaint.setPathEffect(mPathEffect);
        canvas.drawPath(mPath, mPaint);

    }

    /**
     * 虚线路径
     * @param canvas
     */
    private void testDashPathEffect1 (Canvas canvas){
        mPathEffect = new DashPathEffect(new float[]{50f,10f,100f,100f},mPhase);
        mPaint.setColor(Color.RED);
        mPaint.setPathEffect(mPathEffect);
        canvas.drawLine(20,0,400,0, mPaint);

        mPathEffect = new DashPathEffect(new float[]{50f,10f,100f,100f},40);//设置40的偏移量
        mPaint.setColor(Color.RED);
        mPaint.setPathEffect(mPathEffect);
        canvas.translate(0,100);
        canvas.drawLine(20,0,400,0, mPaint);
    }

    /**
     * PathDashPathEffect 有形状的虚线路径
     * @param canvas
     */
    private void testPathDashPathEffect (Canvas canvas){
        //绘制形状类型
        Path path = new Path();
        path.moveTo(0,0);
        path.lineTo(0,10);
        path.lineTo(10,10);
        path.close();
        mPathEffect = new PathDashPathEffect(path,10,mPhase, PathDashPathEffect.Style.TRANSLATE);
        mPaint.setColor(Color.GREEN);
        mPaint.setPathEffect(mPathEffect);
        canvas.drawPath(mPath, mPaint);
    }

    /**
     * 有形状的虚线路径
     * @param canvas
     */
    private void testPathDashPathEffectStyle (Canvas canvas){
        Path path = new Path();
        path.moveTo(30,0);
        path.lineTo(0,30);
        path.lineTo(60,30);
        path.close();
//        mPathEffect = new PathDashPathEffect(path,45,0, PathDashPathEffect.Style.TRANSLATE);
//        mPathEffect = new PathDashPathEffect(path,45,0, PathDashPathEffect.Style.ROTATE);
        mPathEffect = new PathDashPathEffect(path,45,0, PathDashPathEffect.Style.MORPH);
        mPaint.setColor(Color.YELLOW);
        mPaint.setPathEffect(mPathEffect);
        canvas.drawCircle(300,300,200, mPaint);
    }



    /**
     * DiscretePathEffect 离散路径
     * @param canvas
     */
    private void testDiscretePathEffect (Canvas canvas){
        mPathEffect = new DiscretePathEffect(7,10);
        mPaint.setColor(Color.CYAN);
        mPaint.setPathEffect(mPathEffect);
        canvas.drawPath(mPath, mPaint);
    }

    /**
     * SumPathEffect
     * @param canvas
     */
    private void testSumPathEffect (Canvas canvas){

        PathEffect pathEffect1 = new DiscretePathEffect(12,6);
        PathEffect pathEffect2 = new DashPathEffect(new float[]{20f,10f,40f,15f},mPhase);

        mPathEffect = new SumPathEffect(pathEffect1,pathEffect2);
        mPaint.setColor(Color.MAGENTA);
        mPaint.setPathEffect(mPathEffect);
        canvas.drawPath(mPath, mPaint);
    }


    /**
     * ComposePathEffect
     * @param canvas
     */
    private void testComposePathEffect (Canvas canvas){

        PathEffect pathEffect1 = new DiscretePathEffect(12,6);
        PathEffect pathEffect2 = new DashPathEffect(new float[]{20f,10f,40f,15f},mPhase);

        mPathEffect = new ComposePathEffect(pathEffect1,pathEffect2);
        mPaint.setColor(Color.MAGENTA);
        mPaint.setPathEffect(mPathEffect);
        canvas.drawPath(mPath, mPaint);
    }


    /**
     * 随机获取路径
     * @return
     */
    private static Path makeFollowPath() {
        Path p = new Path();
        p.moveTo(0, 0);
        for (int i = 1; i <= 30; i++) {
            p.lineTo(i*20, (float)Math.random() * 100);
        }
        return p;
    }

}
