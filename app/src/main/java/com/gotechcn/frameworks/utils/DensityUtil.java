package com.gotechcn.frameworks.utils;

import android.content.Context;

/**
 * Created by zhwp on 2018/4/3.
 */

public class DensityUtil {
	
	private static float getDensity(Context context) {
		return context.getResources().getDisplayMetrics().density;
	}
	
	public static int dpTopx(Context context, float dpValue) {
		return (int) (dpValue * getDensity(context) + 0.5f);
	}
	
	public static int pxTodp(Context context, float pxValue) {
		return (int) (pxValue / getDensity(context) + 0.5f);
	}
	
	public static float spTopx(Context context, float spValue) {
		return spValue * getDensity(context);
	}
	
}
