/*
 * File Name: JITTest.java
 * Copyright:   Copyright GeShengBin. All Rights Reserved.
 * Description:
 * Author: gsb7090
 * Create Date: 2018-7-16
 * Modifier:
 * Modify Date:
 * Bugzilla Id:
 * Modify Content:
 */
package com.kukukikits;

/**
 * JVM Args: -XX:+PrintCompilation -XX:+PrintInlining
 *
 * @author gsb7090
 * @version LearningJVM V1.0.0, 2018-7-16
 * @see
 * @since
 */
public class JITTest {
    public static final int NUM = 1_5000;
    public static int doubleValue(int i){
        for (int j = 0; j < 100_000; j++);
        return i*2;
    }
    public static long calcSum(){
        long sum = 0;
        for (int i = 0; i <= 100 ; i++) {
            sum += doubleValue(i);
        }
        return sum;
    }

    public static void main(String[] args) {
        for (int i = 0; i < NUM; i++) {
            calcSum();
        }
    }
}
