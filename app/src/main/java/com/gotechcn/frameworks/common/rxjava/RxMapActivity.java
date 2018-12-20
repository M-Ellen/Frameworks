package com.gotechcn.frameworks.common.rxjava;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gotechcn.frameworks.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RxMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_map);
        mapObserver();
        flatMapObserver();
        concatMapObserver();
    }


    @SuppressLint("CheckResult")
    private void mapObserver() {
        Observable.just(1,2,3)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return integer*10+"";
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e("pzm", "map" + s);
            }
        });
    }


    /**
     * flatMap 操作符可以将一个发射数据的 Observable 变换为多个 Observables ，
     * 然后将它们发射的数据合并后放到一个单独的 Observable.
     * 注意，flatMap不保证发送的顺序，如果需要保证发送的顺序，可见concatMap
     **/
    @SuppressLint("CheckResult")
    private void flatMapObserver(){
        Observable.just(1,2,3,4,5,6,7)
                .flatMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Integer integer) throws Exception {
                        List<Integer> list = new ArrayList<>();
                        for (int i = 0; i < 7; i++) {
                            list.add(integer);
                        }
                        //使用fromIterable()，遍历集合，发送每个item.多次自动调用onNext()方法，每次传入一个item.
                        return Observable.fromIterable(list).delay(1, TimeUnit.SECONDS);
                    }
                }).flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        return Observable.just(integer.toString());
                    }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e("pzm", "flatMap -> s =" + s);
            }
        });
    }


    /**
     * concatMap 操作符可以将一个发射数据的 Observable 变换为多个 Observables ，
     * 然后将它们发射的数据合并后放到一个单独的 Observable.
     * 注意: concatMap保证发送的顺序，如果不需要保证发送的顺序，可见flatMap
     **/
    @SuppressLint("CheckResult")
    private void concatMapObserver() {
        Observable.just(1,2,3,4,5,6,7)
                .concatMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Integer integer) throws Exception {
                        List<Integer> list = new ArrayList<>();
                        for (int i = 0; i < 7; i++) {
                            list.add(integer);
                        }
                        //使用fromIterable()，遍历集合，发送每个item.多次自动调用onNext()方法，每次传入一个item.
                        return Observable.fromIterable(list).delay(1, TimeUnit.SECONDS);
                    }
                }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                return Observable.just(integer.toString());
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e("pzm", "concatMap -> s =" + s);
            }
        });
    }
}
