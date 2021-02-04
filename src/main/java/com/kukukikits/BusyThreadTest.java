package com.kukukikits;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BusyThreadTest {

    /**
     * 线程死循环
     */
    public static void createBusyThread(){
        Thread thread = new Thread(() -> {
            while(true){

            }
        }, "testBusyThread");
        thread.start();
    }

    /**
     * 线程锁等待
     */
    public static void createLockThread(final Object lock){
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "testLockThread");
        thread.start();
    }

    /**
     * 线程死锁
     */
    static class SynAddRunable implements  Runnable{
        int a, b;
        public SynAddRunable(int a, int b){
            this.a = a;
            this.b = b;
        }
        @Override
        public void run() {
            synchronized (Integer.valueOf(a)){
                synchronized (Integer.valueOf(b)){
                    System.out.println(a+b);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        /*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread();
        br.readLine();
        Object lock = new Object();
        createLockThread(lock);*/

        //线程死锁测试
        for (int i = 0; i < 100; i++) {
            new Thread(new SynAddRunable(1,2)).start();
            new Thread(new SynAddRunable(2,1)).start();
        }
    }
}
