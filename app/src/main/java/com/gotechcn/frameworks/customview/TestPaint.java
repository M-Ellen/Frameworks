package com.gotechcn.frameworks.customview;

import java.util.Locale;

import com.gotechcn.frameworks.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.EmbossMaskFilter;
import android.graphics.LinearGradient;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by pzm on 2017/10/16
 * 测试Paint的API
 */

public class TestPaint extends View{

    private Paint mPaint = null;
    private Paint mTestPaint = null;

    public TestPaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init(){

        mTestPaint = new Paint();
        mPaint = new Paint();
        mPaint.setStrokeWidth(4);  //设置画笔的笔触宽度
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        testInit(canvas);

        testColor(canvas);

//        testEffect(canvas);

//        testText(canvas);
    }



    private void testInit(Canvas canvas) {
//                testFlag(canvas);
    }

    private void testColor(Canvas canvas) {
//        testLinearGradient(canvas);
//        testShadetTile(canvas);
//        testRadialGradient(canvas);
//        testSweepGradient(canvas);
//        testBitmapShader1(canvas);
//        testComposeShader(canvas);
          TestXfermode(canvas);
    }

    private void testEffect(Canvas canvas) {

//        testStyle(canvas);

//        testStroke(canvas);

//        testAntiAlias(canvas);

//        setDither(canvas);

//        mTestPaint.setDither(true);   //设置抖动

//        testPathEffec(canvas);

//        testShadowLayer(canvas);

//        testMaskFilter1(canvas);
//        testMaskFilter(canvas);

//        testEmbossMaskFilter(canvas);
    }


    private void testText(Canvas canvas) {

//        testDrawText(canvas);

//        testTypeface(canvas);

//        testTextAlign(canvas);

//        testFontFeatureSettings(canvas);

//        testTextLocale(canvas);

        testTextPosition(canvas);
//        testTextPos(canvas);

    }


    private void testFlag(Canvas canvas){
        mPaint.setTextSize(20);
        mPaint.setFlags(Paint.UNDERLINE_TEXT_FLAG);//设置带下划线的文本
        canvas.drawText("厉害了，我的国！！！",200,200,mPaint);
        mPaint.setUnderlineText(true);//设置带下划线的文本
    }

    /*********************颜色 start ********************/


    /**
     * 线性渐变
     * @param canvas
     */
    private void testLinearGradient(Canvas canvas){

        /**
         * 两色渐变
         */
        Shader shader = new LinearGradient(100,100,500,500, Color.RED,Color.GREEN,Shader.TileMode.CLAMP);
//       Shader shader = new LinearGradient(100,100,getWidth()-100,300, Color.parseColor("#E91E63"),Color.parseColor("#E91E63"),Shader.TileMode.CLAMP);
        mPaint.setShader(shader);
        canvas.drawRect(100,100,600,500,mPaint);

        /**
         * 多色渐变
         */
        int[] colors = {Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW,Color.MAGENTA};
        float[] position = {0f, 0.2f, 0.4f, 0.6f, 1.0f};
//        Shader shader1 = new LinearGradient(100,850,600,850,colors,position,Shader.TileMode.CLAMP);
//        mPaint.setShader(shader1);
//        canvas.drawRect(100,600,600,1100,mPaint);

        Shader shader1 = new LinearGradient(600,1100,100,600,colors,position,Shader.TileMode.CLAMP);
        mPaint.setShader(shader1);
        canvas.drawRect(100,600,600,1100,mPaint);

    }

