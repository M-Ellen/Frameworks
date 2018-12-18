package com.gotechcn.frameworks.other.MaterialDesign;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.gotechcn.frameworks.R;

public class CollapsingToolbarActivity extends AppCompatActivity {
	
	private Toolbar toolbar;
	private CollapsingToolbarLayout collapsingToolbarLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_collapsing_toolbar);
		toolbar = findViewById(R.id.toolbar_collapsing_toolbar);

//		toolbar.setTitle("CollapsingToolbarLayout");

		setSupportActionBar(toolbar);
		
		// 通过 actionBar 设置 导航按钮，自动设置图标，默认id是 android.R.id.home
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		//也可单独设置
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);


		// 通过toolbar  设置 导航按钮图标
//		toolbar.setNavigationIcon(R.mipmap.icon_back);
//		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				finish();
//			}
//		});

		/**
		 * 设置 伸张 、折叠字体的颜色
		 */
		collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
//		collapsingToolbarLayout.setTitle("CollapsingToolbarLayout");
		collapsingToolbarLayout.setTitle("Collap");

		collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorAccent));
		collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(android.R.color.white));
	}

    /**
     * 监听 android.R.id.home导航按钮，也可以通过toolbar.setNavigationOnClickListener()来监听
     */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home :
                finish();
				return true;
			default :
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
