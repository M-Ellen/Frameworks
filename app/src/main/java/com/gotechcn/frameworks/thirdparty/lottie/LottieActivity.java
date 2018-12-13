package com.gotechcn.frameworks.thirdparty.lottie;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.gotechcn.frameworks.R;

public class LottieActivity extends FragmentActivity implements View.OnClickListener {

    private Button mStopBtn;
    private Button mResetBtn;
    private Button mSpeedBtn;

    LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie);

        mStopBtn = findViewById(R.id.btn_stop);
        mResetBtn = findViewById(R.id.btn_reset);
        mSpeedBtn = findViewById(R.id.btn_speed);
        mStopBtn.setOnClickListener(this);
        mResetBtn.setOnClickListener(this);
        mSpeedBtn.setOnClickListener(this);

        /**
         * 使用代码设置动画
         */
//        animationView = findViewById(R.id.animation_view);
//        animationView.setAnimation(R.raw.material_wave_loading);//加载json文件
//        animationView.setRepeatMode(LottieDrawable.REVERSE);//设置循环
//        animationView.setRepeatCount(-1);//设置循环
//        animationView.playAnimation(); //播放动画
//
//        /**
//         * 添加监听
//         */
//        animationView.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                Log.e("pzm", "onAnimationUpdate");
//            }
//        });


//        /**
//         * 异步加载
//         */
//        LottieComposition.Factory.fromRawFile(this, R.raw.material_wave_loading, new OnCompositionLoadedListener() {
//            @Override
//            public void onCompositionLoaded(@Nullable LottieComposition composition) {
//                animationView.setComposition(composition);
//                animationView.setRepeatMode(LottieDrawable.REVERSE);//设置循环
//                animationView.setRepeatCount(0);//设置循环
//                animationView.playAnimation(); //播放动画
//            }
//        });
//


        /**
         * 开启硬件加速
         */
//        animationView.useHardwareAcceleration(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_stop :
                if(animationView.isAnimating()) {
                    animationView.pauseAnimation();
                }else {
                    animationView.playAnimation();
                }
                break;
            case R.id.btn_reset :
                animationView.cancelAnimation();
                animationView.setProgress(0);
                break;
            case R.id.btn_speed :

                if(!animationView.isAnimating()) {
                    animationView.playAnimation();
                }
                animationView.setSpeed(3);

                break;
            default:
                break;
        }
    }
}
