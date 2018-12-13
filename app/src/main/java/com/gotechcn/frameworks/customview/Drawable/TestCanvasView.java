package com.gotechcn.frameworks.customview.Drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by pzm on 2017/10/16
 * 测试Canvas的API
 */

public class TestCanvasView extends View{

    private Paint mPaint = null;
    private Paint mTestPaint = null;

    public TestCanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        mTestPaint.setTextSize(75);
//        canvas.drawText("原点为(50,50)的文本", 50, 50, mTestPaint);
//
//        mTestPaint.setTextSize(40);
//        canvas.drawText("在屏幕中央的文本", getWidth()/2, getHeight()/2, mTestPaint);
//
//        mTestPaint.setStrokeWidth(20);
//        mTestPaint.setColor(Color.RED);
//        canvas.drawPoint(50, 50, mTestPaint);
//
//        mTestPaint.setStrokeWidth(4);
//        canvas.drawLine(0, getHeight()/2, getWidth(), getHeight()/2,mTestPaint);
//        canvas.drawLine(getWidth()/2, 0, getWidth()/2, getHeight(),mTestPaint);




        testPaint(canvas);

        /**
         * 画文本
         */
//        canvas.drawText("Android", getWidth()/2, getHeight()/2, mTestPaint);

        /**
         * 画圆
         * 构造函数：drawCircle(float cx, float cy, float radius, @NonNull Paint paint)
         * cx 圆心在X轴方法的坐标
         * cy 圆心在Y轴方法的坐标
         * radius 圆的半径
         * paint  画笔
         */
//        canvas.drawCircle(getWidth() / 10 + 10, getWidth() / 10 + 10, getWidth() / 10, mPaint);

