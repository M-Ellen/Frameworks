package com.gotechcn.frameworks.common.rxjava;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gotechcn.frameworks.R;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class RxRangeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_range);
        rangeObserver();
        rangeLongObserver();
    }

    @SuppressLint("CheckResult")
    private void rangeObserver() {
        /**
         * 参数说明：
         * 参数1：指定起始的位置
         * 参数2：需要发送的次数
         * 注：若设置为负数，则会抛出异常
         */
        Observable.range(3, 10)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e("pzm", "range -> integer= " + integer);
                    }
                });
    }


    /**
     * 和 range()方法一样，只是可以支持long类型
     */
    @SuppressLint("CheckResult")
    private void rangeLongObserver() {
        Observable.rangeLong(9999994,5)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e("pzm", "rangeLong -> Long= " + aLong);
                    }
                });
    }
}
