package com.gotechcn.frameworks.common.rxjava;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gotechcn.frameworks.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;


/**
 *
 *  获取一个被观察者对象,需要注意的是Subscriber和Observer都是观察者，
 *  二者没有本质的区别，都是用来接收被观察者发送过来的数据
 *  不同的是：
 *   Subscriber要与Flowable联合使用，Observer要与Observable联合使用，
 *   Obsesrver用于订阅Observable，而Subscriber用于订阅Flowable。
 *
 */

public class RxCreateActivity extends AppCompatActivity {

    Disposable mDisposable = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_create);
        createObservable();
        justObservable();
        formObservable();
//        doFlowableSomething();

    }

    /**
     * 注：整体方法调用顺序：观察者.onSubscribe（）> 被观察者.subscribe（）> 观察者.onNext（）>观察者.onComplete()
     *
     * onNext方法可以无限调用，Observer（观察者）所有的都能接收到，
     * onError和onComplete是互斥的，Observer（观察者）只能接收到一个，执行其中一个后，onNext()方法不会再调用
     * OnComplete可以重复调用，但是Observer（观察者）只会接收一次，
     * 而onError不可以重复调用，第二次调用就会报异常。
     */
    private void createObservable() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.e("pzm", "subscribe()");
                e.onNext("peng");
                e.onNext("zhi");
//                e.onNext(null);
//                e.onComplete();
//                e.onComplete();
//                e.onComplete();
                e.onNext("mao");

            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                /**
                 * 在该方法拿到Disposable，用于切断 被观察者与观察者的链接
                 */
                mDisposable = d;
                Log.e("pzm", "onSubscribe()");
            }

            @Override
            public void onNext(String s) {
                Log.e("pzm", s);
                if(s.equals("zhi")) {
                    Log.e("pzm", "我已经切断关联了");
                    mDisposable.dispose();
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e("pzm", "onError()");
            }

            @Override
            public void onComplete() {
                Log.e("pzm", "onComplete()");
            }
        });
    }


    /**
     * 一般处理10个以上的事件
     * 分为数组长度为0，1，大于1, 3种情况。
     * 为0时调用empty()方法； 为1时，调用接收 1个 参数的just方法。
     */
    @SuppressLint("CheckResult")
    private void formObservable() {
        //数组
        String [] strings = new String[]{"1","2","3"};
        Observable.fromArray(strings).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e("pzm", "form :s ===" + s);
            }
        });


        //集合
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Observable.fromIterable(list).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e("pzm", "form :integer ===" + integer);
            }
        });



        /**
         * 可以自定义重写观察者的回调方法
         */
        Observable.fromArray(strings).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        }, new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {

            }
        });

    }


    /**
     * Observable.just：接收1个以上,10个以下的参数，然后逐个发射。
     * just方法又多个重载方法，参数从1到10。just接收1个以上10个以下参数。
     * 以4个参数的just方法为例。just会对所有参数逐个判空。然后调用fromArray方法，除了只接收1个参数的just之外，just方法最终调用的是.fromArray
     */
    @SuppressLint("CheckResult")
    private void justObservable() {
        Observable.just(1,2,3).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e("pzm", "integer ===" + integer);
            }
        });

        Observable.just("1", "2", "3").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e("pzm", "s ===" + s);
            }
        });
    }



    /**
     * 这种方式，主要用于背压（即处理：发送的速度 > 解决的速度一种策略）
     */
    private void doFlowableSomething() {

        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(100);
                emitter.onNext(200);
            }
        }, BackpressureStrategy.BUFFER).subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                /**
                 * 这里必须设置一个值，才能正常执行onNext();
                 * 值为1：表示处理方式一个接一个
                 * 值大于1：可以处理的个数
                 */
                s.request(Integer.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                Log.e("pzm", integer.toString());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });

    }


}
