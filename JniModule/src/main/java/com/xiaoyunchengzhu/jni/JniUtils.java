package com.xiaoyunchengzhu.jni;

/**
 * Created by zhangshiyu on 19-5-9
 */
public class JniUtils {

    public JniUtils(String libPath) {
        if (!libPath.startsWith("/")){
            String filePath=getClass().getProtectionDomain().getCodeSource().getLocation().getFile();
            libPath=filePath.substring(0,filePath.lastIndexOf("/")+1)+libPath;
        }
        System.load(libPath);
    }



    /**
     * 示例,把byte转为string
     * @param data 解码传入,也是计算的结果
     * @param callBack 回调接口
     */
    public native void byteToStr(byte[] data, JniCallBack callBack);
}
