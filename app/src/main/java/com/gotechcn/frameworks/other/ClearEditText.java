package com.gotechcn.frameworks.other;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

import com.gotechcn.frameworks.R;

/**
 * Created by zhwp on 2017/3/15.
 */

@SuppressLint("AppCompatCustomView")
public class ClearEditText extends EditText implements View.OnFocusChangeListener, TextWatcher {
	private Drawable							mClearDrawable;
	private boolean								hasFoucs;
	private static OnEditFocusChangeListener	onEditFocusChangeListener	= null;
	private OnVoiceClickListener				mOnVoiceClickListener		= null;
	private boolean								isShowVoiceIcon				= false;

	public ClearEditText(Context context) {
		this(context, null);
		init();
	}
	
	public ClearEditText(Context context, AttributeSet attrs) {
		// 这里构造方法也很重要，不加这个很多属性不能再XML里面定义
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ClearEditText);
		isShowVoiceIcon=a.getBoolean(R.styleable.ClearEditText_isShowVoice,false);
		a.recycle();
		// this(context, attrs, android.R.attr.editTextStyle);
		init();
	}
	
	public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ClearEditText);
        isShowVoiceIcon=a.getBoolean(R.styleable.ClearEditText_isShowVoice,false);
		a.recycle();
        init();
	}
	
	private void init() {
		// 获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
		mClearDrawable = getCompoundDrawables()[2];
		
		if (mClearDrawable == null) {
			
			mClearDrawable = getResources().getDrawable(R.mipmap.icon_search);
		}
		
		mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
		
		// // 默认设置隐藏图标
		setClearIconVisible(false);
		// 设置焦点改变的监听
		setOnFocusChangeListener(this);
		// 设置输入框里面内容发生改变的监听
		addTextChangedListener(this);
	}
	
	/**
	 * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件 当我们按下的位置 在 EditText的宽度 -
	 * 图标到控件右边的间距 - 图标的宽度 和 EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有考虑
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			if (getCompoundDrawables()[2] != null) {
				
				boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight() - getCompoundDrawablePadding())
						&& (event.getX() < ((getWidth() - getPaddingRight())));
				
				if (touchable) {
					if (getText().length() > 0) {
						this.setText("");
					} else {
						if (mOnVoiceClickListener != null) {
							mOnVoiceClickListener.onVoiceClick();
						}
					}
				}
			}
		}
		
		return super.onTouchEvent(event);
	}
	
	/**
	 * 当ClearEditText焦点发生变化的时候，判断里面字符串长度设置清除图标的显示与隐藏
	 */
	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		this.hasFoucs = hasFocus;
		if (hasFocus) {
			setClearIconVisible(true);
			// setClearIconVisible(getText().length() > 0);
		} else {
			setClearIconVisible(false);
		}
		if (onEditFocusChangeListener != null) {
			onEditFocusChangeListener.onEditFocusChange(v, hasFocus);
		}
	}
	
	/**
	 * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
	 *
	 * @param visible
	 */
	protected void setClearIconVisible(boolean visible) {
		Drawable right = visible ? mClearDrawable : null;
		setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
	}


	/**
	 * 当输入框里面内容发生变化的时候回调的方法
	 */
	@Override
	public void onTextChanged(CharSequence s, int start, int count, int after) {
		if (hasFoucs) {
			if (getText().length() > 0) {
				if (isShowVoiceIcon){
					mClearDrawable = getResources().getDrawable(R.mipmap.icon_search);
				}else {
					mClearDrawable = getResources().getDrawable(R.mipmap.icon_search);
				}
			} else {
				if (isShowVoiceIcon){
					mClearDrawable = getResources().getDrawable(R.mipmap.icon_search);
				}else {
					mClearDrawable = getResources().getDrawable(R.mipmap.icon_search);
				}
			}
			mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
			setClearIconVisible(true);
			// setClearIconVisible(s.length() > 0);
		}
	}
	
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	}
	
	@Override
	public void afterTextChanged(Editable s) {
	}
	
	/**
	 * 设置晃动动画
	 */
	public void setShakeAnimation() {
		this.setAnimation(shakeAnimation(5));
	}
	
	/**
	 * 晃动动画
	 *
	 * @param counts
	 *            1秒钟晃动多少下
	 * @return
	 */
	public static Animation shakeAnimation(int counts) {
		Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
		translateAnimation.setInterpolator(new CycleInterpolator(counts));
		translateAnimation.setDuration(1000);
		return translateAnimation;
	}
	
	public void setLeftDrawable(Drawable leftDrawable, Drawable rightDrawable) {
		setCompoundDrawables(leftDrawable, getCompoundDrawables()[1], rightDrawable, getCompoundDrawables()[3]);
	}
	
	public static void setOnEditFocusChangeListener(OnEditFocusChangeListener listener) {
		onEditFocusChangeListener = listener;
	}
	
	public interface OnEditFocusChangeListener {
		void onEditFocusChange(View v, boolean hasFocus);
	}
	
	public void setOnVoiceClickListener(OnVoiceClickListener onVoiceClickListener) {
		this.mOnVoiceClickListener = onVoiceClickListener;
	}
	public interface OnVoiceClickListener {
		void onVoiceClick();
	}
}
