package com.gotechcn.frameworks.common.rxjava;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gotechcn.frameworks.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RxRepeatXxxActivity extends AppCompatActivity {


    private String TAG = "pzm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_repeat_xxx);
        repeat();
//        repeatWhen();
    }


    /**
     * 无条件重复发送
     *注：
         1. 接收到.onCompleted()事件后，触发重新订阅 & 发送
         2. 默认运行在一个新的线程上

     * repeat（）；重复发送次数 = 无限次
       repeatWhen（Integer int ）；重复发送次数有限
     */
    @SuppressLint("CheckResult")
    private void repeat() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        })
                .repeat(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "repeat-> integer= "+ integer  );
                    }
                });
    }


    /**
     * 有条件重复发送
     *
     * 将原始 Observable 停止发送事件的标识（Complete（） / Error（））转换成1个 Object 类型数据传递给1个新被观察者（Observable），
     * 以此决定是否重新订阅 & 发送原来的 Observable
     */
    @SuppressLint("CheckResult")
    private void repeatWhen() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        })
                .repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                        // 此处有2种情况：
                        // 1. 若新被观察者（Observable）返回1个Complete（） /  Error（）事件，则不重新订阅 & 发送原来的 Observable
                        // 2. 若新被观察者（Observable）返回其余事件，则重新订阅 & 发送原来的 Observable
                        return objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(@NonNull Object throwable) throws Exception {
                                // 情况1：若新被观察者（Observable）返回1个Complete（） /  Error（）事件，则不重新订阅 & 发送原来的 Observable
                                return Observable.empty();
                                // Observable.empty() = 发送Complete事件，但不会回调观察者的onComplete（）

                                // return Observable.error(new Throwable("不再重新订阅事件"));
                                // 返回Error事件 = 回调onError（）事件，并接收传过去的错误信息。

                                // 情况2：若新被观察者（Observable）返回其余事件，则重新订阅 & 发送原来的 Observable
                                // return Observable.just(1);
                                // 仅仅是作为1个触发重新订阅被观察者的通知，发送的是什么数据并不重要，只要不是Complete（） /  Error（）事件
                            }
                        });
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "retryWhen -> integer= "+ integer);
                    }
                });
    }
}
