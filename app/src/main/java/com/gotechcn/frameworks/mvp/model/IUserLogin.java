package com.gotechcn.frameworks.mvp.model;

/**
 * Created by pengzhimao on 2017/3/20.
 */

public interface IUserLogin {
    void login(String name, String password, onLoginLisenter onLoginLisenter);
}
