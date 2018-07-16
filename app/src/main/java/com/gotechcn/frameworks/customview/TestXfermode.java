package com.gotechcn.frameworks.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by pzm on 2017/10/16
 */

public class TestXfermode extends View {
	
	private Paint					mPaint	= null;
	private Bitmap					mDstBitmap;
	private Bitmap					mSrcBitmap;
	private Shader					mBG;																														// background
																																								// checker-board
																																								// pattern
	private static final int		W		= 120;
	private static final int		H		= 120;
	
	private static final int		ROW_MAX	= 4;																												// number
																																								// of
																																								// samples
																																								// per
																																								// row
	private static final Xfermode[]	sModes	= {new PorterDuffXfermode(PorterDuff.Mode.CLEAR), new PorterDuffXfermode(PorterDuff.Mode.SRC),
			new PorterDuffXfermode(PorterDuff.Mode.DST), new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER), new PorterDuffXfermode(PorterDuff.Mode.DST_OVER),
			new PorterDuffXfermode(PorterDuff.Mode.SRC_IN), new PorterDuffXfermode(PorterDuff.Mode.DST_IN), new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT),
			new PorterDuffXfermode(PorterDuff.Mode.DST_OUT), new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP), new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP),
			new PorterDuffXfermode(PorterDuff.Mode.XOR), new PorterDuffXfermode(PorterDuff.Mode.DARKEN), new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN),
			new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY), new PorterDuffXfermode(PorterDuff.Mode.SCREEN)};
	
	private static final String[]	sLabels	= {"Clear", "Src", "Dst", "SrcOver", "DstOver", "SrcIn", "DstIn", "SrcOut", "DstOut", "SrcATop", "DstATop", "Xor",
			"Darken", "Lighten", "Multiply", "Screen"};
	
	/**
	 * 直接在xml中定义并显示，所以只实现该构造函数即可
	 * 
	 * @param context
	 * @param attrs
	 */
	public TestXfermode(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mDstBitmap = makeDst(W, H);
		mSrcBitmap = makeSrc(W, H);
		// make a ckeckerboard pattern
		Bitmap bm = Bitmap.createBitmap(new int[]{0xFFFFFFFF, 0xFFCCCCCC, 0xFFCCCCCC, 0xFFFFFFFF}, 2, 2, Bitmap.Config.RGB_565);
		mBG = new BitmapShader(bm, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
		Matrix m = new Matrix();
		m.setScale(6, 6);
		mBG.setLocalMatrix(m);
		
	}
	/*
	 * 
	 * 谷歌例子源码
	 * 
	 * @Override protected void onDraw(Canvas canvas) { super.onDraw(canvas);
	 * canvas.drawColor(Color.WHITE);
	 * 
	 * Paint labelP = new Paint(Paint.ANTI_ALIAS_FLAG);
	 * labelP.setTextAlign(Paint.Align.CENTER);
	 * 
	 * Paint paint = new Paint(); paint.setFilterBitmap(false);
	 * 
	 * canvas.translate(15, 35);
	 * 
	 * int x = 0; int y = 0; for (int i = 0; i < sModes.length; i++) { // draw the
	 * border paint.setStyle(Paint.Style.STROKE); paint.setShader(null);
	 * canvas.drawRect(x - 0.5f, y - 0.5f, x + W + 0.5f, y + H + 0.5f, paint);
	 * 
	 * // draw the checker-board pattern paint.setStyle(Paint.Style.FILL);
	 * paint.setShader(mBG); canvas.drawRect(x, y, x + W, y + H, paint);
	 * 
	 * // draw the src/dst example into our offscreen bitmap int sc =
	 * canvas.saveLayer(x, y, x + W, y + H, null, Canvas.MATRIX_SAVE_FLAG |
	 * Canvas.CLIP_SAVE_FLAG | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
	 * Canvas.FULL_COLOR_LAYER_SAVE_FLAG | Canvas.CLIP_TO_LAYER_SAVE_FLAG);
	 * canvas.translate(x, y); canvas.drawBitmap(mDstBitmap, 0, 0, paint);
	 * paint.setXfermode(sModes[i]); canvas.drawBitmap(mSrcBitmap, 0, 0, paint);
	 * paint.setXfermode(null); canvas.restoreToCount(sc);
	 * 
	 * // draw the label canvas.drawText(sLabels[i], x + W/2, y -
	 * labelP.getTextSize()/2, labelP);
	 * 
	 * x += W + 10;
	 * 
	 * // wrap around when we've drawn enough for one row if ((i % ROW_MAX) ==
	 * ROW_MAX - 1) { x = 0; y += H + 30; } } }
	 * 
	 * // create a bitmap with a circle, used for the "dst" image static Bitmap
	 * makeDst(int w, int h) { Bitmap bm = Bitmap.createBitmap(w, h,
	 * Bitmap.Config.ARGB_8888); Canvas c = new Canvas(bm); Paint p = new
	 * Paint(Paint.ANTI_ALIAS_FLAG);
	 * 
	 * p.setColor(0xFFFFCC44); c.drawOval(new RectF(0, 0, w*3/4, h*3/4), p); return
	 * bm; }
	 * 
	 * // create a bitmap with a rect, used for the "src" image static Bitmap
	 * makeSrc(int w, int h) { Bitmap bm = Bitmap.createBitmap(w, h,
	 * Bitmap.Config.ARGB_8888); Canvas c = new Canvas(bm); Paint p = new
	 * Paint(Paint.ANTI_ALIAS_FLAG);
	 * 
	 * p.setColor(0xFF66AAFF); c.drawRect(w/3, h/3, w*19/20, h*19/20, p); return bm;
	 * }
	 */
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		// mPaint.setColor(Color.YELLOW);
		// canvas.drawRect(0,60,400,500,mPaint);
		
		int saved = canvas.saveLayer(0, 0, W * 2, H * 2, mPaint, Canvas.ALL_SAVE_FLAG);
		
		canvas.drawBitmap(mDstBitmap, 0, 0, mPaint);
		Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC);
		// mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
		mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
		// mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST));
		// mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
		// mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
		// mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		// mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		// mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
		// mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
		// mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
		// mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
		// mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
		// mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DARKEN));
		// mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN));
		// mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
		// mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SCREEN));
		// mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.ADD));
		// mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.OVERLAY));
		canvas.drawBitmap(mSrcBitmap, W / 2, H / 2, mPaint);
		// mPaint.setXfermode(null);
		
		// canvas.restore();
		
		// mPaint.setColor(Color.YELLOW);
		canvas.drawBitmap(mSrcBitmap, 0, H * 3 + 10, mPaint);
		mPaint.setColor(Color.YELLOW);
		canvas.drawRect(0, 200, 400, 500, mPaint);
		
		// int saved2 = canvas.saveLayer(0,0,W*2,H*2,mPaint,Canvas.ALL_SAVE_FLAG);
		
		// canvas.restoreToCount(saved);
		// canvas.restore();
		// canvas.restore();
		// canvas.restore();
		// int saved3 = canvas.saveLayer(0,0,W*2,H*2,mPaint,Canvas.ALL_SAVE_FLAG);
		
	}
	
	// create a bitmap with a circle, used for the "dst" image
	static Bitmap makeDst(int w, int h) {
		Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(bm);
		Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
		
		p.setColor(0xFFFFCC44);
		c.drawOval(new RectF(0, 0, w, h), p);
		return bm;
	}
	
	// create a bitmap with a rect, used for the "src" image
	static Bitmap makeSrc(int w, int h) {
		Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(bm);
		Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
		
		p.setColor(0xFF66AAFF);
		c.drawRect(0, 0, w, h, p);
		return bm;
	}
	
}
