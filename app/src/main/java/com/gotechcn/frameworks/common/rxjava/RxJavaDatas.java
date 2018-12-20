package com.gotechcn.frameworks.common.rxjava;


public class RxJavaDatas {
    private int mFunctionId = 0;
    private String mFunctionName = null;
    private String mFunctionDes = null;

    public RxJavaDatas(String name, String des) {
        this.mFunctionName = name;
        this.mFunctionDes = des;
    }

    public int getFunctionId() {
        return mFunctionId;
    }

    public void setFunctionId(int functionId) {
        mFunctionId = functionId;
    }

    public String getFunctionName() {
        return mFunctionName;
    }

    public void setFunctionName(String functionName) {
        mFunctionName = functionName;
    }

    public String getFunctionDes() {
        return mFunctionDes;
    }

    public void setFunctionDes(String functionDes) {
        mFunctionDes = functionDes;
    }
}
