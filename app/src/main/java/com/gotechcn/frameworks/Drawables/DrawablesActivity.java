package com.gotechcn.frameworks.Drawables;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gotechcn.frameworks.R;

public class DrawablesActivity extends Activity implements View.OnClickListener {

    private TextView mTv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawables);
        initView();
    }

    private void initView(){
        mTv1 = (TextView) findViewById(R.id.tv1);

        mTv1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv1 :
                startActivity(new Intent(DrawablesActivity.this, CanvasActivity.class));
                break;
        }
    }


}
