package com.learn.knowledge.basics.collection;

import java.util.*;

/**
 * @description: HashMap 的源码，实现原理
 * @author: YHR
 * @date: Created in 2020/3/26 16:19
 * @version: 1.0.0
 * @modified By:
 */
public class HashMapTheory<K,V> {
    //https://zhuanlan.zhihu.com/p/114508758?utm_source=wechat_session&utm_medium=social&utm_oi=618213383560892416


    //3.1关键属性分析
    transient Node<K,V> [] table; //Node类型的数组，记我们常说的bucket数组，其中每个元素为链表或者树形结构

    transient int size;//HashMap中保存的数据个数

    int threshold;//HashMap需要resize操作的阈值

    final float loadFactor;//负载因子，用于计算threshold。计算公式为：threshold = loadFactor * capacity


    //此构造函数创建一个空的HashMap，其中负载因子为默认值0.75
    public HashMapTheory() {
        this.loadFactor = 0.75f; // all other fields defaulted
    }

    //传入默认的容量大小，创造一个指定容量大小和默认负载因子为0.75的HashMap
    public HashMapTheory(int initialCapacity) {
        this(initialCapacity, 0.75f);
    }

    //创建一个指定容量和指定负载因为HashMap，以下代码删除了入参检查
    public HashMapTheory(int initialCapacity, float loadFactor) {
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }
    /**
     * Returns a power of two size for the given target capacity.
     * 为给定的目标容量返回两个大小的幂。
     *  HashMap需要resize操作的阈值
     */
    /**
     case initialCapacity = 0:

     this.threshold = 1；

     case initialCapacity为非0且不为2的n次方:

     this.threshold = 大于initialCapacity中第一个2的n次方的数。

     case initialCapacity = 2^n：

     this.threshold = initialCapacity
    */
    static final int MAXIMUM_CAPACITY = 1 << 30;
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
    // HashMap的容量为什么是2的n次幂，和这个(n - 1) & hash的计算方法有着千丝万缕的关系，
    // 符号&是按位与的计算，这是位运算，计算机能直接运算，特别高效，按位与&的计算方法是，
    // 有当对应位置的数据都为1时，运算结果也为1，当HashMap的容量是2的n次幂时，(n-1)的2进制也就是1111111***111这样形式的，
    // 这样与添加元素的hash值进行位运算时，能够充分的散列，使得添加的元素均匀分布在HashMap的每个位置上，减少hash碰撞，下面举例进行说明。
    // hashCode 比如一个String@4e57de  @"前面的是你的类名，后面的就是散列码的16进制表示
    void resize() {
        Node<K, V>[] oldTab = table;
        int oldThr = threshold;  //oldThr 根据传入的初始化cap决定 2的n次方

        int newCap, newThr = 0;
        if (oldThr > 0) // 当构造函数中传入了capacity的时候
            newCap = oldThr;  //newCap = threshold  2的n次方，即构造函数的时候的初始化容量
        else {               // zero initial threshold signifies using defaults
            newCap = 16;
            newThr = (int)(0.75 * 16);
        }
        float ft = (float)newCap * loadFactor;  // 2的n次方 * loadFactory

        newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                (int)ft : Integer.MAX_VALUE);

        threshold = newThr; //新的threshold== newCap * loadFactory
        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap]; //长度为2的n次方的数组
        table = newTab;

        //不是第一次插入,并且达到阈值threshold时 会执行下面方法
        //需要将之前的数据节点复制到新的table中
        if (oldTab != null) {
            int oldCap = (int)(16*0.75);
            for (int j = 0; j < oldCap; ++j) {
                Node<K,V> e;
                if ((e = oldTab[j]) != null) {
                    oldTab[j] = null;
                    if (e.next == null) //只有一个节点
                    {
                        newTab[e.hash & (newCap - 1)] = e;
                    }
//                    else if (e instanceof TreeNode) // 如果是树形结构，拆开
//                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                    else { // preserve order
                        Node<K,V> loHead = null, loTail = null;
                        Node<K,V> hiHead = null, hiTail = null;
                        Node<K,V> next;
                        do {
                            next = e.next;
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null) {
                                    loHead = e;
                                } else {
                                    loTail.next = e;
                                }
                                loTail = e;
                            }
                            else {
                                if (hiTail == null) {
                                    hiHead = e;
                                } else {
                                    hiTail.next = e;
                                }
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
    }


    //HashMap的遍历
    /*方法一：

            通过map.keySet()获取key的集合，然后通过遍历key的集合来遍历map
    方法二：

            通过map.entrySet()方法获取map中节点集合，然后遍历此集合遍历map*/
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "test");
        map.put("age", "25");
        map.put("address", "HZ");

        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            System.out.println(map.get(key));
            System.out.println("key is : {\"" + key + "\", \"" + map.get(key)+"\"}");
        }

        Set<Map.Entry<String, Object>> set = map.entrySet();
        for (Map.Entry<String, Object> entry : set) {
            System.out.println("key is : " + entry.getKey() + ".  value is " + entry.getValue());
        }
        Hashtable<Object, Object> hashtable = new Hashtable<>();
        hashtable.put("1",null);
        //hashtable.put(null,null);

    }

    //TODO
    static final class TreeNode<K,V>  {

    }

    static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        HashMapTheory.Node<K,V> next;

        Node(int hash, K key, V value, HashMapTheory.Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public final K getKey()        { return key; }
        @Override
        public final V getValue()      { return value; }
        @Override
        public final String toString() { return key + "=" + value; }

        @Override
        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        @Override
        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        @Override
        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                return Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue());
            }
            return false;
        }
    }
}
