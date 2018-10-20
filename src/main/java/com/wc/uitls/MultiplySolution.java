package com.wc.uitls;

import java.lang.reflect.Method;

/**
 * Created by îñ¸ç on 2018/4/23.
 */
public class MultiplySolution {

    Object[] args;
    Class clazz;

    public MultiplySolution(Class clazz, Object... args) {
        this.args = args;
        this.clazz = clazz;
    }

    void start() {
        for (Method it : clazz.getDeclaredMethods()) {
            new Thread(() -> {
                try {
                    long startTime = System.currentTimeMillis();
                    System.out.println(it.getName() + "Êä³ö: " + it.invoke(clazz.getConstructor().newInstance(), args).toString());
                    System.out.println(it.getName() + "ºÄÊ±: " + (System.currentTimeMillis() - startTime) + "ms");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }


}