package com.gotechcn.frameworks.common.rxjava;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gotechcn.frameworks.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

/**
 * 组合多个被观察者一起发送数据，合并后 按发送顺序串行执行
 */
public class RxConcatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_concat);
        concatObserver();
        concatArrayObserver();
//        concatArrayErrorObserver();
    }


    /**
     * 组合被观察者数量 ≤ 4个
     */
    @SuppressLint("CheckResult")
    private void concatObserver() {
        Observable.concat(
                Observable.just(1, 2, 3),
                Observable.just(4, 5, 6),
                Observable.just(7, 8, 9),
                Observable.just(10, 11, 12))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e("pzm", "concat -> integer " + integer);
                    }
                });
    }


    /**
     * 组合被观察者数量 > 4个
     */
    @SuppressLint("CheckResult")
    private void concatArrayObserver() {
        Observable.concatArray(
                // 从0开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
                Observable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS),
                Observable.intervalRange(3, 3, 1, 1, TimeUnit.SECONDS),
                Observable.intervalRange(6, 3, 1, 1, TimeUnit.SECONDS),
                Observable.intervalRange(9, 3, 1, 1, TimeUnit.SECONDS),
                Observable.intervalRange(12, 3, 1, 1, TimeUnit.SECONDS))
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e("pzm", "concatArray -> aLong " + aLong);
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
    @SuppressLint("CheckResult")
    private void concatArrayErrorObserver() {
        Observable.concatArrayDelayError(
                Observable.just(1, 2, 3),
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(4);
                        emitter.onNext(5);
                        emitter.onError(new NullPointerException());
                        emitter.onNext(6);
                    }
                }),
                Observable.just(7, 8, 9),
                Observable.just(10, 11, 12))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e("pzm", "concat -> integer " + integer);
                    }
                });
    }

}
