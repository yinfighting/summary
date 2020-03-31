package com.learn.knowledge.basics.basic;

/**
 * @description:
 * @author: YHR
 * @date: Created in 2020/3/25 14:59
 * @version:
 * @modified By:
 */
public class IntAndInteger {

    public static void main(String[] args) {
//        1、都是封装类，都是new出来的，肯定不相等。因为对象的内存地址不一样。
//        2、都是封装类，都不是new出来的，如果值在-128~127之间，那就相等，否则不相等。
//        3、如果是封装类和基本类型进行比较，只要数值相等那就相等，否则就不相等。因为封装类和基本数据类型进行比较的时候会有一个自动拆箱操作。
//        4、都是基本数据类型，如果数值相等，那就相等；否则不相等。
        Integer a = new Integer(1);
        Integer b = new Integer(1);

        int c = 1;
        int d = 1;

        Integer e = 1;
        Integer f = 1;

        Integer g = 130;
        Integer h = 130;

        Integer i = new Integer(130);
        int j = 130;
        Integer.valueOf(130);  //内部实现

    }
           /* 1：a == b 吗？ 废话，肯定不相等。两个new出来的对象地址不一样。
            2：c == d 吗？ 这个也是废话，都是基本数据类型的值肯定相等。
            3：现在的关键问题是 e == f 吗？ g == h 吗？
    答案是： e == f; g != h。为什么会出现这种情况?因为ava在进行编译时 Integer g = 130会被编译成 Integer.valueOf(130) ，这个可以通过反编译class文件看到。而通过Integer源码可以得出，Integer.valueOf() 方法会在数值-128~127之间会对Integer进行缓存，不会再重新new一个，所以 e==f ；当数值二大于127或者小于-128的时候则会重新new一个，所以g != h 。

            4：c == e 吗， i == j 吗？
    答案都是相等的。因为封装类和基本数据类型进行比较的时候，java会自动拆箱，然后比较数值是 否相等。*/


}
