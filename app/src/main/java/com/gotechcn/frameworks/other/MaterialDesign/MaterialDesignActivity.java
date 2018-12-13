package com.gotechcn.frameworks.other.MaterialDesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.gotechcn.frameworks.R;
import com.gotechcn.frameworks.fragment.CommonFrameFragmentAdapter;

public class MaterialDesignActivity extends AppCompatActivity {

    private static final String TAG = "MaterialDesignActivity";
    private ListView mListView;
    private CommonFrameFragmentAdapter mAdapter;
    private String[] mDatas = {"Toolbar","AppBarLayout","CollapsingToolbarLayout","DrawerLayout+NavigationView",
    "FloatingActionButton","Snackbar","TextInputLayout","TabLayout","Animation"};

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design);
        mListView = findViewById(R.id.list_view_material_design);
        mAdapter = new CommonFrameFragmentAdapter(this, mDatas);
        mListView.setAdapter(mAdapter);
        initListener();
        initToolbar();
    }

    private void initListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String data = mDatas[position];

                switch (data) {
                    case "Toolbar" :
                        mToolbar.setVisibility(View.VISIBLE);
                        break;
                    case "AppBarLayout" :
                        Intent intent = new Intent(MaterialDesignActivity.this,AppBarActivity.class);
                        startActivity(intent);
                        break;
                    case "CollapsingToolbarLayout" :
                        Intent intent2 = new Intent(MaterialDesignActivity.this,CollapsingToolbarActivity.class);
                        startActivity(intent2);
                        break;
                    case "DrawerLayout+NavigationView" :
                        Intent intent3 = new Intent(MaterialDesignActivity.this,DrawerLayoutActivity.class);
                        startActivity(intent3);
                        break;
                    case "FloatingActionButton" :
                        Intent intent4 = new Intent(MaterialDesignActivity.this,FloatingActionButtonActivity.class);
                        startActivity(intent4);
                        break;
                    case "Snackbar" :
                        Intent intent5 = new Intent(MaterialDesignActivity.this,SnackbarActivity.class);
                        startActivity(intent5);
                        break;
                    case "TextInputLayout" :
                        Intent intent6 = new Intent(MaterialDesignActivity.this,TextInputActivity.class);
                        startActivity(intent6);
                        break;
                    case "TabLayout" :
                        Intent intent7 = new Intent(MaterialDesignActivity.this,TabLayoutActivity.class);
                        startActivity(intent7);
                        break;
                    case "Animation" :
                        Intent intent8 = new Intent(MaterialDesignActivity.this,AnimationActivity.class);
                        startActivity(intent8);
                        break;
                    default:
                        break;
                }
                // 点击位置的显示
                Toast.makeText(getApplicationContext(), "data==" + data,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initToolbar() {
        //init toolbar
        mToolbar = findViewById(R.id.toolbar);
        //设置标题
        mToolbar.setTitle(R.string.MaterialDesigntitle);
        //设置子标题
        mToolbar.setSubtitle(R.string.app_name);
        //设置logo
        mToolbar.setLogo(R.mipmap.ic_launcher);
        // 调用该方法，才最终设置给Activity
        setSupportActionBar(mToolbar);

        //设置返回按钮，需在 setSupportActionBar() 后面设置
        mToolbar.setNavigationIcon(R.mipmap.icon_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mToolbar.setVisibility(View.GONE);
    }
}
