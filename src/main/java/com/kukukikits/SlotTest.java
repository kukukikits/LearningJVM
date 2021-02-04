/*
 * File Name: SlotTest.java
 * Copyright:   Copyright GeShengBin. All Rights Reserved.
 * Description:
 * Author: gsb7090
 * Create Date: 2018-7-12
 * Modifier:
 * Modify Date:
 * Bugzilla Id:
 * Modify Content:
 */
package com.kukukikits;

/**
 * 局部变量表Slot复用对垃圾收集的影响
 * JVM Args: -verbose:gc
 * @author gsb7090
 * @version LearningJVM V1.0.0, 2018-7-12
 * @see
 * @since
 */
public class SlotTest {
    //不会回收
    public static void test1(){
        byte[] palceholder = new byte[64 * 1024 * 1024];
        System.gc();
    }
    //不会回收

    /**
     * 代码在执行到花括号后面的时候，局部变量表中仍然有Slot与placeholder数组对象关联，
     * 因为之后的代码只有一个gc操作，没有任何对局部变量表的读写操作，所以placeholder
     * 原本占用的Slot还保持着数组对象的引用，所以下面代码不会回收64M的内存
     */
    public static void test2(){
        {
            byte[] palceholder = new byte[64 * 1024 * 1024];
        }
        System.gc();
    }
    //回收

    /**
     * int a = 0;的操作复用了原本被placeholder占用的Slot，所以在GC的时候，局部变量表上
     * 已经没有数组对象的引用了，所以此时可以进行回收
     */
    public static void test3(){
        {
            byte[] palceholder = new byte[64 * 1024 * 1024];
        }
        int a = 0;
        System.gc();
    }
    //回收
    public static void test4(){
        byte[] palceholder = new byte[64 * 1024 * 1024];

        //其他操作

        //如果接下来有其他的操作，最好将placeholder的引用清空，以控制变量回收
        //但是以恰当的变量作用域来控制变量回收时间，才是最优雅的解决方法
        palceholder = null;

        //其他操作
        System.gc();
    }

    public static void main(String[] args) {
        test4();
    }
}
