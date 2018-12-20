package com.gotechcn.frameworks.common.rxjava;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gotechcn.frameworks.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

public class RxZipActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_zip);
        zipObserver();
    }



    @SuppressLint("CheckResult")
    private void zipObserver(){
        Observable.zip(getStringObservable(), getIntegerObservable(), new BiFunction<String, Integer, String>() {
            @Override
            public String apply(String s, Integer integer) throws Exception {
                return s + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e("pzm","zip=  "+s);

            }
        });

    }

    private Observable<String> getStringObservable(){
        final String[] stringArray = {"String A", "String B", "String C", "String D", "String E"};
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                for(int i=0; i<stringArray.length; i++){
                    if(e.isDisposed()){
                        break;
                    }else{
                        e.onNext(stringArray[i]);
                    }
                }
            }
        });

        return observable;
    }

    private Observable<Integer> getIntegerObservable() {
        final int[] integerArray = {1, 2, 3, 4, 5, 6, 7};
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0; i < integerArray.length; i++) {
                    if (e.isDisposed()) {
                        break;
                    } else {
                        e.onNext(integerArray[i]);
                    }
                }

            }
        });
        return observable;
    }
}
