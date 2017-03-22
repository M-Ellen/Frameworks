package com.gotechcn.frameworks.mvp.presenter;

import android.os.Handler;

import com.gotechcn.frameworks.mvp.model.IUserLogin;
import com.gotechcn.frameworks.mvp.model.User;
import com.gotechcn.frameworks.mvp.model.UserLogin;
import com.gotechcn.frameworks.mvp.model.onLoginLisenter;
import com.gotechcn.frameworks.mvp.view.IUserLoginView;

/**
 * Created by pengzhimao on 2017/3/20.
 */

public class UserLoginPresenter {

    private IUserLogin mUserLogin;
    private IUserLoginView mUserLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.mUserLogin = new UserLogin();
        this.mUserLoginView = userLoginView;
    }

    public void login(){
        mUserLoginView.showLoading();
        mUserLogin.login(mUserLoginView.getUserName(), mUserLoginView.getPassword(), new onLoginLisenter() {
            @Override
            public void loginSuccess(final User user) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mUserLoginView.hideLoading();
                        mUserLoginView.toMainActivity(user);
                    }
                });

            }

            @Override
            public void loginFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mUserLoginView.hideLoading();
                        mUserLoginView.showFailedError();
                    }
                });

            }
        });
    }

    public void cancel(){
        mUserLoginView.clearUserName();
        mUserLoginView.clearPassword();
    }
}
