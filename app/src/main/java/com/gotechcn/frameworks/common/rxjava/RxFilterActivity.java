package com.gotechcn.frameworks.common.rxjava;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gotechcn.frameworks.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class RxFilterActivity extends AppCompatActivity {

    private String TAG  = "pzm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_filter);
//        filterObserver();
//        ofTypeObserver();
        skipAndskipLast();
//        distinctAndDistinctUntilChanged();
//        takeAndTakeLast();
//        throttleFirstAndThrottleLast();
//        sample();
//        throttleWithTimeoutAndDebounce();
//        firstElementAndLastElement();
//        elementAt();
//        elementAtOrError();
    }

    /**
     * 在elementAt（）的基础上，当出现越界情况（即获取的位置索引 ＞ 发送事件序列长度）时，即抛出异常
     */
    private void elementAtOrError() {

    }

    /**
     * 获取指定的事件
     */
    private void elementAt() {

    }

    /**
     * 仅选取第1个事件 / 最后一个事件
     */
    private void firstElementAndLastElement() {

    }

    /**
     * 发送数据事件时，若2次发送事件的间隔 ＜ 指定时间，就会丢弃前一次的数据，直到指定时间内都没有新数据发射时才会发送后一次的数据
     */
    private void throttleWithTimeoutAndDebounce() {

    }

    /**
     * 在某段时间内，只发送该段时间内最新（最后）1次事件
     */
    private void sample() {

    }

    /**
     * 在某段时间内，只接受该段时间内第1次事件 / 最后1次事件
     */
    private void throttleFirstAndThrottleLast() {

    }

    private void filterObserver(){
        Observable.just(10, -1, 5, -5, 0, 1000, -40).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer > 0;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.e("pzm","onNext:"+integer);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    /**
     * 过滤 特定数据类型的数据
     */
    @SuppressLint("CheckResult")
    private void ofTypeObserver() {
        Observable.just(1, "peng", 3, "zhi", 5)
                // 筛选出 整型数据
                .ofType(Integer.class)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept( Integer integer) throws Exception {
                        Log.d("pzm","获取到的整型事件元素是： "+ integer);
                    }
                });
    }


    /**
     * 跳过某个事件
     */
    @SuppressLint("CheckResult")
    private void skipAndskipLast() {
        // 使用1：根据顺序跳过数据项
        Observable.just(1, 2, 3, 4, 5)
                .skip(1) // 跳过正序的前1项
                .skipLast(2) // 跳过正序的后2项
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept( Integer integer) throws Exception {
                        Log.e("pzm","获取到的整型事件元素是： "+ integer);
                    }
                });

        // 使用2：根据时间跳过数据项
        // 发送事件特点：发送数据0-5，每隔1s发送一次，每次递增1；第1次发送延迟0s
        Observable.intervalRange(0, 5, 0, 1, TimeUnit.SECONDS)
                .skip(1, TimeUnit.SECONDS) // 跳过第1s发送的数据
                .skipLast(1, TimeUnit.SECONDS) // 跳过最后1s发送的数据
                .subscribe(new Consumer<Long>() {

                    @Override
                    public void accept( Long along ) throws Exception {
                        Log.e("pzm","获取到的整型事件元素是： "+ along);
                    }
                });
    }

    /**
     *  通过设置指定的事件数量(或者时间)，仅发送特定数量的事件
     */
    @SuppressLint("CheckResult")
    private void takeAndTakeLast() {
        Observable.just(1, 2, 3, 1 , 2 )
                .take(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept( Integer integer) throws Exception {
                        Log.d(TAG,"take-> integer ="+ integer);
                    }
                });
        Observable.just(1, 2, 3, 1 , 2 )
                .takeLast(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept( Integer integer) throws Exception {
                        Log.d(TAG,"takeLast-> integer "+ integer);
                    }
                });
    }

    /**
     * 过滤事件序列中重复的事件 / 连续重复的事件
     */
    @SuppressLint("CheckResult")
    private void distinctAndDistinctUntilChanged() {
        Observable.just(1, 2, 3, 1 , 2 )
                .distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept( Integer integer) throws Exception {
                        Log.d(TAG,"distinct-> integer= "+ integer);
                    }
                });

        // 使用2：过滤事件序列中 连续重复的事件
        // 下面序列中，连续重复的事件 = 3、4
        Observable.just(1,2,3,1,2,3,3,4,4 )
                .distinctUntilChanged()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept( Integer integer) throws Exception {
                        Log.d(TAG,"distinctUntilChanged-> integer= "+ integer);
                    }
                });
    }




}
