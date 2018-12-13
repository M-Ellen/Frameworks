package com.gotechcn.frameworks.customview.RotateMenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gotechcn.frameworks.R;

public class RotateMenuActivity extends Activity implements View.OnClickListener {

    private ImageView mMenu;
    private ImageView mHome;
    private RelativeLayout mLeve1;
    private RelativeLayout mLeve2;
    private RelativeLayout mLeve3;
    /**
     * 判断是是否显示
     */
    private boolean isLeve3Show = true;
    private boolean isLeve2Show = true;
    private boolean isLeve1Show = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate_menu);

        mMenu = (ImageView) findViewById(R.id.menu);
        mHome = (ImageView) findViewById(R.id.home);
        mLeve1 = (RelativeLayout)findViewById(R.id.level1);
        mLeve2 = (RelativeLayout) findViewById(R.id.level2);
        mLeve3 = (RelativeLayout) findViewById(R.id.level3);

        mMenu.setOnClickListener(this);
        mHome.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.menu:
                if(isLeve3Show) {
                    isLeve3Show = false;
                    Tools.hideView(mLeve3);
                }else {
                    isLeve3Show = true;
                    Tools.showView(mLeve3);
                }

                break;
            case  R.id.home:
                if(isLeve2Show) {
                    isLeve2Show = false;
                    Tools.hideView(mLeve2);
                    if(isLeve3Show) {
                        isLeve3Show = false;
                        Tools.hideView(mLeve3,200);
                    }

                }else {
                    isLeve2Show = true;
                    Tools.showView(mLeve2);
                }

                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case  KeyEvent.KEYCODE_MENU:
                if(isLeve1Show) {
                    isLeve1Show = false;
                    Tools.hideView(mLeve1);
                    if(isLeve2Show) {
                        isLeve2Show = false;
                        Tools.hideView(mLeve2,100);
                        if(isLeve3Show) {
                            isLeve3Show = false;
                            Tools.hideView(mLeve3,200);
                        }
                    }
                }else {
                    isLeve1Show = true;
                    Tools.showView(mLeve1);
                    if(!isLeve2Show) {
                        isLeve2Show = true;
                        Tools.showView(mLeve2,200);
                    }
                }

                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}
