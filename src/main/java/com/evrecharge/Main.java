package com.evrecharge;

public class Main {

    public static void main(String[] args) {
        int a = 1;
        int b = 3;
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println(a);
        System.out.println(b);
    }
}
