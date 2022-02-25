package com.yuefeng.multiThread.updateException;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2022/1/17
 */
public class StaticMethod {
    private String s;

    public String getS() {
        return s;
    }

    public StaticMethod(String s) {
        this.s = s;
    }

    public  String appendString() {
        s = appendA(s);
        s = appendB(s);
        s = appendC(s);
        return s;
    }

    public static String appendA(String s) {
        return s + "a";
    }

    public static String appendB(String s) {
        return s + "B";
    }

    public static String appendC(String s) {
        return s + "c";
    }

     private int count = 0;

    public synchronized void incr() {
       count++;
    }

    public int getCount() {
        return count;
    }

}
