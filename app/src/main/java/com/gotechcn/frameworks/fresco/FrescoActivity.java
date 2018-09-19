package com.gotechcn.frameworks.fresco;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;
import com.gotechcn.frameworks.R;

/**
 * @author pzm
 */
public class FrescoActivity extends Activity {

    private static final String[] FRESCO_TYPE = {"加载图","占位图","占位图","占位图","占位图","占位图"};
    Uri uri1 = Uri.parse("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2944094007,2505799703&fm=27&gp=0.jpg");
//    Uri uri1 = Uri.parse("http://pic.58pic.com/58pic/11/29/93/88c58PICKZ4.jpg");



    GenericDraweeHierarchyBuilder mDraweeHierarchyBuilder;
    /**
     * 不能复用
     */
    GenericDraweeHierarchy mDraweeHierarchy;
    DraweeController mDraweeController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco);
//        Spinner spinner = (Spinner) findViewById(R.id.spinner);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, FRESCO_TYPE);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(this);

        initData();

        /**
         * 通过布局设置基本属性
         */
        setPlaceholderImage();
        setProgressBarImage();
        setOverlayImage();

        /**
         * 通过代码设置基本属性
         */
        testImage();
        testGifImage();
    }

    private void initData(){

    }

    /**
     * 设置占位图
     */
    private void setPlaceholderImage(){
        SimpleDraweeView draweeView1 = (SimpleDraweeView) findViewById(R.id.my_image_view1);
        mDraweeController = Fresco.newDraweeControllerBuilder()
                .setTapToRetryEnabled(true)
                .build();
        draweeView1.setImageURI("1111");
        draweeView1.setController(mDraweeController);
    }


    /**
     * 设置加载进度占位图
     */
    private void setProgressBarImage(){
        SimpleDraweeView draweeView4 = (SimpleDraweeView) findViewById(R.id.my_image_view2);
        draweeView4.setImageURI(uri1);
    }

    /**
     * 设置叠加图 占位图
     */
    private void setOverlayImage(){
        SimpleDraweeView draweeView3 = (SimpleDraweeView) findViewById(R.id.my_image_view3);
        draweeView3.setImageURI(uri1);
    }


    /**
     *使用代码 设置基本属性
     */
    private void testImage(){
        SimpleDraweeView draweeView4 = (SimpleDraweeView) findViewById(R.id.my_image_view4);

        mDraweeHierarchyBuilder = new GenericDraweeHierarchyBuilder(getResources());

        /**
         * 设置圆形|圆角属性
         */
        RoundingParams roundingParams = RoundingParams.asCircle();                          //设置圆形
        roundingParams.setOverlayColor(getResources().getColor(R.color.bg_color_blue));     //设置底下的叠加颜色

        roundingParams = RoundingParams.fromCornersRadius(20);                              //设置圆角（可以设置不同大小的4个圆角）
        roundingParams = RoundingParams.fromCornersRadii(10,20,30,40);

        mDraweeHierarchy = mDraweeHierarchyBuilder
                .setPlaceholderImage(R.mipmap.fresco_placeholder_image)                     //设置占位符
                .setBackground(getResources().getDrawable(R.color.bg_color_blue))           //设置背景
                .setFailureImage(R.mipmap.fresco_failure_image)                             //设置失败的占位符
                .setRetryImage(R.mipmap.fresco_retry_image)                                 //设置重新加载的占位符
                .setProgressBarImage(new ProgressBarDrawable())                             //设置加载进度条
//                .setOverlay(getResources().getDrawable(R.mipmap.bitmap6))                 //设置叠加占位图
                .setPressedStateOverlay(getResources().getDrawable(R.color.colorAccent))    //设置按压状态下的叠加图
//                .setActualImageScaleType(ScalingUtils.ScaleType.FOCUS_CROP)               //设置缩放类型，几乎和ImageView一致
//                .setActualImageFocusPoint(new PointF(0f, 0f))                             //如果设置了FOCUS_CROP类型，需要设置缩放的中心点
                .setRoundingParams(roundingParams)                                          //设置圆形|圆角 属性
                .build();


        Postprocessor redMeshPostprocessor = new BasePostprocessor() {
            @Override
            public String getName() {
                return "redMeshPostprocessor";
            }

            @Override
            public void process(Bitmap bitmap) {
                for (int x = 0; x < bitmap.getWidth(); x+=2) {
                    for (int y = 0; y < bitmap.getHeight(); y+=2) {
                        bitmap.setPixel(x, y, Color.RED);
                    }
                }
            }
        };

        /**
         * 设置图片的参数
         */
        Uri uri = Uri.parse("http://pic.58pic.com/58pic/11/29/93/88c58PICKZ4.jpg");
        Uri highturi = Uri.parse("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3912703204,2657880096&fm=26&gp=0.jpg");
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(highturi)
                .setProgressiveRenderingEnabled(true)                                       //设置渐进式（只支持网络的JPEG图）
                .setPostprocessor(redMeshPostprocessor)                                     //设置后处理，修改图片
                .build();

        DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                .setUri(highturi)
                .setTapToRetryEnabled(true)                                                 //是否支持重新加载
                .setOldController(draweeView4.getController())                              //设置旧的控制器（可节省不必要的内存分配）
                .setImageRequest(imageRequest)                                              //设置图片请求参数
//                .setLowResImageRequest(ImageRequest.fromUri(uri))                           //先加载分辨率较低的图片（多图请求）
//                .setImageRequest(ImageRequest.fromUri(highturi))
                .build();

        draweeView4.setHierarchy(mDraweeHierarchy);
        draweeView4.setController(mDraweeController);
    }


    /**
     * gif 动画
     * 不支持圆形|圆角
     */
    private void testGifImage(){
        SimpleDraweeView gifImage = (SimpleDraweeView) findViewById(R.id.gif_image);

        /**
         * 监听器
         */
        ControllerListener controllerListener  = new BaseControllerListener(){
            /**
             * 图片加载成功
             * @param id
             * @param imageInfo
             * @param animatable
             */
            @Override
            public void onFinalImageSet(String id, @Nullable Object imageInfo, @Nullable Animatable animatable) {
                super.onFinalImageSet(id, imageInfo, animatable);
                if(animatable != null) {
                    animatable.start();  //开始动画
                }
            }

            /**
             * 图片加载失败
             * @param id
             * @param throwable
             */
            @Override
            public void onFailure(String id, Throwable throwable) {
                super.onFailure(id, throwable);
            }


            /**
             * 如果允许呈现渐进式JPEG，同时图片也是渐进式图片，onIntermediateImageSet会在每个扫描被解码后回调
             * @param id
             * @param imageInfo
             */
            @Override
            public void onIntermediateImageSet(String id, @Nullable Object imageInfo) {
                super.onIntermediateImageSet(id, imageInfo);
            }

            /**
             * 同上  加载失败
             * @param id
             * @param throwable
             */
            @Override
            public void onIntermediateImageFailed(String id, Throwable throwable) {
                super.onIntermediateImageFailed(id, throwable);
            }
        };

        //本地
        Uri GIF_URI = Uri.parse("res://" + getPackageName() + "/" + R.drawable.icon_gif);

        //网络
        Uri GIF_URI1 = Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536896390714&di=4ada82eec7aa5ec3387036ff434f490c&imgtype=0&src=http%3A%2F%2Fimg5.duitang.com%2Fuploads%2Fitem%2F201409%2F22%2F20140922013938_NZfBs.thumb.700_0.gif");


        mDraweeController = Fresco.newDraweeControllerBuilder()
                .setUri(GIF_URI1)
//                .setAutoPlayAnimations(true)                                           //自动播放动画
                .setControllerListener(controllerListener)                               //设置控制器监听
                .setOldController(gifImage.getController())                              //设置旧的控制器（可节省不必要的内存分配）
                .build();

        gifImage.setController(mDraweeController);
    }

//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        switch (position) {
//            case 0 :
//
//                break;
//            case 1 :
//
//                break;
//            case 2 :
//
//                break;
//            case 3 :
//
//                break;
//            case 4 :
//
//                break;
//            default:
//                break;
//        }
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
}
