package com.gotechcn.frameworks.common.rxjava;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gotechcn.frameworks.R;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

public class RxReduceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_reduce);
        reduceObserver();
    }

    @SuppressLint("CheckResult")
    private void reduceObserver() {
        Observable.just("1", "2", "3", "4").
                reduce(new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2) throws Exception {
                return s+s2;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e("pzm","reduce= "+s);
            }
        });
    }
}
