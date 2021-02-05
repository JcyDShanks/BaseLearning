package com.chunyu.baselearning.java;

import com.chunyu.baselearning.kotlin.Str;

public class JavaBase {
    // 基本数据类型
    // 引用数据类型
    public static void main(String[] args) {
        int a = 0;
        String b = "111";
        System.out.println(b.equals("2"));

    }
}

abstract class Parent {
    int a = 0;

    abstract int getA();

    final int getB() {
        return 3;
    }

    private int returnA() {
        return a;
    }
}

class Sub extends Parent {

    @Override
    int getA() {
        return 1;
    }

}

interface IJava {
    int countA();
    abstract int countB();
}