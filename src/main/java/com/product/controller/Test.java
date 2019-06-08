package com.product.controller;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> list = f1();
        System.out.println(list);

    }

    static  List<String> f1(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        return list.size()>3?list.subList(0, 3):list;
    }
}
