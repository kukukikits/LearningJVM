package com.kukukikits;

public class ClassLoading {
    public static void main(String[] args) {
        System.out.println(SubClass.value);
    }
}
class SuperClass{
    static{
        System.out.println("Super class init");
    }
    public static String value = "java";
}
class SubClass extends SuperClass{
    static {
        System.out.println("Sub class init");
    }
}
