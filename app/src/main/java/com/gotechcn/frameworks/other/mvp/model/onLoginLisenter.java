package com.gotechcn.frameworks.other.mvp.model;

/**
 * Created by pengzhimao on 2017/3/20.
 */

public interface onLoginLisenter {
    void loginSuccess(User user);
    void loginFailed();
}
