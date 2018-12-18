package com.gotechcn.frameworks.other.MaterialDesign;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.gotechcn.frameworks.R;
import com.gotechcn.frameworks.other.ClearEditText;

public class TextInputActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout mNameInputLayout, mPswInputLayout;
    private ClearEditText mNameEt;
    private TextInputEditText mPswEt;

    private Button commitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mNameInputLayout = findViewById(R.id.input_layout_name);
        mPswInputLayout = findViewById(R.id.input_layout_number);
        mNameEt = findViewById(R.id.et_name);
        mPswEt = findViewById(R.id.et_number);

        commitBtn = findViewById(R.id.btn_commit);
        commitBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_commit:
                mNameInputLayout.setError("用户名不对");
                mPswInputLayout.setError("密码错误");
                break;
            default:
                break;
        }
    }
}
