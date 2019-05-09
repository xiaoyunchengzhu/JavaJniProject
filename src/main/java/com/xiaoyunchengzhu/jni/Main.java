package com.xiaoyunchengzhu.jni;


import java.io.File;

/**
 * Created by zhangshiyu on 19-4-23
 */
public class Main {


    public static void main(String[] argc) {
        String soPath=null;

        if (argc.length==2){
            if (argc[0].equals("-path")){
             soPath=argc[1];
            }
        }
        if (soPath==null){
            System.out.println("Usage:\n  -path   <>  路径(动态库绝对路径,如果和jar同意目录,则可以是动态库文件名)");
            return;
        }
        JniUtils jniUtils=new JniUtils(soPath);
        String test="I am Test";
        byte[] bytes=test.getBytes();
        jniUtils.byteToStr(bytes, new JniCallBack() {
            @Override
            public void decodeResponse(JniResult result) {
                System.out.println("result:"+result.getStr());
            }
        });

    }
}
