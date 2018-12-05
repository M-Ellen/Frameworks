# 介绍

Lottie 是一个用于 Android 和 iOS 的代码库，可以解析用 Adobe After Effects 制作动画后通过 Bodymovin 插件 导出的JSON数据文件，
并在移动端原生渲染! 最低支持 API 16

## 安装包

方法数：1084

安装包大小：68KB

这是用全民K歌release包的测试数据，lottie本身方法数不小，有方法数超标和安装包过大的风险，业务可自行评估

> 注：LottieAnimationView继承于V7的AppCompatImageView，需要引入V7兼容包，根据业务需要，可以源码引入Lottie，让LottieAnimationView继承与ImageView，就不用引入V7兼容包，可减小安装大小。

# 使用:

### 1. 在项目的 build.gradle 文件添加依赖:

    dependencies {
      implementation 'com.airbnb.android:lottie:$lottieVersion'
    }

> 最新版本可以点击这里查看 [版本](https://github.com/airbnb/lottie-android)


### 在xml中使用：

``` java

<!--使用 lottie_fileName 来加载文件-->
    <com.airbnb.lottie.LottieAnimationView
        android:id="@+id/animation_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:lottie_fileName="Fail.json"
        app:lottie_loop="true"
        app:lottie_autoPlay="true" />


    <!--使用 lottie_rawRes 来加载文件，！！！官方更推荐这种！！！-->
    <com.airbnb.lottie.LottieAnimationView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:lottie_rawRes="@raw/material_wave_loading"
        app:lottie_loop="true"
        app:lottie_autoPlay="true" />

```

### 在代码中使用：

``` java

        /**
         * 使用代码设置动画
         */
        final LottieAnimationView animationView = findViewById(R.id.animation_view);
        animationView.setAnimation(R.raw.material_wave_loading);//加载json文件
        animationView.setRepeatMode(LottieDrawable.REVERSE);//设置循环
//        animationView.setRepeatCount(0);//设置循环
        animationView.playAnimation(); //播放动画

```

### 常用方法


**animationView.loop(true)**  设置动画循环演示。  //已过期

animationView.setRepeatCount(-1)  设置动画循环演示

animationView.setRepeatMode(LottieDrawable.REVERSE) 设置重复模式

animationView.setAnimation( )  设置动画文件。

animationView.isAnimating()   是否在演示中。

animationView.setProgress(float progress)  设置演示的进度。progress在 [0,1]

animationView.getProgress()  获取演示的进度。

animationView.getDuration()  获取演示的时间。

animationView.palyAnimation()  运行动画。


animationView.pauseAnimation()  暂停动画。

animationView.cancleAnimation() 关闭动画。

pauseAniamtion()与cancleAnimation()的效果是一样，区别在于与cancleAnimation()多了一个监听回调，可以查看源码


如果想停止动画，并回到最初的样子：

    animationView.cancleAnimation();
    animationView.setProgress(0);

### 其他使用：

**具体参考下面文档**


# 文档学习：

git：https://github.com/airbnb/lottie-android

官方文档：http://airbnb.io/lottie/android/android.html#sample-app

动画商店：https://www.lottiefiles.com/

动画效果预览：https://nicolasjengler.github.io/bodymovins-json-tester/dist/

博客中文：https://github.com/bigxixi/lottie-android

总结性博客：
https://www.ui.cn/detail/199429.html

https://cloud.tencent.com/developer/article/1005896


*****https://www.cnblogs.com/plokmju/tag/Lottie/



注意事项：

1. 在未开启硬件加速的情况下，帧率、内存，CPU都比属性动画差，开启硬件加速后，性能差不多。

2. 如果没有遮罩、阴影和蒙版，那么性能和内存非常好，没有bitmap创建，大部分操作都是简单的cavas绘制。

3. 在列表中使用动画，推荐使用缓存，避免内存抖动