        /**
         *画圆角矩形
         *构造函数：drawOval(float left, float top, float right, float bottom, @NonNull Paint paint)
         *
         */
//        canvas.drawOval(10, getWidth() / 5 + 20, getWidth() / 5 + 10, getWidth() * 2 / 5 + 20, mPaint);

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
//        canvas.drawRect(10, getWidth() / 5 + 20, getWidth() / 5 + 10, getWidth() * 2 / 5 + 20, mPaint);


    }

    private void init(){

        mPaint = new Paint();
        mPaint.setAntiAlias(true);//设置抗锯齿
        mPaint.setDither(true);   //设置防抖动
        mPaint.setStrokeWidth(4);  //设置画笔的笔触宽度
        mPaint.setColor(Color.GREEN); //设置画笔颜色
        mPaint.setStyle(Paint.Style.STROKE); //设置样式
    }

    /**
     * 测试 paint API
     */
    private void testPaint(Canvas canvas){
        mTestPaint = new Paint();

//        testDrawText(canvas);

//        testTextOther(canvas);

//        testTextAlign(canvas);

//        testStyle(canvas);

        testStroke(canvas);

//        testAntiAlias(canvas);


//        mTestPaint.setDither(true);   //设置防抖动

//        mTestPaint.setStyle(Paint.Style.STROKE); //设置样式




    }

    private void testStyle(Canvas canvas) {
        mTestPaint.reset();
        mTestPaint.setColor(Color.BLUE);
        mTestPaint.setStrokeWidth(40);

        mTestPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(150, 150, 100, mTestPaint);

        mTestPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(150, 450, 100, mTestPaint);

        mTestPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(150, 750, 100, mTestPaint);
    }

    /**
     * 抗锯齿
     */
    private void setDither(Canvas canvas){
        //重置画笔
        mTestPaint.reset();
        mTestPaint.setTextSize(120);
        mTestPaint.setColor(Color.RED);
        mTestPaint.setFakeBoldText(true);

        canvas.drawText("没有抗锯齿", 50, 400, mTestPaint);

        mTestPaint.setAntiAlias(true);//设置抗锯齿
        canvas.drawText("设置抗锯齿", 50, 600, mTestPaint);
    }

    /**
     * 抗锯齿
     */
    private void testAntiAlias(Canvas canvas){
        //重置画笔
        mTestPaint.reset();
        mTestPaint.setTextSize(120);
        mTestPaint.setColor(Color.RED);
        mTestPaint.setFakeBoldText(true);

        canvas.drawText("没有抗锯齿", 50, 400, mTestPaint);

        mTestPaint.setAntiAlias(true);//设置抗锯齿
        canvas.drawText("设置抗锯齿", 50, 600, mTestPaint);
    }


    /**
     *与笔触有关的api
     */
    private void testStroke(Canvas canvas) {
        //重置画笔
        mTestPaint.reset();
        canvas.restore();

        //设置画笔的笔触宽度
        mTestPaint.setStrokeWidth(4);
        canvas.drawLine(100, 30, 300, 30, mTestPaint);
        mTestPaint.setStrokeWidth(10);
        canvas.drawLine(100, 80, 300, 80, mTestPaint);
        mTestPaint.setStrokeWidth(30);
        canvas.drawLine(100, 130, 300, 130, mTestPaint);

        //画笔的笔帽风格
        mTestPaint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawLine(100, 280, 300, 280, mTestPaint);
        mTestPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(100, 330, 300, 330, mTestPaint);
        mTestPaint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawLine(100, 380, 300, 380, mTestPaint);

        //重置画笔
        mTestPaint.reset();
        mTestPaint.setStrokeWidth(20);
        mTestPaint.setStrokeCap(Paint.Cap.ROUND);
        mTestPaint.setStyle(Paint.Style.STROKE); //设置画笔风格，只描边

        //画笔转弯处的连接风格
        Path path  = new Path();
        path.moveTo(100,480);
        path.lineTo(250,480);
        path.lineTo(250,580);
        mTestPaint.setStrokeJoin(Paint.Join.MITER);
        canvas.drawPath(path, mTestPaint);

        Path path2  = new Path();
        path2.moveTo(300,480);
        path2.lineTo(450,480);
        path2.lineTo(450,580);
        mTestPaint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawPath(path2, mTestPaint);

        Path path3  = new Path();
        path3.moveTo(500,480);
        path3.lineTo(650,480);
        path3.lineTo(650,580);
        mTestPaint.setStrokeJoin(Paint.Join.BEVEL);
        canvas.drawPath(path3, mTestPaint);

        //设置画笔的倾斜度
        mTestPaint.reset();
        mTestPaint.setStrokeWidth(20);
        mTestPaint.setStyle(Paint.Style.STROKE); //设置画笔风格，只描边

        Path path4  = new Path();
        path4.moveTo(100,680);
        path4.lineTo(250,680);
        path4.lineTo(100,780);
        path4.lineTo(100,670);
        canvas.drawPath(path4, mTestPaint);

        Path path6  = new Path();
        path6.moveTo(300,680);
        path6.lineTo(450,680);
        path6.lineTo(300,780);
        path6.lineTo(300,670);
        mTestPaint.setStrokeMiter(0);
        canvas.drawPath(path6, mTestPaint);

        Path path5  = new Path();
        mTestPaint.setStrokeMiter(2.3f);
        path5.moveTo(500,680);
        path5.lineTo(650,680);
        path5.lineTo(500,780);
        path5.lineTo(500,670);
        canvas.drawPath(path5, mTestPaint);

    }


    /**
     * 与文本相关的API
     */
    private void testDrawText(Canvas canvas){

        canvas.translate(getWidth()/10, 50);//先将画布平移，方便观察

        canvas.drawText("默认的文本", 50, 0, mTestPaint);

        mTestPaint.setTextSize(40);
        canvas.drawText("有大小的文本", 50, 50, mTestPaint);

        mTestPaint.setColor(Color.RED);
        canvas.drawText("有颜色的文本", 50, 100, mTestPaint);

        //重置画笔
        mTestPaint.reset();
        mTestPaint.setTextSize(40);

        //设置下划线
        mTestPaint.setUnderlineText(true);  //设置下划线
        canvas.drawText("有下划线的文本", 50, 150, mTestPaint);

        //重置画笔
        mTestPaint.reset();
        mTestPaint.setTextSize(40);

        //设置删除线
        mTestPaint.setStrikeThruText(true);
        canvas.drawText("有删除线的文本", 50, 200, mTestPaint);

        //重置画笔
        mTestPaint.reset();
        mTestPaint.setTextSize(40);

        //设置粗体字
        mTestPaint.setFakeBoldText(true);
        canvas.drawText("粗体的文本", 50, 250, mTestPaint);

        //重置画笔
        mTestPaint.reset();
        mTestPaint.setTextSize(40);

        //设置文本阴影
        mTestPaint.setShadowLayer(5, 5, 5, Color.YELLOW);
        canvas.drawText("阴影的文本", 50, 300, mTestPaint);

        //重置画笔
        mTestPaint.reset();
        mTestPaint.setTextSize(40);

        //设置文本水平倾斜
        mTestPaint.setTextSkewX(-0.5f);
        canvas.drawText("斜体的文本", 50, 350, mTestPaint);

        //重置画笔
        mTestPaint.reset();
        mTestPaint.setTextSize(40);

        //设置文本水平倾斜
        mTestPaint.setTextScaleX(2);
        canvas.drawText("放大的文本", 50, 400, mTestPaint);

        //重置画笔
        mTestPaint.reset();
        mTestPaint.setTextSize(40);

        //设置文本字体样式
        Typeface typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC);
        mTestPaint.setTypeface(typeface);
        canvas.drawText("带有样式的文本", 50, 450, mTestPaint);

        //重置画笔
        mTestPaint.reset();
        mTestPaint.setTextSize(40);

        mTestPaint.setLetterSpacing(1);
        canvas.drawText("有间距的文本", 50, 500, mTestPaint);

