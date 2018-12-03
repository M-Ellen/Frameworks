package com.gotechcn.frameworks.customview;


import android.app.Activity;
import android.os.Bundle;

import com.gotechcn.frameworks.R;

/**
 * @author
 */
public class PaintActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);

//        ImageView imageView = (ImageView) findViewById(R.id.iv_matrix);
//
//        ImageView.ScaleType scaleType = imageView.getScaleType();
//        imageView.setScaleType(ImageView.ScaleType.MATRIX);
//
//        ImageView.ScaleType scaleType1 = imageView.getScaleType();
//        Matrix matrix = new Matrix();
//        matrix.setRotate(30);
//        imageView.setImageMatrix(matrix);

    }
}
