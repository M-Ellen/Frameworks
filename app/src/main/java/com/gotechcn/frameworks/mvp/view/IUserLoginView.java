package com.gotechcn.frameworks.mvp.view;

import com.gotechcn.frameworks.mvp.model.User;

/**
 * Created by pengzhimao on 2017/3/20.
 */

public interface IUserLoginView {
    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedError();

}
