package com.gotechcn.frameworks.common.volley;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gotechcn.frameworks.R;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VolleyActivity extends Activity implements View.OnClickListener {

    private Button mBtnGet;
    private Button mBtnPost;
    private Button mBtnJson;
    private Button mBtnImageRequest;
    private Button mBtnImageLoader;
    private Button mBtnNetWorkImage;
    private Button mBtnXml;

    private TextView mTvResult;
    private ImageView mIamgeResult;
    private NetworkImageView mNetworkImageResult;

    RequestQueue requestQueue = null;
    String url = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        initView();
        initData();
        setListener();
    }

    private void initData() {
        requestQueue = Volley.newRequestQueue(this);
        url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
    }

    private void setListener() {
        mBtnGet.setOnClickListener(this);
        mBtnPost.setOnClickListener(this);
        mBtnJson.setOnClickListener(this);
        mBtnImageRequest.setOnClickListener(this);
        mBtnImageLoader.setOnClickListener(this);
        mBtnNetWorkImage.setOnClickListener(this);
        mBtnXml.setOnClickListener(this);
    }

    private void initView() {
        mTvResult = (TextView) findViewById(R.id.tv_volley_result);
        mBtnGet = (Button) findViewById(R.id.btn_volley_get);
        mBtnPost = (Button) findViewById(R.id.btn_volley_post);
        mBtnJson = (Button) findViewById(R.id.btn_volley_json);
        mBtnImageRequest = (Button) findViewById(R.id.btn_volley_imagerequest);
        mBtnImageLoader = (Button) findViewById(R.id.btn_volley_imageloader);
        mBtnNetWorkImage = (Button) findViewById(R.id.btn_volley_networkImageView);
        mBtnXml = (Button) findViewById(R.id.btn_volley_xml);
        mIamgeResult = (ImageView) findViewById(R.id.iv_volley_result);
        mNetworkImageResult = (NetworkImageView) findViewById(R.id.iv_volley_networkresult);
    }

    private void hideImage(){
        mIamgeResult.setVisibility(View.GONE);
        mNetworkImageResult.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_volley_get:
                //1. 创建�?个RequestQueue对象�?
                //2. 创建�?个StringRequest对象�?
                //3. 将StringRequest对象添加到RequestQueue里面�?
                StringRequest stringRequest = new StringRequest(
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                mTvResult.setText(s);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                mTvResult.setText("loading error�?" + volleyError);
                            }
                        });
                requestQueue.add(stringRequest);
                hideImage();


                break;
            case R.id.btn_volley_post:
                StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                mTvResult.setText(s);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                mTvResult.setText("loading error�?" + volleyError);
                            }
                        })
                        //post请求添加参数，需要重写该方法（匿名）
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
//                        map.put("key","valuse");
                        return super.getParams();
                    }
                };
                requestQueue.add(stringRequest1);
                hideImage();
                break;
            case R.id.btn_volley_json:
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                mTvResult.setText(jsonObject.toString());

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                mTvResult.setText("loading error�?" + volleyError);
                            }
                        });
                requestQueue.add(jsonObjectRequest);
                hideImage();
                break;

            case R.id.btn_volley_imagerequest:
                mIamgeResult.setVisibility(View.VISIBLE);
                String imageurl = "http://img5.mtime.cn/mg/2016/10/11/160347.30270341.jpg";
                ImageRequest imageRequest = new ImageRequest(imageurl,
                        new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap bitmap) {
                                mIamgeResult.setImageBitmap(bitmap);

                            }
                        }, 0, 0, Bitmap.Config.RGB_565,
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                mIamgeResult.setImageResource(R.mipmap.ic_launcher);
                            }
                        });
                requestQueue.add(imageRequest);

                break;
            case R.id.btn_volley_imageloader:
                mIamgeResult.setVisibility(View.VISIBLE);
//                ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
//                    @Override
//                    public Bitmap getBitmap(String s) {
//                        return null;
//                    }
//
//                    @Override
//                    public void putBitmap(String s, Bitmap bitmap) {
//
//                    }
//                });

                // add cache mechanism
                ImageLoader imageLoader = new ImageLoader(requestQueue, new MyImageCache());

//              String url1 = "http://img5.mtime.cn/mg/2016/10/11/160347.30270341.jpg";
                String url1 = "http://scimg.jb51.net/allimg/150629/14-1506291A242927.jpg";
                imageLoader.get(url1, imageLoader.getImageListener(mIamgeResult, R.mipmap.ic_launcher, R.mipmap.ic_launcher));

                break;
            case R.id.btn_volley_networkImageView:
                mNetworkImageResult.setVisibility(View.VISIBLE);
//                1. 创建�?个RequestQueue对象�?
//                2. 创建�?个ImageLoader对象�?
//                3. 在布�?文件中添加一个NetworkImageView控件�?
//                4. 在代码中获取该控件的实例�?
//                5. 设置要加载的图片地址�?
                String networkUrl = "http://img01.taopic.com/150522/267818-15052206394474.jpg";
                ImageLoader imageLoader2 = new ImageLoader(requestQueue, new MyImageCache());
                mNetworkImageResult.setDefaultImageResId(R.mipmap.ic_launcher);
                mNetworkImageResult.setErrorImageResId(R.mipmap.ic_launcher);
                mNetworkImageResult.setImageUrl(networkUrl, imageLoader2);

                break;
            case R.id.btn_volley_xml:
                String xmlUrl = "http://flash.weather.com.cn/wmaps/xml/china.xml";
                XMlRequest xMlRequest = new XMlRequest(xmlUrl,
                        new Response.Listener<XmlPullParser>() {
                            @Override
                            public void onResponse(XmlPullParser xmlPullParser) {
//                                mTvResult.setText(xmlPullParser.toString());
//                                try {
//                                    xmlPullParser.setInput(null,"UTF-8");
//                                } catch (XmlPullParserException e) {
//                                    e.printStackTrace();
//                                }
                                List<String> lists = new ArrayList<>();
                                try {
                                    int eventType = xmlPullParser.getEventType();
                                    while (eventType != XmlPullParser.END_DOCUMENT) {
                                        switch (eventType) {
                                            case XmlPullParser.START_TAG:
                                                String nodeName = xmlPullParser.getName();
                                                if ("city".equals(nodeName)) {
                                                    String pName = xmlPullParser.getAttributeValue(0);
                                                    Log.d("TAG", "pName is " + pName);
                                                    lists.add(pName);

                                                }
                                                break;
                                        }
                                        eventType = xmlPullParser.next();
                                    }
                                } catch (XmlPullParserException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                mTvResult.setText(lists.toString());

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                mTvResult.setText("loading error�?" + volleyError);
                            }
                        });
                requestQueue.add(xMlRequest);
                hideImage();
            default:
                break;
        }
    }

    /**
     * definition lrucache cache mechanism
     */

    class MyImageCache implements ImageLoader.ImageCache {

        private LruCache<String, Bitmap> mLruCache;

        public MyImageCache() {
            int maxsize = 5 * 1024 * 1024; //equal 10MB
            mLruCache = new LruCache<String, Bitmap>(maxsize) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    return bitmap.getRowBytes() * bitmap.getHeight();
                }
            };
        }

        @Override
        public Bitmap getBitmap(String s) {
            return mLruCache.get(s);
        }

        @Override
        public void putBitmap(String s, Bitmap bitmap) {
            mLruCache.put(s, bitmap);

        }
    }
}