    private void testShadetTile(Canvas canvas) {
        int[] colors = {Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW,Color.MAGENTA};
        float[] position = {0f, 0.2f, 0.4f, 0.6f, 1.0f};
        Shader shader1 = new LinearGradient(0,0,getWidth()/2,getHeight()/2,colors,position,Shader.TileMode.CLAMP);
        mPaint.setShader(shader1);
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);

    }


    /**
     * 放射渐变
     * @param canvas
     */
    private void testRadialGradient(Canvas canvas) {
        int[] colors = {Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW,Color.MAGENTA};
        Shader shader1 = new RadialGradient(400,400,200,colors,null,Shader.TileMode.CLAMP);//均匀分布
        mPaint.setShader(shader1);
        canvas.drawCircle(400,400,200,mPaint);

//        //test  TileMode
//        Shader shaderClamp = new RadialGradient(300,300,100,Color.RED,Color.GREEN,Shader.TileMode.MIRROR);
//        mPaint.setShader(shaderClamp);
//        canvas.drawRect(100,100,600,500,mPaint);
    }


    /**
     * 扫描渐变
     * @param canvas
     */
    private void testSweepGradient(Canvas canvas) {
        int[] colors = {Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW,Color.MAGENTA};
        Shader shader1 = new SweepGradient(400,400,colors,null);//均匀分布
        mPaint.setShader(shader1);
        canvas.drawCircle(400,400,200,mPaint); //圆形
//        canvas.drawRect(100,200,600,600,mPaint);矩形

    }


    /**
     * BitmapShader
     */
    private void testBitmapShader(Canvas canvas) {

        /**
         * 将矩形绘制区域大小，设置为bitmap的大小，这里TileMode则没有效果
         * 看起来，和drawBitmap()几乎是一样的
         */
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bitmap);
        Shader shader1 = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.CLAMP);
        mPaint.setShader(shader1);
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);

