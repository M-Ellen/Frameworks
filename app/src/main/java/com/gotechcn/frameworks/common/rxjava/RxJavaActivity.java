package com.gotechcn.frameworks.common.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gotechcn.frameworks.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class RxJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
    }


    private void text1(){
        String[] names = {"peng", "zhi", "mao"};

        /**
         * 创建被观察者
         */
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("peng");
                emitter.onNext("zhi");
                emitter.onNext("mao");
                emitter.onComplete();
            }
        });
    }


//    public static void hello(String... names) {
//        Observable.from(names).subscribe(new Action1<String>() {
//
//            @Override
//            public void call(String s) {
//                System.out.println("Hello " + s + "!");
//            }
//
//        });
//    }
}
