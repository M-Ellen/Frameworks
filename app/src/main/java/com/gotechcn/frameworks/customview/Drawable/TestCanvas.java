package com.gotechcn.frameworks.customview.Drawable;


import com.gotechcn.frameworks.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by pzm on 2017/10/16
 * 测试Canvas的API
 */

public class TestCanvas extends View{

    private Paint mPaint = null;

    /**
     * 直接在xml中定义，所以实现该操作函数即可
     * @param context
     * @param attrs
     */
    public TestCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        testDrawPicture();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        //4.显示Picture图片
//        canvas.drawPicture(mPicture);
        testDrawArc(canvas);
//        testDrawRect(canvas);
    }

    /**
     * test draw color
     * @param canvas
     */
    private void testDrawColor(Canvas canvas){
//        canvas.drawColor(Color.RED);
        canvas.drawColor(Color.parseColor("#66ff66ff"));
//        canvas.drawRGB(200, 0, 0);
//        canvas.drawARGB(100, 0, 0, 100);
    }

    private void testDrawPoint(Canvas canvas){
        mPaint.setStrokeWidth(20);//设置画笔的大小
//        canvas.drawPoint(200,200,mPaint);
        //绘制4个点
        canvas.drawPoints(new float[]{300,300, 300,350, 300,400, 300,450}, mPaint);
        //从数组第3个数开始，取出4个数值，再绘制点
        canvas.translate(200,0);
        canvas.drawPoints(new float[]{300,300, 300,350, 300,400, 300,450}, 4,4, mPaint);
    }

    private void testDrawLine(Canvas canvas){
        mPaint.setStrokeWidth(10);
        canvas.drawLine(100,100,300,300,mPaint);

        canvas.drawLines(new float[]{250,350, 300,350, 350,350, 400,350, 450,350, 500,350},mPaint);

//        canvas.drawLines(new float[]{250,350, 300,350, 350,350, 400,350, 450,350, 500,350},3,8, mPaint);
    }

    private void testDrawRect(Canvas canvas){

        int save = canvas.save();
        canvas.restoreToCount(0);
        canvas.getSaveCount();
        int save00 = canvas.save();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(100,100,500,400,mPaint);

        int save1 = canvas.save();
        mPaint.setColor(Color.RED);
        canvas.drawRect(new Rect(200,200,600,500), mPaint);

        int save2 = canvas.save();
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(new RectF(300,300,700,600), mPaint);
        int save3 = canvas.save();
    }

    private void testDrawRoundRect(Canvas canvas){
        canvas.drawRoundRect(100,100,500,400,40,60,mPaint);
    }

    private void testDrawOval(Canvas canvas){
        canvas.drawOval(100,100,600,400,mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);
        canvas.drawRect(100,100,600,400,mPaint);
    }

    private void testDrawCircle(Canvas canvas){
        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(300,400,200,mPaint);
    }

    private void testDrawArc(Canvas canvas){

        canvas.drawArc(50,50,400,200,0,120,true,mPaint);//使用中心点
        canvas.translate(300,0);
        canvas.drawArc(50,50,400,200,0,120,false,mPaint); //不使用中心点

        //注意：画出来的效果 与画笔Paint的填充效果有关。
        canvas.translate(-300,300);
        mPaint.setStyle(Paint.Style.STROKE);//设置画笔填充模式为描边

        canvas.drawArc(50,50,400,200,-90,180,true,mPaint);//使用中心点
        canvas.translate(300,0);
        canvas.drawArc(50,50,400,200,-90,180,false,mPaint);//不使用中心点
    }


    private void testDrawTextOnPath(Canvas canvas){
        //设置画笔的属性
        mPaint.setTextSize(40);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);

        //根据路径path 绘制矩形
        Path path = new Path();
        path.addRect(100,100,500,400,Path.Direction.CW);
        canvas.drawPath(path,mPaint);

        //根据path的路径，绘制文本
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
        canvas.drawTextOnPath("Hello World !厉害了，我的国！￥#@&*$",path,30,30,mPaint);

    }

    /**
     * 暂时没有发现有什么效果
     * @param canvas
     */
    private void testDrawTextRun(Canvas canvas){
        mPaint.setTextSize(40);
        mPaint.setStrokeWidth(5);
//        mPaint.setStyle(Paint.Style.STROKE);

//        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
        char [] chars = {'a','b','e','f','g','h','i','j','k','l','m','n','o','p','q'};
        canvas.drawTextRun(chars,0,chars.length,3,10,300,300,false,mPaint);

    }


    /**
     * 绘制Bitmap图片
     */
    private void testDrawBitmap(Canvas canvas){

        //通过BitmapFactory 从资源文件获取图片，
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.d);
        //原始图片，在（0，0）开始显示
        canvas.drawBitmap(bitmap,0,0, mPaint);

        //剪切图片，并指定显示区域, 这时dst > src  会拉伸图片
        Rect src = new Rect(100, 0, 400, bitmap.getHeight());
        Rect dst = new Rect(0, 500, getWidth(), 800);
        canvas.drawBitmap(bitmap,src,dst, mPaint);