//        Bitmap bitmap6 = Bitmap.createBitmap(150,200, Bitmap.Config.ARGB_8888);//创建一个Bitmap对象，指定大小
//        Canvas canvas = new Canvas(bitmap6);

    }

    private void testBitmapShader1(Canvas canvas) {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bitmap);
        Shader shader1 = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(shader1);
        canvas.drawCircle(100,100,100,mPaint);

    }


    private void testComposeShader(Canvas canvas) {

        Bitmap bitmapA = BitmapFactory.decodeResource(getResources(), R.mipmap.composeshader_bg);
        Bitmap bitmapB = BitmapFactory.decodeResource(getResources(), R.mipmap.bitmap6);

        Shader shaderA = new BitmapShader(bitmapA, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Shader shaderB = new BitmapShader(bitmapB, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        Shader shader1 = new ComposeShader(shaderA,shaderB, PorterDuff.Mode.DST_IN);

        mPaint.setShader(shader1);
        canvas.drawRect(0,0,bitmapA.getWidth(),bitmapA.getHeight(),mPaint);

    }

    private void TestXfermode(Canvas canvas) {


        Bitmap bitmapA = BitmapFactory.decodeResource(getResources(), R.mipmap.composeshader_bg);
        Bitmap bitmapB = BitmapFactory.decodeResource(getResources(), R.mipmap.bitmap6);
        int save = canvas.saveLayer(0, 0, bitmapB.getWidth(), bitmapB.getHeight(), mPaint,Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(bitmapA, 0, 0, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));//设置 Xfermode
        canvas.drawBitmap(bitmapB, 0, 0, mPaint);
        mPaint.setXfermode(null); //用完之后及时清理

        canvas.restoreToCount(save);

    }


    /*********************颜色 end********************/


    /*****************效果 start****************/
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
     * 抗锯齿  （无效果）
     */
    private void setDither(Canvas canvas){
        //重置画笔
        mTestPaint.reset();
        mTestPaint.setColor(Color.RED);

        Bitmap bitmap = Bitmap.createBitmap(300,300, Bitmap.Config.ARGB_4444);


        canvas.drawRect(0,0,200,200,mTestPaint);
        canvas.drawBitmap(bitmap,0,0,mTestPaint);

        canvas.translate(0,400);
        mTestPaint.setDither(true);
        Bitmap bitmap2 = Bitmap.createBitmap(300,300, Bitmap.Config.ARGB_4444);
        canvas.drawRect(0,0,200,200,mTestPaint);
        canvas.drawBitmap(bitmap2,0,0,mTestPaint);

//
//        Rect rect = new Rect(100,100,400,400);
//        canvas.drawRect(rect,mTestPaint);
//
//        canvas.translate(0,500);
//        mTestPaint.setDither(true);//设置抖动
//        canvas.drawRect(rect,mTestPaint);

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
        mTestPaint.setStyle(Paint.Style.FILL);
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

        float miter = mPaint.getStrokeMiter();
        Path path4  = new Path();
        path4.moveTo(100,680);
        path4.lineTo(250,680);
        path4.lineTo(100,780);
        path4.lineTo(100,670);
        canvas.drawPath(path4, mTestPaint);

        Path path5  = new Path();
        mTestPaint.setStrokeMiter(100f);
        path5.moveTo(500,680);
        path5.lineTo(650,680);
        path5.lineTo(350,740);
        path5.close();//闭环
        canvas.drawPath(path5, mTestPaint);

        Path path6  = new Path();
        mTestPaint.setStrokeMiter(2f);
        path6.moveTo(200,880);
        path6.lineTo(450,880);
        path6.lineTo(350,980);
        path6.close();//闭环
        canvas.drawPath(path6, mTestPaint);

    }


    private void testPathEffec(Canvas canvas){
        TestPathEffec testPathEffec = new TestPathEffec(getContext());
        testPathEffec.onDraw(canvas);
    }

    private void testShadowLayer(Canvas canvas){
        mPaint.reset();
        mPaint.setStrokeWidth(30);
        mPaint.setTextSize(80);
        mPaint.setShadowLayer(10, 5, 5, Color.BLUE);
        canvas.drawText("M-Ellen", 100, 300, mPaint);

//        mPaint.clearShadowLayer();  //清除阴影
    }

    private void testMaskFilter(Canvas canvas){
        mPaint.reset();
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(80);

        MaskFilter maskFilter = new BlurMaskFilter(50, BlurMaskFilter.Blur.INNER);
//        MaskFilter maskFilter = new BlurMaskFilter(100, BlurMaskFilter.Blur.OUTER);
//        MaskFilter maskFilter = new BlurMaskFilter(100, BlurMaskFilter.Blur.NORMAL);
//        MaskFilter maskFilter = new BlurMaskFilter(150, BlurMaskFilter.Blur.SOLID);

//        mPaint.setMaskFilter(maskFilter);

        canvas.translate(0, 350);
        canvas.drawCircle(300,0,150,mPaint);

        canvas.translate(0, 300);
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.d),100,0, mPaint);

    }

    private void testMaskFilter1(Canvas canvas){
        canvas.drawColor(Color.BLACK);
        mTestPaint.reset();
        mTestPaint.setColor(Color.RED);
        mTestPaint.setTextSize(100);
        mTestPaint.setFakeBoldText(true);

//        canvas.drawText("原始图片", 100, 100, mTestPaint);

        MaskFilter maskFilter = new BlurMaskFilter(10, BlurMaskFilter.Blur.INNER);
//        MaskFilter maskFilter = new BlurMaskFilter(10, BlurMaskFilter.Blur.OUTER);
//        MaskFilter maskFilter = new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL);
//        MaskFilter maskFilter = new BlurMaskFilter(30, BlurMaskFilter.Blur.SOLID);

        mTestPaint.setMaskFilter(maskFilter);

        canvas.drawText("Blur.INNER", 100, 100, mTestPaint);
//        canvas.drawText("Blur.OUTER", 100, 100, mTestPaint);
//        canvas.drawText("Blur.NORMAL", 100, 100, mTestPaint);
//        canvas.drawText("Blur.SOLID", 100, 100, mTestPaint);

    }


    private void testEmbossMaskFilter(Canvas canvas){
        mPaint.reset();
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(100);
        mPaint.setFakeBoldText(true);
        mPaint.setFakeBoldText(true);

        MaskFilter maskFilter = new EmbossMaskFilter(new float[]{10,10,10},0.2f,0.2f,10);
        mPaint.setMaskFilter(maskFilter);

        canvas.drawText("EmbossMask",20,100,mPaint);

        canvas.translate(0, 350);
        canvas.drawCircle(300,0,150,mPaint);

        canvas.translate(0, 300);
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.d),100,0, mPaint);

    }


