package com.gotechcn.frameworks;

import android.app.Application;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;

import com.orhanobut.logger.*;
import com.orhanobut.logger.BuildConfig;

import java.io.File;


/**
 * user： pzm    date： 2017/10/28
 */

public class MyApplication extends Application {

    private static MyApplication mApplication;

    public static MyApplication getInstance() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;

        Logger.addLogAdapter(new AndroidLogAdapter());
        /**
         * 自定义配置
         */
//        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
//                .methodCount(6)
//                .methodOffset(0)
//                .tag("MyTAG")
//                .build();
//        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));

        /**
         * 控制打印开关
         */
//        Logger.addLogAdapter(new AndroidLogAdapter(){
//            @Override
//            public boolean isLoggable(int priority, String tag) {
//                return BuildConfig.DEBUG;
//            }
//        });

        /**
         * 保存log到文件
         */
//        Logger.addLogAdapter(new DiskLogAdapter());
//
//
//        FormatStrategy formatStrategy = CsvFormatStrategy.newBuilder()
//                .build();
//
//        Logger.addLogAdapter(new DiskLogAdapter(formatStrategy));


    }
}
