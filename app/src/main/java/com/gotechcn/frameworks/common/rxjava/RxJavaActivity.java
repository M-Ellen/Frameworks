package com.gotechcn.frameworks.common.rxjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.gotechcn.frameworks.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Schedulers.immediate()   当前线程 = 不指定线程      默认
 AndroidSchedulers.mainThread()   Android主线程      操作UI
 Schedulers.newThread()     常规新线程               耗时等操作
 Schedulers.io()            io操作线程              网络请求、读写文件等io密集型操作
 Schedulers.computation()   CPU计算操作线程           大量计算操作
 *
 */

public class RxJavaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private GridView mGridView = null;
    private List<RxJavaDatas> mDataList = null;
    private RxJavaGridViewAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        initData();
        mGridView = findViewById(R.id.rxjava_gridview);
        mGridView.setOnItemClickListener(this);
        loadGridView();
    }


    protected void initData() {
        mDataList = new ArrayList<>();
        /***创建符***/
        mDataList.add(new RxJavaDatas(getString(R.string.rx_create), getString(R.string.rx_create_des)));
        mDataList.add(new RxJavaDatas(getString(R.string.rx_defer), getString(R.string.rx_defer_des)));
        mDataList.add(new RxJavaDatas(getString(R.string.rx_timer_interval), getString(R.string.rx_timer_interval_des)));
        mDataList.add(new RxJavaDatas(getString(R.string.rx_range), getString(R.string.rx_range_des)));
        /***创建符***/


        /***转换符***/
        mDataList.add(new RxJavaDatas(getString(R.string.rx_map), getString(R.string.rx_map_des)));
//        mDataList.add(new RxJavaDatas(getString(R.string.rx_flatMap), getString(R.string.rx_flatMap_des)));
//        mDataList.add(new RxJavaDatas(getString(R.string.rx_concatMap), getString(R.string.rx_concatMap_des)));
        /***转换符***/


        /*** 组合符 ***/
        mDataList.add(new RxJavaDatas(getString(R.string.rx_buffer), getString(R.string.rx_buffer_des)));
        mDataList.add(new RxJavaDatas(getString(R.string.rx_concat), getString(R.string.rx_concat_des)));
        mDataList.add(new RxJavaDatas(getString(R.string.rx_merge), getString(R.string.rx_merge_des)));
        mDataList.add(new RxJavaDatas(getString(R.string.rx_zip), getString(R.string.rx_zip_des)));
        mDataList.add(new RxJavaDatas(getString(R.string.rx_combineLatest), getString(R.string.rx_combineLatest_des)));
        mDataList.add(new RxJavaDatas(getString(R.string.rx_reduce), getString(R.string.rx_reduce_des)));
        mDataList.add(new RxJavaDatas(getString(R.string.rx_collect), getString(R.string.rx_collect_des)));
        mDataList.add(new RxJavaDatas(getString(R.string.rx_startWith), getString(R.string.rx_startWith_des)));
        mDataList.add(new RxJavaDatas(getString(R.string.rx_count), getString(R.string.rx_count_des)));

        /*** 组合符 ***/

        /****功能性操作符****/
        mDataList.add(new RxJavaDatas(getString(R.string.rx_doXxx), getString(R.string.rx_doXxx_des)));
        mDataList.add(new RxJavaDatas(getString(R.string.rx_onErrorXxx), getString(R.string.rx_onErrorXxx_des)));
        mDataList.add(new RxJavaDatas(getString(R.string.rx_retryXxx), getString(R.string.rx_retryXxx_des)));
        mDataList.add(new RxJavaDatas(getString(R.string.rx_repeatXxx), getString(R.string.rx_repeatXxx_des)));
        /****功能性操作符****/

        /****各种过滤性操作符****/
        mDataList.add(new RxJavaDatas(getString(R.string.rx_filter), getString(R.string.rx_filter_des)));
//        mDataList.add(new RxJavaDatas(getString(R.string.rx_ofType), getString(R.string.rx_ofType_des)));
        /****各种过滤性操作符****/


        mDataList.add(new RxJavaDatas(getString(R.string.rx_single), getString(R.string.rx_single_des)));
        mDataList.add(new RxJavaDatas(getString(R.string.rx_scan), getString(R.string.rx_scan_des)));
        mDataList.add(new RxJavaDatas(getString(R.string.rx_window), getString(R.string.rx_window_des)));
        mDataList.add(new RxJavaDatas(getString(R.string.rx_PublishSubject), getString(R.string.rx_PublishSubject_des)));
        mDataList.add(new RxJavaDatas(getString(R.string.rx_AsyncSubject), getString(R.string.rx_AsyncSubject_des)));
        mDataList.add(new RxJavaDatas(getString(R.string.rx_BehaviorSubject), getString(R.string.rx_BehaviorSubject_des)));
        mDataList.add(new RxJavaDatas(getString(R.string.rx_Completable), getString(R.string.rx_Completable_des)));
        mDataList.add(new RxJavaDatas(getString(R.string.rx_Flowable), getString(R.string.rx_Flowable_des)));
    }


    private void loadGridView() {
        mAdapter = new RxJavaGridViewAdapter(this, mDataList);
        mGridView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, RxCreateActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, RxDeferActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, RxIntervalActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, RxRangeActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, RxMapActivity.class));
                break;
            case 5:
                startActivity(new Intent(this, RxBufferActivity.class));
                break;
            case 6:
                startActivity(new Intent(this, RxConcatActivity.class));
                break;
            case 7:
                startActivity(new Intent(this, RxMergeActivity.class));
                break;
            case 8:
                startActivity(new Intent(this, RxZipActivity.class));
                break;
            case 9:
                startActivity(new Intent(this, RxCombineLatestActivity.class));
                break;
            case 10:
                startActivity(new Intent(this, RxReduceActivity.class));
                break;
            case 11:
//                startActivity(new Intent(this, RxCollectActivity.class));
                break;
            case 12:
//                startActivity(new Intent(this, RxStartWithActivity.class));
                break;
            case 13:
//                startActivity(new Intent(this, RxCountActivity.class));
                break;
            case 14:
                startActivity(new Intent(this, RxDoXxxActivity.class));
                break;
            case 15:
                startActivity(new Intent(this, RxOnErrorXxxActivity.class));
                break;
            case 16:
                startActivity(new Intent(this, RxRetryXxxActivity.class));
                break;
            case 17:
                startActivity(new Intent(this, RxRepeatXxxActivity.class));
                break;

            case 18:
                startActivity(new Intent(this, RxFilterActivity.class));
                break;
//            case 19:
//                startActivity(new Intent(this, RxSingleActivity.class));
//                break;
//            case 22:
//                startActivity(new Intent(this, RxScanActivity.class));
//                break;
//            case 23:
//                startActivity(new Intent(this, RxWindowActivity.class));
//                break;
//            case 24:
//                startActivity(new Intent(this, RxPublishSubjectActivity.class));
//                break;
//            case 25:
//                startActivity(new Intent(this, RxAsyncSubjectActivity.class));
//                break;
//            case 26:
//                startActivity(new Intent(this, RxBehaviorSubjectActivity.class));
//                break;
//            case 27:
//                startActivity(new Intent(this, RxCompletableActivity.class));
//                break;
            case 26:
                startActivity(new Intent(this, RxFlowableActivity.class));
                break;

            default:
                break;
        }

    }

}