//        mTestPaint.setTextAlign(Paint.Align.LEFT);
//        canvas.drawText("文本左对齐", 50, 150, mTestPaint);
//        mTestPaint.setTextAlign(Paint.Align.CENTER);
//        canvas.drawText("文本居中对齐", 50, 200, mTestPaint);
//        mTestPaint.setTextAlign(Paint.Align.RIGHT);
//        canvas.drawText("文本右对齐", 50, 250, mTestPaint);
    }

    private void testTextAlign(Canvas canvas){

        canvas.translate(getWidth()/2, 200);//先将画布平移，方便观察

        mTestPaint.reset();
        mTestPaint.setTextSize(60);
        mTestPaint.setStrokeWidth(8);

        mTestPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("左对齐的文本", 0, 0, mTestPaint);

        mTestPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("居中对齐的文本", 0, 100, mTestPaint);

        mTestPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("右对齐的文本", 0, 200, mTestPaint);
    }

    private void testTextOther(Canvas canvas){

        /**
         * 常见的2个问题
         */
//        mTestPaint.setTextSize(75);
//        canvas.drawText("原点为(50,50)的文本", 50, 50, mTestPaint);
//
//        mTestPaint.setTextSize(60);
//        mTestPaint.setTextAlign(Paint.Align.CENTER);
//        canvas.drawText("在屏幕中央的文本", getWidth()/2, getHeight()/2, mTestPaint);
//
//        mTestPaint.setStrokeWidth(20);
//        mTestPaint.setColor(Color.RED);
//        canvas.drawPoint(50, 50, mTestPaint);
//
//        mTestPaint.setStrokeWidth(4);
//        canvas.drawLine(0, getHeight()/2, getWidth(), getHeight()/2,mTestPaint);
//        canvas.drawLine(getWidth()/2, 0, getWidth()/2, getHeight(),mTestPaint);

        /**
         * 解决这2个问题
         */
        mTestPaint.setTextSize(70);
        Paint.FontMetrics fontMetrics = mTestPaint.getFontMetrics();
        float top = fontMetrics.top;
        canvas.drawText("原点为(50,top)的文本", 50, Math.abs(top), mTestPaint);//将Y值改为top

        mTestPaint.setTextSize(60);
        mTestPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics1 = mTestPaint.getFontMetrics();
        canvas.drawText("在屏幕中央的文本", getWidth()/2, getHeight()/2 + Math.abs((fontMetrics1.ascent) + (fontMetrics1.descent))/2, mTestPaint);


        /**
         * 对于第2个问题的另一种解决
         */
//        mTestPaint.setTextSize(60);
//        String text = "在屏幕中央的文本";
//        Rect textRect = new Rect();
//        mTestPaint.getTextBounds(text, 0, text.length(), textRect);
//        canvas.drawText(text, getWidth()/2 - textRect.width()/2, getHeight()/2 + textRect.height()/2, mTestPaint);


        mTestPaint.setColor(Color.RED);
        mTestPaint.setStrokeWidth(4);

        canvas.drawLine(50, Math.abs(top), getWidth(), Math.abs(top),mTestPaint);//第一个问题的参考线
        canvas.drawLine(0, getHeight()/2, getWidth(), getHeight()/2,mTestPaint);//第二个问题的参考线
        canvas.drawLine(getWidth()/2, 0, getWidth()/2, getHeight(),mTestPaint);

        /**
         * 绘制4线格
         */
//        int baseLineY = 300;
//        int baseLineX = 0 ;
//
//        mTestPaint.setTextSize(80);
//        mTestPaint.setStrokeWidth(4);
//        mTestPaint.setTextAlign(Paint.Align.CENTER);
//        canvas.drawText("abcgjABCGJāÀ", getWidth()/2, baseLineY, mTestPaint);
//
//        Paint.FontMetrics fontMetrics = mTestPaint.getFontMetrics();
//        float ascent = baseLineY + fontMetrics.ascent;
//        float descent = baseLineY + fontMetrics.descent;
//        float top = baseLineY + fontMetrics.top;
//        float bottom = baseLineY + fontMetrics.bottom;
//        //画基线
//        mTestPaint.setColor(Color.RED);
//        canvas.drawLine(baseLineX, baseLineY, getWidth(), baseLineY, mTestPaint);
//
//        //画top
//        mTestPaint.setColor(Color.BLUE);
//        canvas.drawLine(baseLineX, top, getWidth(), top, mTestPaint);
//
//        //画ascent
//        mTestPaint.setColor(Color.GREEN);
//        canvas.drawLine(baseLineX, ascent, getWidth(), ascent, mTestPaint);
//
//        //画descent
//        mTestPaint.setColor(Color.YELLOW);
//        canvas.drawLine(baseLineX, descent, getWidth(), descent, mTestPaint);
//
//        //画bottom
//        mTestPaint.setColor(Color.RED);
//        canvas.drawLine(baseLineX, bottom, getWidth(), bottom, mTestPaint);
    }
}
