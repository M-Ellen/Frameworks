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
import io.reactivex.schedulers.Schedulers;

/**
 * 用于 每隔指定时间 发送 事件
 */
public class RxIntervalActivity extends AppCompatActivity {

    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_interval);
//        timerObserver();
//        intervalObserver();
        intervalRangObserver();
    }



    private void intervalRangObserver() {
        /**
         *   // 参数说明：
             // 参数1 = 事件序列起始点；
             // 参数2 = 事件数量；
             // 参数3 = 第1次事件延迟发送时间；
             // 参数4 = 间隔时间数字；
             // 参数5 = 时间单位
         */
        Observable.intervalRange(0,60, 0,1,TimeUnit.SECONDS, Schedulers.io())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("pzm", "onSubscribe");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.e("pzm", "aLong = " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("pzm", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("pzm", "onComplete");
                    }
                });
    }

    /**
     * 类似定时器，可以多次发送事件, 已包含 timer的功能也有
     */
    @SuppressLint("CheckResult")
    private void intervalObserver() {
        dispose();
        /**
         *  参数说明：
         *  参数1 = 第1次延迟时间；
         *  参数2 = 间隔时间数字；
         *  参数3 = 时间单位；
         *  参数4 = 线程调度
         */
        mDisposable = Observable.interval(10,1,TimeUnit.SECONDS, Schedulers.newThread())
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e("pzm","interval =="+ aLong);
                        if(aLong == 60) {
                            mDisposable.dispose();
                        }
                    }
                });

    }


    /**
     * 指定延迟时间，可以发送一次事件
     */
    @SuppressLint("CheckResult")
    private void timerObserver() {
        Observable.timer(10, TimeUnit.SECONDS, Schedulers.io())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e("pzm","timer =="+ aLong);
                    }
                });
    }

    private void dispose(){
        if(mDisposable != null && mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
