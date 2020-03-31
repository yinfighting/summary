package com.learn.knowledge.basics.collection;

import com.learn.knowledge.entity.People;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description: List 和 Set 区别
 * @author: YHR
 * @date: Created in 2020/3/25 15:21
 * @version: 1.0.0
 * @modified By:
 */
public class ListAndSetDifferention {
    /*List和Set中 我们要熟记：
    List:有序，重复
    有序：指按照一定的顺序输出。||   重复：指可以向list中添加相同的值
    Set:无序，唯一
    无序：指输出是没有顺序。  ||  唯一：指不添加可以向set中添加相同的元素，如果你添加相同的元素，最后输出的结果也是唯一的。
    */

    @Test
    public void testSet() {
        People p1 = new People("AA", 144, 12.6);
        People p2 = new People("BB", 1, 16.6);
        People p3 = new People("CC", 13, 11.6);
        People p4 = new People("DD", 2, 10.3);
        Set<People> set = new HashSet<People>();
        set.add(p1);
        set.add(p2);
        set.add(p3);
        set.add(p4);
        set.add(p1);
        for (People people : set) {
            System.out.println(people);
            //预期ABCDA  ,实际
            //People(name=AA, age=144, height=12.6)
            //People(name=CC, age=13, height=11.6)
            //People(name=BB, age=1, height=16.6)
            //People(name=DD, age=2, height=10.3)
            //证明set 无序，唯一
        }
    }

    @Test
    public void testList() {
        People p1 = new People("AA", 144, 12.6);
        People p2 = new People("BB", 1, 16.6);
        People p3 = new People("CC", 13, 11.6);
        People p4 = new People("DD", 2, 10.3);
        List<People> list = new ArrayList<People>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p1);
        for (People people : list) {
            System.out.println(people);
            //预期ABCDA  ,实际
//            People(name=AA, age=144, height=12.6)
//            People(name=BB, age=1, height=16.6)
//            People(name=CC, age=13, height=11.6)
//            People(name=DD, age=2, height=10.3)
//            People(name=AA, age=144, height=12.6)
            //证明list  有序，重复
        }
    }


}
