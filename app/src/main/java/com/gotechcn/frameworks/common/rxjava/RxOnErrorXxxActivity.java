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
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;


/**
 * 在处理事件过程，处理错误的策略，错误发生并处理后，后面的事件不再执行
 *
 * 在前面也说到，有关于错误的处理：具体参考 RxConcatActivity -> concatArrayErrorObserver();
 */
public class RxOnErrorXxxActivity extends AppCompatActivity {

    private String TAG = "pzm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_on_error_xxx);
//        onErrorReturn();
//        onErrorResumeNext();
        onExceptionResumeNext();
    }

    /**
     * 遇到错误时，发送1个特殊事件 & 正常终止，后面不再执行
     */
    @SuppressLint("CheckResult")
    private void onErrorReturn() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onError(new Throwable("====错误==="));
                emitter.onNext("3");
            }
        })
                .onErrorReturn(new Function<Throwable, String>() {
                    @Override
                    public String apply(Throwable throwable) throws Exception {
                        return "99";
                    }
                }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.e("pzm", "onErrorReturn = " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("pzm", "onError = " + e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }


    /**
     *onErrorResumeNext（）拦截的错误 : Throwable；若需拦截Exception请用onExceptionResumeNext（）
     *若onErrorResumeNext（）拦截的错误 : Exception，则会将错误传递给观察者的onError方法,然后应用奔溃
     */
    private void onErrorResumeNext() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Throwable("Throwable 发生错误了"));
//                e.onError(new Exception("Exception 发生错误了"));
                e.onNext(3);
            }
        })
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Integer>>() {
                    @Override
                    public ObservableSource<? extends Integer> apply(@NonNull Throwable throwable) throws Exception {
                        // 1. 捕捉错误异常
                        Log.e(TAG, "在onErrorReturn处理了错误: "+throwable.toString() );
                        // 2. 发生错误事件后，发送一个新的被观察者 & 发送事件序列
                        return Observable.just(11,22);

                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "onErrorResumeNext = "+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });
    }


    /**
     * onExceptionResumeNext（）拦截的错误 : Exception；若需拦截Throwable请用onErrorResumeNext（）
     *
     * 若onExceptionResumeNext（）拦截的错误 : Throwable，则会将错误传递给观察者的onError方法,然后应用奔溃
     *
     */
    private void onExceptionResumeNext() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
//                e.onError(new Throwable("Throwable 发生错误了"));
                e.onError(new Exception("Exception 发生错误了"));
                e.onNext(3);
            }
        })
                .onExceptionResumeNext(new Observable<Integer>() {
                    @Override
                    protected void subscribeActual(Observer<? super Integer> observer) {
                        // 1. 捕捉错误异常
                        observer.onNext(11);
                        observer.onNext(22);
//                        observer.onComplete();
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "onExceptionResumeNext = "+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });
    }
}
