package com.gotechcn.frameworks.mvp.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gotechcn.frameworks.R;
import com.gotechcn.frameworks.mvp.model.User;
import com.gotechcn.frameworks.mvp.presenter.UserLoginPresenter;

public class UserLoginActivity extends Activity implements IUserLoginView, View.OnClickListener {

    private Button mBtnLogin;
    private Button mBtnCancle;
    private EditText mEtName;
    private EditText mEtPassword;
    private ProgressBar mProgressBar;
    private UserLoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        mPresenter = new UserLoginPresenter(this);
        initView();
    }

    private void initView() {
        mBtnLogin = (Button)findViewById(R.id.btn_mvp_login);
        mBtnCancle = (Button)findViewById(R.id.btn_mvp_cancel);
        mEtName = (EditText)findViewById(R.id.et_mvp_name);
        mEtPassword = (EditText)findViewById(R.id.et_mvp_password);
        mProgressBar = (ProgressBar)findViewById(R.id.ProgressBar_mvp);
        mBtnLogin.setOnClickListener(this);
        mBtnCancle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.btn_mvp_login:
                mPresenter.login();
                break;
            case  R.id.btn_mvp_cancel:
                mPresenter.cancel();
                break;
        }
    }


    @Override
    public String getUserName() {
        return mEtName.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEtPassword.getText().toString();
    }

    @Override
    public void clearUserName() {
        mEtName.setText("");
    }

    @Override
    public void clearPassword() {
        mEtPassword.setText("");
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity(User user) {
        Toast.makeText(UserLoginActivity.this,"loginSuccess=="+user.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(UserLoginActivity.this,"loginFailed",Toast.LENGTH_SHORT).show();
    }


}
