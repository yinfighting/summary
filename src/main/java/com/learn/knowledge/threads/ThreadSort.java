package com.learn.knowledge.threads;

/**
 * @description:
 * @author: YHR
 * @date: Created in 2020/3/30 18:14
 * @version:
 * @modified By:
 */
public class ThreadSort {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread (()->{
            System.out.println("thread1");
        });
        Thread thread2 = new Thread (()->{
            System.out.println("thread1");
        });
        Thread thread3 = new Thread (()->{
            System.out.println("thread1");
        });
//        thread1.start();
//        thread2.start();
//        thread3.start();
        //要让线程1,2,3按顺序执行j'oin,可以设置优先级和用join方法,优先级是没办法保证100%排序的,还有一种方法,就是第三种创建线程的方法,
        //现在先用join方法试一下
        thread1.start();   thread1.join();
        thread2.start();   thread2.join();
        thread3.start();   thread3.join();
        //结果可以排序,原因:
        /*
        * 看源码可以知道join方法是一个synchronized方法
        *  传入参数0,调用的是object的wait方法
        */
    }

}
