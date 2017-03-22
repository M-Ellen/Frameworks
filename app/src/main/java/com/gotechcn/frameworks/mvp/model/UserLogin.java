package com.gotechcn.frameworks.mvp.model;

/**
 * Created by pengzhimao on 2017/3/20.
 */

public class UserLogin implements IUserLogin {
    @Override
    public void login(final String name, final String password, final onLoginLisenter onLoginLisenter) {

        new Thread(){
            @Override
            public void run() {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(name.equals("pzm") && password.equals("123")) {
                   User user = new User();
                   user.setUserName(name);
                   user.setPassword(password);
                   onLoginLisenter.loginSuccess(user);
               }else {
                   onLoginLisenter.loginFailed();
               }
            }
        }.start();
    }
}
