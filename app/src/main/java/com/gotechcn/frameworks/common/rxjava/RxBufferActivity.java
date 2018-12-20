package com.gotechcn.frameworks.common.rxjava;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gotechcn.frameworks.R;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class RxBufferActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_buffer);
        bufferObserver();
    }

    @SuppressLint("CheckResult")
    private void bufferObserver() {
        Observable.just(1,2,3,4,5)
                /**
                 * 参数说明：
                 * 参数1：缓存的数量
                 * 参数2：步长，每次重新获取新事件的数量
                 */
                .buffer(3,2)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        Log.e("pzm", "buffer -> integers =" + integers);
                    }
                });


        /**
         * 待理解。。。
         */
        Observable.just(1,2,3,4,5)
                .buffer(20,10, TimeUnit.SECONDS)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        Log.e("pzm", "buffer(timer) -> integers =" + integers);
                    }
                });
    }
}
