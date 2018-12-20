package com.gotechcn.frameworks.common.rxjava;

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
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;


public class RxRetryXxxActivity extends AppCompatActivity {

    private String TAG = "pzm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_retry_xxx);
        retry();
        retryUntil();
        retryWhen();
    }

    /**
     * <-- 1. retry（） -->
      作用：出现错误时，让被观察者重新发送数据
      注：若一直错误，则一直重新发送

     <-- 2. retry（long time） -->
      作用：出现错误时，让被观察者重新发送数据（具备重试次数限制
      参数 = 重试次数

     <-- 3. retry（Predicate predicate） -->
     // 作用：出现错误后，判断是否需要重新发送数据（若需要重新发送& 持续遇到错误，则持续重试）
     // 参数 = 判断逻辑

     <--  4. retry（new BiPredicate<Integer, Throwable>） -->
      作用：出现错误后，判断是否需要重新发送数据（若需要重新发送 & 持续遇到错误，则持续重试
      参数 =  判断逻辑（传入当前重试次数 & 异常错误信息）

     <-- 5. retry（long time,Predicate predicate） -->
      作用：出现错误后，判断是否需要重新发送数据（具备重试次数限制
      参数 = 设置重试次数 & 判断逻辑
     */
    private void retry() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Exception("发生错误了"));
                e.onNext(3);
            }
        })
                // 遇到错误时，让被观察者重新发射数据（若一直错误，则一直重新发送)
//                .retry()
//                .retry(new BiPredicate<Integer, Throwable>() {
//                    @Override
//                    public boolean test(Integer integer, Throwable throwable) throws Exception {
//                        Log.e(TAG, "retry (BiPredicate)  integer = " + integer);
//                        //返回false = 不重新重新发送数据 & 调用观察者的onError结束
//                        //返回true = 重新发送请求（若持续遇到错误，就持续重新发送）
//                        if(integer == 3) {
//                            return false;
//                        }
//                        return true;
//                    }
//                })
                .retry(3, new Predicate<Throwable>() {
                    @Override
                    public boolean test(Throwable throwable) throws Exception {
                        Log.e(TAG, "retry (3, Predicate)");
                        return true;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件"+ value  );
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
     * 与 retry（Predicate predicate） 方法类似
     * 区别是，返回值对应的处理不同
     */
    private void retryUntil() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Exception("发生错误了"));
                e.onNext(3);
            }
        })
                .retryUntil(new BooleanSupplier() {
                    @Override
                    public boolean getAsBoolean() throws Exception {
                        // 注意：是返回 true 则不重新发送数据事件
                        return false;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件"+ value  );
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
     * 遇到错误时，将发生的错误传递给一个新的被观察者（Observable），并决定是否需要重新订阅原始被观察者（Observable）& 发送事件
     */
    private void retryWhen() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onError(new Exception("发生错误了"));
                e.onNext(3);
            }
        })
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                        // 参数Observable<Throwable>中的泛型 = 上游操作符抛出的异常，可通过该条件来判断异常的类型
                        // 返回Observable<?> = 新的被观察者 Observable（任意类型）
                        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(@NonNull Throwable throwable) throws Exception {
                                /**
                                 * 1. 若返回的Observable发送的事件 = Error事件，则原始的Observable不重新发送事件
                                 * 该异常错误信息可在观察者中的onError（）中获得
                                 */
                                return Observable.error(new Throwable("retryWhen终止啦"));

                                /**
                                 * 2. 若返回的Observable发送的事件 = Next事件，则原始的Observable重新发送事件（若持续遇到错误，则持续重试）
                                 *
                                 */
//                                return Observable.just(1);
                            }
                        });
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件"+ value  );
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
