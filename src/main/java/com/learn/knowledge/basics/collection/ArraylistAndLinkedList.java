package com.learn.knowledge.basics.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: Arraylist 与 LinkedList 区别
 * @author: YHR
 * @date: Created in 2020/3/25 15:53
 * @version: 1.0.0
 * @modified By:
 */
public class ArraylistAndLinkedList {
    /*1、ArrayList与LinkedList 在JDK中所在的位置
        D:\ideaSpace\knowledge\src\main\resources\static\images\98fc176b-5d1c-3e97-af2f-a62b251e8e93.png
        ArrayList实现了List接口,它是以数组的方式来实现的,数组的特性是可以使用索引的方式来快速定位对象的位置,
        因此对于快速的随机取得对象的需求,使用ArrayList实现执行效率上会比较好.
     */
    @Test
    public void ArrayListDemo() {
        List<String> userlist = new ArrayList<String>();
        userlist.add("yulon");
        userlist.add("xiaoyun");
        userlist.add("羽龙共舞");
        System.out.println("使用普通for循环:");
        for(int i=0; i<userlist.size(); i++){
            System.out.print(userlist.get(i)+" ");
        }
        System.out.println();
        System.out.println();
        System.out.println("使用Iterator迭代器:");
        Iterator it = userlist.iterator();
        while(it.hasNext()){
            System.out.print(it.next()+" ");
        }
        System.out.println();
        System.out.println();
        System.out.println("使用增强for循环:");

        for(String s : userlist){
            System.out.print(s+" ");
        }
        System.out.println();
        System.out.println("使用普通for循环,用get方法获取; 使用Iterator迭代器,使用next方法遍历;" +
                "使用增强for循环,直接输出!  由此可见第三种方法是最方便，最简洁的!");
    }


     /*2. LinkedList是采用链表的方式来实现List接口的,它本身有自己特定的方法，如:
          addFirst(),addLast(),getFirst(),removeFirst()等. 由于是采用链表实现的,
          因此在进行insert和remove动作时在效率上要比ArrayList要好得多!适合用来实现Stack(堆栈)与Queue(队列),前者先进后出，后者是先进先出
          linkedList是双向链表
     */
     //堆栈
     static class StringStack {
         private LinkedList<String> linkedList = new LinkedList<String>();

         /**
          * 将元素加入LinkedList容器
          * (即插入到链表的第一个位置)
          */
         public void push(String name){
             linkedList.addFirst(name);
         }
         /**
          * 取出堆栈中最上面的元素
          * (即取出链表linkedList的第一个元素)
          * @return
          */
         public String getTop(){
             return linkedList.getFirst();
         }
         /**
          * 取出并删除最上面的元素
          * (即移出linkedList的第一个元素)
          * @return
          */
         public String pop(){
             return linkedList.removeFirst();
         }
         /**
          * 获取元素个数
          * @return
          */
         public int size(){
             return linkedList.size();
         }

         /**
          * 判断堆栈是否为空
          * (即判断 linkedList是否为空)
          * @return
          */
         public boolean isEmpty(){
             return linkedList.isEmpty();
         }
         //测试
         public static void main(String[] str) {
             StringStack stack = new StringStack();
             stack.push("yulon");
             stack.push("xiaoyun");
             stack.push("羽龙共舞");
             System.out.print("第一个元素是:\t");
             System.out.println(stack.getTop());
             System.out.println();
             System.out.println("全部元素:");
             while(!stack.isEmpty()){
                 System.out.println("\t"+stack.pop());
             }
         }
     }

  //  在删除可插入对象的动作时，为什么ArrayList的效率会比较低呢?

    /*
        解析: 因为ArrayList是使用数组实现的,若要从数组中删除或插入某一个对象，
        需要移动后段的数组元素，从而会重新调整索引顺序,调整索引顺序会消耗一定的时间，
        所以速度上就会比LinkedList要慢许多. 相反,LinkedList是使用链表实现的,
        若要从链表中删除或插入某一个对象,只需要改变前后对象的引用即可!
    */

}

