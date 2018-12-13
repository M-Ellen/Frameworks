package com.gotechcn.frameworks.customview.Drawable;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gotechcn.frameworks.R;

public class CustomViewActivity extends Activity implements View.OnClickListener {

    private TextView mTv1;
    private TextView mTv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customview);
        initView();
    }

    private void initView(){
        mTv1 = (TextView) findViewById(R.id.tv1);
        mTv2 = (TextView) findViewById(R.id.tv2);

        mTv1.setOnClickListener(this);
        mTv2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv1 :
                startActivity(new Intent(CustomViewActivity.this, CanvasActivity.class));
                break;
            case R.id.tv2 :
                startActivity(new Intent(CustomViewActivity.this, PaintActivity.class));
                break;
        }
    }


}
