package com.xiaoyunchengzhu.jni;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zhangshiyu on 19-5-9
 */
public class JniUtilsTest {

    /**
     * 请换成绝对路径
     */
    String soPath="JniModule/jni/build/libJniUtil.so";
    @Test
    public void byteToStr() {
        /**

        JniUtils jniUtils=new JniUtils(soPath);
        String test="I am Test";
        byte[] bytes=test.getBytes();
        jniUtils.byteToStr(bytes, new JniCallBack() {
            @Override
            public void decodeResponse(JniResult result) {
                System.out.println("result:"+result.getStr());
            }
        });
         *
         */
    }
}