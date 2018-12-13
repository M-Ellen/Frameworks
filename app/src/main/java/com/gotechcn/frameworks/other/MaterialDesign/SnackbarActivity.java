package com.gotechcn.frameworks.other.MaterialDesign;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gotechcn.frameworks.R;

public class SnackbarActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1,btn2,btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1 :
                Snackbar.make(v, "普通默认的Snackbar",Snackbar.LENGTH_LONG).show();
                break;
            case R.id.btn2 :
                Snackbar snackbar = Snackbar.make(v, "有Action的Snackbar",Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("关闭", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SnackbarActivity.this, "点击了Snackbar中的关闭", Toast.LENGTH_SHORT).show();
                    }
                });
                snackbar.show();
                break;
            case R.id.btn3 :
                Snackbar snackbar1 = Snackbar.make(v, "自定义的Snackbar",Snackbar.LENGTH_INDEFINITE);
                snackbar1.setAction("关闭", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SnackbarActivity.this, "点击了Snackbar中的关闭", Toast.LENGTH_SHORT).show();
                    }
                });

                /**
                 * 获取 Snackbar 的控件，具体可以查看源码
                 */
                View view = snackbar1.getView();
                view.setBackgroundResource(R.color.colorPrimary);

                TextView tvMsg = view.findViewById(R.id.snackbar_text);
                tvMsg.setTextColor(getResources().getColor(R.color.bg_color_blue));

                Button btnAction = view.findViewById(R.id.snackbar_action);


                ViewGroup.LayoutParams lp = view.getLayoutParams();
                LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(lp.width, lp.height);
                llp.gravity = Gravity.TOP;

                view.setLayoutParams(llp);

                snackbar1.show();
                break;
            default:
                break;
        }
    }
}