//    private void testGetPath(Canvas canvas){
//        mPaint.setPathEffect(new DashPathEffect(new float[]{20f,10f,40f,15f},0));
//        Path src ;
//        Path dst = new Path();
//        mPaint.getFillPath(src, dst);
//        canvas.drawPath(dst, mPaint);
//    }

    /*****************效果 end****************/


    /**
     * 与文本相关的API
     */
    private void testDrawText(Canvas canvas){

        canvas.translate(getWidth()/10, 50);//先将画布平移，方便观察

        mTestPaint.setTextSize(50);
        canvas.drawText("大小为50的文本", 50, 50, mTestPaint);

        //重置画笔
        mTestPaint.reset();
        mTestPaint.setTextSize(40);

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

        //设置文本水平倾斜
        mTestPaint.setTextSkewX(-0.5f);
        canvas.drawText("倾斜的文本", 50, 300, mTestPaint);

        //重置画笔
        mTestPaint.reset();
        mTestPaint.setTextSize(40);

        //设置文本水平倾斜
        mTestPaint.setTextScaleX(2);
        canvas.drawText("水平放大的文本", 50, 350, mTestPaint);

        //重置画笔
        mTestPaint.reset();
        mTestPaint.setTextSize(40);


        //重置画笔
        mTestPaint.reset();
        mTestPaint.setTextSize(40);

        mTestPaint.setLetterSpacing(1);
        canvas.drawText("有间距的文本", 50, 400, mTestPaint);

        //重置画笔
        mTestPaint.reset();
        mTestPaint.setLetterSpacing(0);
        mTestPaint.setTextSize(40);

        //设置文本阴影
        mTestPaint.setShadowLayer(5, 5, 5, Color.RED);
        canvas.drawText("有阴影的文本", 50, 450, mTestPaint);

        //重置画笔
        mTestPaint.reset();
        mTestPaint.setTextSize(40);

        //设置文本字体样式
        Typeface typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC);
        mTestPaint.setTypeface(typeface);
        canvas.drawText("有样式的文本", 50, 500, mTestPaint);

