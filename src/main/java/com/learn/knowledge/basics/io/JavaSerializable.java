package com.learn.knowledge.basics.io;

import java.io.*;

/**
 * @description: 什么是 java 序列化，如何实现 java 序列化？
 * @author: YHR
 * @date: Created in 2020/3/27 14:07
 * @version: 1.0.0
 * @modified By:
 */
public class JavaSerializable implements Serializable {
//        序列化就是一种用来处理对象流的机制，所谓对象流也就是将对象的内容进行流化（将对象转换成二进制）。
//    可以对流化后的对象进行读写操作，也可将流化后的对象传输于网络之间，序列化是为了解决在对对象流进行读写操作时所引发的问题。
//    把对象转换为字节序列的过程称为对象的序列化，把字节序列恢复为对象的过程称为对象的反序列化。
//
//        序列化的实现：将需要被序列化的类实现Serializable接口，该接口没有需要实现的方法，
//     implements Serializable只是为了标注该对象是可被序列化的，然后使用一个输出流(如：FileOutputStream)
//    来构造一个ObjectOutputStream(对象流)对象，接着，使用ObjectOutputStream对象的writeObject(Object obj)方法
//    就可以将参数为obj的对象写出(即保存其状态)，要恢复的话则用输入流。

    //在java中如何实现序列化： 首先我们要把准备要序列化类，实现 Serializabel接口 例如：我们要JavaSerializable类里的name和age都序列化
    //本类可以序列化
    private String name ;
    private int age ;
    public JavaSerializable(String name,int age)
    {
        this.name = name ; this.age = age ;
    }
    @Override
    public String toString() {
        return "姓名：" + this.name + "，年龄" + this.age ;
    }
}
class ObjectOutputStreamDemo
{
    //序列化
    public static void main(String[] args) throws Exception
    {
        //序列化后生成指定文件路径
        File file = new File("D:" + File.separator + "JavaSerializable.txt") ;
        ObjectOutputStream oos = null ;
        //装饰流（流）
        oos = new ObjectOutputStream(new FileOutputStream(file)) ;
        //实例化类
        JavaSerializable per = new JavaSerializable("张三",30) ;
        oos.writeObject(per) ;
        //把类对象序列化
        oos.close() ;
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        JavaSerializable javaSerializable = (JavaSerializable) ois.readObject();
        System.out.println(javaSerializable.toString());
        // 对象的序列化主要有两种用途：

        //1） 把对象的字节序列永久地保存到硬盘上，通常存放在一个文件中；

        //2） 在网络上传送对象的字节序列。
    }
}