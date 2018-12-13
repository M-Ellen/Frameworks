package com.gotechcn.frameworks.common.logger;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.gotechcn.frameworks.R;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LoggerActivity extends Activity {

    private static final String TAG = "Log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger);
//        Logger.i("调用了onCreate");
//        testNormal();
//        testCollections();
//        testJson();
//        testXml();
        testFormat();
//        testException();
    }

    public void testNormal(){
//        Logger.t("hello").d("DEBUG日志");
        Logger.d("DEBUG日志");
        Logger.i("INFO日志");
        Logger.e("ERROR日志");
        //打印储存路径
//        String diskPath = Environment.getExternalStorageDirectory().getAbsolutePath();
//        Log.e(TAG, diskPath);
    }

    public void testJson(){
        String jsonDate = "{\"id\":859,\"channelnumber\":681,\"bilingual\":0,\"name\":\"beIN Sports 2 English 600K H265\",\n" +
                " \"language\":\"EN\",\n" +
                " \"videotype\":\"MPEG2\",\"audiotype\":\"MPEP1\",\"callsign\":\"beIN Sports 2 English 600K H265\",\"country\":\"cn\"};";

        Logger.json(jsonDate);
        Log.d(TAG, jsonDate);
    }

    public void testXml() {
        String xmlDate = "<province id=\"01\" name=\"北京\">  \n" +
                "    <city id=\"0101\" name=\"北京\">  \n" +
                "      <county id=\"010101\" name=\"北京\" weatherCode=\"101010100\"/>  \n" +
                "      <county id=\"010102\" name=\"海淀\" weatherCode=\"101010200\"/>  \n" +
                "      <county id=\"010103\" name=\"朝阳\" weatherCode=\"101010300\"/>  \n" +
                "      <county id=\"010110\" name=\"石景山\" weatherCode=\"101011000\"/>  \n" +
                "    </city>  \n" +
                "  </province>  ";
        Logger.xml(xmlDate);
        Log.d(TAG, xmlDate);
    }

    public void testCollections(){
        /**
         * list
         */
        List list = new ArrayList();
        list.add("hello");
        list.add("world");
        Logger.d(list);
//        Log.e(TAG, list.toString());

        /**
         * Map
         */
        Map map = new HashMap();
        map.put("map1","hello");
        map.put("map2","world");
        Logger.d(map);

        /**
         * Set
         */
        Set set = new HashSet();
        set.add("hello");
        set.add("world");
        Logger.d(set);

        /**
         * Array
         */
        String [] strs = {"hello", "world"};
        Logger.d(strs);
    }

    public void testFormat(){
        Logger.d("hello %s, luck number is %d", "world", 5);
    }

    public void testException(){
        List list = null;
        try {
            list.add("hello world");

        }catch (Exception e){
            Logger.e(e.toString());
            Logger.e(e, "message");
        }

    }
}
