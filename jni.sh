#!/usr/bin/env bash


jnipath="JniModule/jni"
JniModule="JniModule"

root=$(pwd)

clean(){
if [ -d $jnipath/build ];then
  rm -rf $jnipath/build
else
  echo "jni build have been delete "
fi
./gradlew -b $JniModule/build.gradle clean
./gradlew clean
}
build (){
if [ -d $jnipath/build ];then
  rm -rf $jnipath/build
else
  echo "create build"
fi

mkdir $jnipath/build

cd $jnipath/build
cmake .. -O2
make
cd $root
./gradlew -b $JniModule/build.gradle build
./gradlew build

}

case $1 in
 "clean")
 clean;
 ;;
 "build")
 build;
 ;;
 *)
 printf "Usage:\n  -build   构建\n  -clean   清理\n"
 esac