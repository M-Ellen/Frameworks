package com.gotechcn.frameworks.common.recycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gotechcn.frameworks.R;
import com.gotechcn.frameworks.common.recycleview.itemanimation.SlideInOutBottomItemAnimator;

import java.util.ArrayList;

public class RecycleViewActivity extends AppCompatActivity implements View.OnClickListener {
    
    private Button mBtnadd;
    private Button mBtnbDelete;
    private Button mBtnList;
    private Button mBtnGrid;
    private Button mBtnFlow;
    private RecyclerView mRecyclerview;

    private ArrayList<String> datas;
    private MyRecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        initView();
        initData();

        adapter = new MyRecycleViewAdapter(this,datas);
        mRecyclerview.setAdapter(adapter);
        /**
         * 第一个参数：上下文
         * 第二个参数：选择布局的方向
         * 第三个参数：顺序是否倒序
         */
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        //如果设置倒序，默认还是选择第1个（即视图的最后一个），可以用该方法设置选中的位置
//        mRecyclerview.scrollToPosition(datas.size() - 1);

        //设置分割线
//        mRecyclerview.addItemDecoration(new DividerListItemDecoration(this,DividerListItemDecoration.VERTICAL_LIST));
        mRecyclerview.addItemDecoration(new DividerGridItemDecoration(this));

        //设置动画（默认）
//        mRecyclerview.setItemAnimator(new DefaultItemAnimator());
        mRecyclerview.setItemAnimator(new SlideInOutBottomItemAnimator(mRecyclerview));
        setLisenter();
    }

    /**
     * item监听
     */
    private void setLisenter() {
        adapter.setOnItemClickListener(new MyRecycleViewAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RecycleViewActivity.this, position + " click",Toast.LENGTH_SHORT).show();            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(RecycleViewActivity.this, position + " longclick",Toast.LENGTH_SHORT).show();
                adapter.removeData(position);
            }
        });
    }

    private void initData() {
        datas = new ArrayList();
        for (int i = 0; i < 100; i++) {
            datas.add("content" + i);
        }
    }

    private void initView() {
        mBtnadd = (Button)findViewById(R.id.btn_add);
        mBtnbDelete = (Button)findViewById(R.id.btn_delete);
        mBtnList = (Button)findViewById(R.id.btn_list);
        mBtnGrid = (Button)findViewById(R.id.btn_grid);
        mBtnFlow = (Button)findViewById(R.id.btn_flow);
        mRecyclerview = (RecyclerView)findViewById(R.id.recyclerview);

        mBtnadd.setOnClickListener(this);
        mBtnbDelete.setOnClickListener(this);
        mBtnList.setOnClickListener(this);
        mBtnGrid.setOnClickListener(this);
        mBtnFlow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add :
                adapter.addData(0);
                //默认添加之后，不会显示到最新的位置，需要设置
                mRecyclerview.scrollToPosition(0);

                break;
            case R.id.btn_delete :
                adapter.removeData(0);

                break;
            case R.id.btn_list :
                //设置listView的效果
                mRecyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//                mRecyclerview.setItemAnimator(new SlideInOutLeftItemAnimator(mRecyclerview));
                break;
            case R.id.btn_grid :
                //设置GridView的效果
                mRecyclerview.setLayoutManager(new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false));
//                mRecyclerview.setItemAnimator(new SlideInOutTopItemAnimator(mRecyclerview));

                break;
            case R.id.btn_flow :
                mRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
//                mRecyclerview.setItemAnimator(new SlideScaleInOutRightItemAnimator(mRecyclerview));

                break;
            default:
                break;

        }
    }
}