//        float[] verts;
//        int[] colors;
//        canvas.drawBitmapMesh(bitmap6, 50, 40, verts, 10, colors, 10, mPaint);

    }


    /**
     * 绘制Picture图片
     */
    Picture mPicture = new Picture();//一张空白的Picture

    private void testDrawPicture(){

        //1.开始录制
        Canvas c = mPicture.beginRecording(getWidth(), getHeight());

        //2.绘制内容
        mPaint.setStyle(Paint.Style.STROKE);
        c.drawCircle(300,300,100,mPaint);

        mPaint.setTextSize(20);
        c.drawText("画一个圈圈诅咒你",200,450,mPaint);

        //3.结束录制
        mPicture.endRecording();

    }


    /**
     * 画布的平移
     */
    private void testTranslate(Canvas canvas){

    }

    /**
     * 画布的旋转
     */
    private void testRotate(Canvas canvas){

        /**
         * 注意：先将画布移动到中间点，不然使用旋转后，有时会看不到绘制的效果，显示在屏幕外
         */

        mPaint.setStrokeWidth(10);
        canvas.translate(300,300);//那么，对于旋转来说，相对于屏幕的坐标（300,300）就是画布原点
        canvas.drawLine(0,0,getWidth(),0,mPaint);

        /**
         * 第一种方式
         */
//        canvas.rotate(30);
//        mPaint.setColor(Color.RED);
//        canvas.drawLine(0,0,400,0,mPaint);
//
//        canvas.rotate(60);
//        mPaint.setColor(Color.GREEN);
//        canvas.drawLine(0,0,400,0,mPaint);

        /**
         * 第二种方式
         */
        canvas.rotate(30,getWidth()/2,0);//此次操作，是相对与上一次平移后的画布，不是整个屏幕旋转
        mPaint.setColor(Color.RED);
        canvas.drawLine(0,0,getWidth(),0,mPaint);

        canvas.rotate(60,getWidth()/2,0); //此次操作，是相对与上一次的画布，所以最终旋转了90度
        mPaint.setColor(Color.GREEN);
        canvas.drawLine(0,0,getWidth(),0,mPaint);

    }

    /**
     * 画布的平移
     */
    private void testScale(Canvas canvas){

        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.translate(300,300);
        canvas.drawRect(0,0,200,200,mPaint);


        /**
         * 第一种方式
         */
//        canvas.scale(1.5f, 1.5f);
//        mPaint.setColor(Color.RED);
//        canvas.drawRect(0,0,200,200,mPaint);

        /**
         * 第二种方式
         */
        canvas.scale(-1.5f, -1.5f,100,0);//放大倍数为负数，向反方法缩放
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(0,0,200,200,mPaint);

    }



    /**
     * 画布的错切
     */
    private void testSkew(Canvas canvas){

        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.translate(300,300);
        canvas.drawRect(0,0,200,200,mPaint);

        canvas.skew(1,2);//设置倾斜系数
        mPaint.setColor(Color.RED);
        canvas.drawRect(0,0,200,200,mPaint);

    }

    /**
     * 画布的剪切
     */
    private void testClip(Canvas canvas){

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.d);//定义一涨图片
        /**
         * 使用矩形形状
         */
        canvas.clipRect(100,0,400,300);
        canvas.drawBitmap(bitmap,0,0,mPaint);

        /**
         * 使用Path自定义形状
         */
        Path path = new Path();
        path.moveTo(150,0);
        path.lineTo(250,400);
        path.lineTo(450,400);
        path.lineTo(550,0);
        canvas.clipPath(path);
//        canvas.clipOutPath(path); //clipPath() 剪切区域 取反
        canvas.drawBitmap(bitmap,0,0,mPaint);
    }


    /**
     * 画布的保存与恢复
     */
    private void testSave(Canvas canvas){
        /**
         save()
         ...  //绘制操作
         restore()

         如处理平移、旋转、缩放画布等，如果旋转30°，那么后面的画布一直是旋转的，希望这次旋转不影响后面的操作，那么就可以：

         save();  //保存当前状态
         canvas.rotate(30);
         canvas.drawLine(0,0,400,0,mPaint);
         restore(); //恢复保存的状态
         ......
         */

    }

/**


  获取Canvas的几种方法


   1.通过自定义Bitmap 对象，创建Canvas

    Bitmap b = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
    Canvas c = new Canvas(b);


    2.通过继承View类，重写onDraw(),使用参数中的Canvas（常用）
        一般用于 不需要大量的处理速度或帧率
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    3.通过继承SurfaceView类，并实现SurfaceHolder.Callback方法，然后通过SurfaceHolder来获取Canvas对象
    为应用程序提供辅助线程来绘图，以便应用程序不需要等待系统的View层次结构准备好绘制，性能高。
    一般用于频繁刷新的绘制

    SurfaceHolder surfaceHolder = surfaceView.getHolder();
    Canvas c = surfaceHolder.lockCanvas();

 *
 */


}