//        mTestPaint.setTextAlign(Paint.Align.LEFT);
//        canvas.drawText("文本左对齐", 50, 150, mTestPaint);
//        mTestPaint.setTextAlign(Paint.Align.CENTER);
//        canvas.drawText("文本居中对齐", 50, 200, mTestPaint);
//        mTestPaint.setTextAlign(Paint.Align.RIGHT);
//        canvas.drawText("文本右对齐", 50, 250, mTestPaint);
    }


    /**
     * 设置字体
     */
    private void testTypeface(Canvas canvas){
        //设置文本字体样式
        mTestPaint.setTextSize(40);

        //直接设置Typeface对象
        mTestPaint.setTypeface(Typeface.MONOSPACE);
        canvas.drawText("字体monospace，样式默认", 50, 100, mTestPaint);


        //指定样式
        Typeface typeface0 = Typeface.defaultFromStyle(Typeface.ITALIC);
        mTestPaint.setTypeface(typeface0);
        canvas.drawText("字体默认，样式斜体", 50, 150, mTestPaint);

        //指定字体、样式
        Typeface typeface1 = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC);
        mTestPaint.setTypeface(typeface1);
        canvas.drawText("字体sans serif，样式粗体+斜体", 50, 200, mTestPaint);

        //指定字体、样式
        Typeface typeface2 = Typeface.create("微软雅黑", Typeface.BOLD);
        mTestPaint.setTypeface(typeface2);
        canvas.drawText("字体微软雅黑，样式粗体", 50, 250, mTestPaint);

        //该字体对中文没有效果
        Typeface typeface3 = Typeface.createFromAsset(getContext().getAssets(),"fonts/samplefont.ttf");
        mTestPaint.setTypeface(typeface3);
        canvas.drawText("Custom Font", 50, 300, mTestPaint);

    }


    /**
     * CSS 样式的字体
     * @param canvas
     */
    private void testFontFeatureSettings(Canvas canvas){

//        mTestPaint.setFontFeatureSettings("tnum");
        mTestPaint.reset();
        mTestPaint.setTextSize(60);
//        mTestPaint.setFontFeatureSettings("dlig");
//        mTestPaint.setFontFeatureSettings("tnum");
//        mTestPaint.setFontFeatureSettings("tnum");
//        mTestPaint.setFontFeatureSettings("tnum");
//        mTestPaint.setFontFeatureSettings("tnum");

//        mTestPaint.setFontVariationSettings("'wdth' 150");
//        canvas.drawText(" Hello CSS ", 50, 100, mTestPaint);

    }

    private void testTextLocale(Canvas canvas){
        mPaint.setTextSize(40);
        mPaint.setTextLocale(Locale.CHINA);
        canvas.drawText("厉害了，我的国", 50, 100, mPaint);

        mPaint.setTextLocale(Locale.CHINESE);
        canvas.drawText("厉害了，我的国", 50, 150, mPaint);

        mPaint.setTextLocale(Locale.TAIWAN);
        canvas.drawText("厉害了，我的国", 50, 200, mPaint);

        mPaint.setTextLocale(Locale.JAPAN);
        canvas.drawText("厉害了，我的国", 50, 250, mPaint);


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

    private void testTextPosition(Canvas canvas){

        /**
         * 2个问题
         */
        mTestPaint.setTextSize(75);
        canvas.drawText("原点为(50,50)的文本", 50, 50, mTestPaint);

        mTestPaint.setTextSize(40);
        canvas.drawText("在屏幕中央的文本", getWidth()/2, getHeight()/2, mTestPaint);

        mTestPaint.setStrokeWidth(2);
        mTestPaint.setColor(Color.RED);
        mTestPaint.setTextAlign(Paint.Align.CENTER);
//        canvas.drawPoint(50, 50, mTestPaint);
        canvas.drawLine(0, 50, getWidth(), 50, mTestPaint);

//        mTestPaint.setStrokeWidth(4);
//        canvas.drawLine(0, getHeight()/2, getWidth(), getHeight()/2,mTestPaint);
//        canvas.drawLine(getWidth()/2, 0, getWidth()/2, getHeight(),mTestPaint);



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


    private void testTextPos(Canvas canvas){

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
        canvas.drawText("在屏幕中央的文本", getWidth()/2, getHeight()/2 + (fontMetrics1.descent - fontMetrics1.ascent)/2 - fontMetrics1.descent, mTestPaint);


        /**
         * 对于第2个问题的另一种解决
         */
//        mTestPaint.setTextSize(60);
//        String text = "在屏幕中央的文本";
//        Rect textRect = new Rect();
//        mTestPaint.getTextBounds(text, 0, text.length(), textRect);
//        canvas.drawText(text, getWidth()/2 - textRect.width()/2, getHeight()/2 + textRect.height()/2, mTestPaint);


//        mTestPaint.setColor(Color.RED);
//        mTestPaint.setStrokeWidth(4);
//
        canvas.drawLine(50, Math.abs(top), getWidth(), Math.abs(top),mTestPaint);//第一个问题的参考线
        canvas.drawLine(0, getHeight()/2, getWidth(), getHeight()/2,mTestPaint);//第二个问题的参考线
        canvas.drawLine(getWidth()/2, 0, getWidth()/2, getHeight(),mTestPaint);


    }

    private void test1(){

//        mTestPaint.getRunAdvance("测光标的显示", 0, 4);
//
//        getRunAdvance(CharSequence text, int start, int end, int contextStart, int contextEnd, boolean isRtl, int offset)
//
//        getOffsetForAdvance(CharSequence text, int start, int end, int contextStart, int contextEnd, boolean isRtl, float advance)

    }
}
