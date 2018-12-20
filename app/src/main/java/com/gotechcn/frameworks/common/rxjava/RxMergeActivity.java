package com.gotechcn.frameworks.common.rxjava;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gotechcn.frameworks.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;


/**
 * 组合多个被观察者一起发送数据，合并后 按时间线并行执行
 */
public class RxMergeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_merge);
        mergeObserver();
        mergeArrayObserver();
        mergeArrayDelayErrorObserver();
    }

    /**
     * 组合被观察者数量 ≤ 4个
     */
    @SuppressLint("CheckResult")
    private void mergeObserver() {
        Observable.merge(
                // 从0开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
                Observable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS),
                Observable.intervalRange(3, 3, 1, 1, TimeUnit.SECONDS))
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e("pzm", "merge -> aLong " + aLong);
                    }
                });
    }


    /**
     * 组合被观察者数量 > 4个
     */
    @SuppressLint("CheckResult")
    private void mergeArrayObserver() {
        Observable.mergeArray(
                // 从0开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
                Observable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS),
                Observable.intervalRange(3, 3, 1, 1, TimeUnit.SECONDS),
                Observable.intervalRange(6, 3, 1, 1, TimeUnit.SECONDS),
                Observable.intervalRange(9, 3, 1, 1, TimeUnit.SECONDS),
                Observable.intervalRange(12, 3, 1, 1, TimeUnit.SECONDS))
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e("pzm", "mergeArray -> aLong " + aLong);
                    }
                });
    }


    /**
     * concatDelayError
     * concatArrayDelayError
     * 如果使用没有带“Error”的整合，那么，中途遇到错误，后面单独的事件不会继续执行
     * 如果使用带“Error”的整合，那么，中途遇到错误，后面单独的事件继续执行
     *
     */
    private void mergeArrayDelayErrorObserver() {

        //具体参考 RxConcatActivity -> concatArrayErrorObserver();
    }
}
