package com.yuefeng.multiThread.updateException;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2022/1/17
 */
public class InvokeThread extends Thread {

    StaticMethod staticMethod;

    public InvokeThread(StaticMethod staticMethod) {
        this.staticMethod = staticMethod;
    }

    @Override
    public void run(){
//         for (int i=0; i<1000; i++) {
            staticMethod.incr();
            staticMethod.appendString();
//        }

    }

     public static void main(String[] args) throws InterruptedException {
		StaticMethod staticMethod = new StaticMethod("X");
    	Thread[] threads = new Thread[10000];
    	for (int i=0; i<threads.length; i++) {
    		threads[i] = new InvokeThread(staticMethod);
    		threads[i].start();
		}

    	for (int i=0; i<threads.length; i++) {
    		threads[i].join();
		}

    	System.out.println(staticMethod.getS());
    	System.out.println(staticMethod.getCount());
	}
}
