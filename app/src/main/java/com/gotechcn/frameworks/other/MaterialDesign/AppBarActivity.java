package com.gotechcn.frameworks.other.MaterialDesign;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.gotechcn.frameworks.R;

public class AppBarActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    AppBarLayout.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar);

        //init toolbar
        toolbar = findViewById(R.id.toolbar_app_bar);
        // 设置给Activity
        toolbar.setTitle("AppBarLayout");
        setSupportActionBar(toolbar);

        //设置返回按钮，需在 setSupportActionBar() 后面设置
        toolbar.setNavigationIcon(R.mipmap.icon_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        /**
         * 通过代码设置 setScrollFlags()
         */
//        appBarLayout = findViewById(R.id.app_bar_layout);
//        params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
//        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
//        params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_app_bar, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.app_bar_menu_scroll:
//                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);
//                break;
//            case R.id.app_bar_menu_enterAlways:
//                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
//                break;
//
//            case R.id.app_bar_menu_enterAlwaysCollapsed:
//                //需要设置最小高度，效果才明显
//                toolbar.setMinimumHeight(DensityUtil.dpTopx(this,56));
//                ViewGroup.LayoutParams layoutParams = toolbar.getLayoutParams();
//                layoutParams.height = DensityUtil.dpTopx(this,200);
//                toolbar.setLayoutParams(layoutParams);
//                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
//                        |AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
//                        |AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED);
//                break;
//            case R.id.app_bar_menu_exitUntilCollapsed:
//                //需要设置最小高度，效果才明显
//                toolbar.setMinimumHeight(DensityUtil.dpTopx(this,56));
//                ViewGroup.LayoutParams layoutParams1 = toolbar.getLayoutParams();
//                layoutParams1.height = DensityUtil.dpTopx(this,200);
//                toolbar.setLayoutParams(layoutParams1);
//
//                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
//                        |AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
//                        |AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
//                break;
//            case R.id.app_bar_menu_snap:
//                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP);
//                break;
//            default:
//                break;
//        }
//        Toast.makeText(getApplicationContext(),item.getTitle(), Toast.LENGTH_SHORT).show();
//        return super.onOptionsItemSelected(item);
//    }
}
