package com.chunyu.baselearning.java;

public class FinalCheck {
    public static final String s1 = "123";
    public final String s2 = "456";

    public static void main(String[] args) {
        FinalCheck finalcheck =  new FinalCheck();
        System.out.println(FinalCheck.s1);
        System.out.println(finalcheck.s2);
    }
}
