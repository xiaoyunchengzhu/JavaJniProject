# jni的创建和使用

### 环境(此处的操作系统,只能是linux(默认) 或mac)
1. 安装jdk,配置好环境变量.
2. 安装cmake用来编译动态库 

说明:工程是gradle构建的工程.可以使用idea打开.(非android工程)

### 1 创建java native 方法

在一个类中创建native 方法,以便于c来实现

例如在JniModule的Java工程中的JniUtils类中:
```
    /**
     * 示例,把byte转为string
     * @param data 解码传入,也是计算的结果
     * @param callBack 回调接口
     */
    public native void byteToStr(byte[] data, JniCallBack callBack);
```
### 2 做好加载动态库的代码
我这里做的是参数传入.也可以使用System.loadLibrary(libname)方法,传入放入系统工作空间里实现的动态库名字

此处仅只能绝对路径
```
    public JniUtils(String libPath) {
        System.load(libPath);
    }
```
### 3 生成 c 头文件
```
 javah -classpath JniModule/src/main/java -o JniModule/jni/jniutil.h -jni com.xiaoyunchengzhu.jni.JniUtils
```
生成一个jniutil.h的头文件在JniModule/jni的路径下.

说明:
1. -classpath <path> 加载class的路径
1. -o <file>输出的文件
1. -d <dir> 输出的目录
1. -jni  native文件的类.

如果不加-o参数则会生成一个默认的 com_xiaoyunchengzhu_jni_JniUtils.h头文件.

里面的jni的c函数的命名规则,和java的方法签名说明暂时省略.
### 4 实现头文件的 cmake构建
前提是必须装了jdk并设置了JAVA_HOME环境变量,如果没有,那么自己绝对路径来指定要引入的库目录.
```
cmake_minimum_required(VERSION 3.5.1)
project(DecodeJni1)

set(CMAKE_CXX_STANDARD 14)
include_directories($ENV{JAVA_HOME}/include)
IF ( WIN32 )
    message('win 32')
ELSEIF( APPLE)
    set(enviroment darwin)
    message('apple')
ELSE()
    set(enviroment linux)
    message("linux")
ENDIF()
include_directories($ENV{JAVA_HOME}/include/${enviroment})
add_library(JniUtil SHARED jniutil.h jniutil.c)
```
### 5 实现方法(看代码,例子实现非常简单.)
在 JniModule/jni/jniutil.c文件中

例如:(省略)
### 6 生成动态库
命令:

```
cd JniModule/jni
mkdir build
cd build
cmake ..
make
```

创建build主要是分离代码和构建生成的文件.执行完毕

build 文件夹下的libJniUtil.so文件就动态库文件了(linux环境下),mac的动态库,是.dylib后缀
### 7 java调用动态库
JniModule build之后生成生成JniModule-1.0.0.jar 包
```
./gradlew -b JniModule/build.gradle build
```
在使用的工程中引入包

例如在gralde中
```
    compile fileTree(dir:'JniModule/build/libs',includes:['*jar'])
```

调用库函数 ,需要把so的库函数绝对路径传入.
```
public class Main {

    public static void main(String[] argc) {
        String soPath="/media/zhangshiyu/00064DE5000FE4D9/eclipseworkspace/JavaJniProject/JniModule/jni/build/libJniUtil.so";
        JniUtils jniUtils=new JniUtils(soPath);
    }
}
```

### 8 so动态库的路径 最好是命令行参数的方式传入,因为无法一块打入可执行jar中
省略

### 9 shell脚本构建和执行(本示例)
基于已经完成了native方法类的创建;jni头文件生成,c代码实现并且cmake配置完成;示例引入jni 的jar包.

本示例可以直接执行脚本构建.
```
./jni.sh build # 构建(包括so库的生成和jnit native 类的jar包生成,和引入)
java -jar build/libs/JavaJniProject-${version}.jar -path ${动态库路径} # 执行调用
./jni.sh clean # 清理构建生成的文件
```