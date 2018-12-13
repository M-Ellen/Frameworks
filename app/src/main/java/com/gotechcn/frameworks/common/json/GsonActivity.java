package com.gotechcn.frameworks.common.json;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gotechcn.frameworks.R;
import com.gotechcn.frameworks.common.json.bean.ShopInfo;

import java.util.ArrayList;
import java.util.List;

public class GsonActivity extends Activity implements View.OnClickListener {

    private TextView tv_title;
    private Button bt_gson_tojavaobject;
    private Button bt_gson_tojavalist;
    private Button bt_gson_javatojsonobject;
    private Button bt_gson_javatojsonarray;

    private TextView tv_gson_orignal;
    private TextView tv_gson_last;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);

        // 初始化view
        initView();
        // 初始化监听
        initListener();

    }

    private void initListener() {
        bt_gson_tojavaobject.setOnClickListener(this);
        bt_gson_tojavalist.setOnClickListener(this);
        bt_gson_javatojsonobject.setOnClickListener(this);
        bt_gson_javatojsonarray.setOnClickListener(this);
    }

    // cch初始化view
    private void initView() {
        // 获取标题对象
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("Gson解析");

        // 获取4个button对象
        bt_gson_tojavaobject = (Button) findViewById(R.id.bt_gson_tojavaobject);
        bt_gson_tojavalist = (Button) findViewById(R.id.bt_gson_tojavalist);
        bt_gson_javatojsonobject = (Button) findViewById(R.id.bt_gson_javatojsonobject);
        bt_gson_javatojsonarray = (Button) findViewById(R.id.bt_gson_javatojsonarray);

        // 获取展示数据的对象
        tv_gson_orignal = (TextView) findViewById(R.id.tv_gson_orignal);
        tv_gson_last = (TextView) findViewById(R.id.tv_gson_last);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // （1）将json格式的字符串{}转换为Java对象
            case R.id.bt_gson_tojavaobject:
                jsonToJavaObjectByGson();
                break;

            // （2）将json格式的字符串[]转换为Java对象的List
            case R.id.bt_gson_tojavalist:
                jsonToJavaListByGson();
                break;

            // （3）将Java对象转换为json字符串{}
            case R.id.bt_gson_javatojsonobject:
                javaToJsonObjectByGson();
                break;

            // （4）将Java对象的List转换为json字符串[]
            case R.id.bt_gson_javatojsonarray:
                javaToJsonArrayByGson();
                break;
        }
    }

    private void javaToJsonArrayByGson() {
        // 1 获取或创建Java对象
        List<ShopInfo> shops = new ArrayList<>();
        ShopInfo baoyu = new ShopInfo(1, "鲍鱼", 250.0, "baoyu");
        ShopInfo longxia = new ShopInfo(2, "龙虾", 251.0, "longxia");

        shops.add(baoyu);
        shops.add(longxia);

        Gson gson = new Gson();
        String json = gson.toJson(shops);

        // 3 显示JSON数据
        tv_gson_orignal.setText(shops.toString());
        tv_gson_last.setText(json);
    }

    private void javaToJsonObjectByGson() {
        // 1 获取或创建Java对象
        ShopInfo shopInfo = new ShopInfo(1,"鲍鱼",250.0,"baoyu");

        Gson gson = new Gson();
        String json = gson.toJson(shopInfo);

        // 3 显示JSON数据
        tv_gson_orignal.setText(shopInfo.toString());
        tv_gson_last.setText(json);

    }

    private void jsonToJavaListByGson() {
        // 1 获取或创建JSON数据
        String json = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"imagePath\": \"http://192.168.10.165:8080/f1.jpg\",\n" +
                "        \"name\": \"大虾1\",\n" +
                "        \"price\": 12.3\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"imagePath\": \"http://192.168.10.165:8080/f2.jpg\",\n" +
                "        \"name\": \"大虾2\",\n" +
                "        \"price\": 12.5\n" +
                "    }\n" +
                "]";
        //gson解析
        Gson gson = new Gson();
        List<ShopInfo> shops = gson.fromJson(json, new TypeToken<List<ShopInfo>>() {}.getType());

        // 3 显示JSON数据
        tv_gson_orignal.setText(json);
        tv_gson_last.setText(shops.toString());
    }

    private void jsonToJavaObjectByGson() {
        // 1 获取或创建JSON数据
        String json = "{\n" +
                "\t\"id\":2, \"name\":\"大虾\", \n" +
                "\t\"price\":12.3, \n" +
                "\t\"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f1.jpg\"\n" +
                "}\n";

        Gson gson = new Gson();
        ShopInfo shopInfo = gson.fromJson(json, ShopInfo.class);

        // 3 显示JSON数据
        tv_gson_orignal.setText(json);
        tv_gson_last.setText(shopInfo.toString());
    }
}