package com.xiaoyunchengzhu.jni;

/**
 * Created by zhangshiyu on 19-4-25
 */
public class JniResult {

    private byte[] result;
    private String str;

    public JniResult(byte[] result, String str) {
        this.result = result;
        this.str = str;
    }

    public byte[] getResult() {
        return result;
    }

    public void setResult(byte[] result) {
        this.result = result;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
