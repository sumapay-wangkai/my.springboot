package demo.wk.springboot4mybaits.datastructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName MyList
 * @Description List
 * @Author wangkai60
 * @Date 2019/1/21 11:01
 * @Version 1.0
 **/
public class MyList {

    public static void main(String[] args) {
        List linkedList = new LinkedList<>();
        List arrayList = new ArrayList<>();

        System.out.println(" insert linkedList spend "+ testInsertSpendTime(linkedList,100000));
        System.out.println(" insert arrayList spend "+ testInsertSpendTime(arrayList,100000));
        System.out.println(" read linkedList spend "+ testReadSpendTime(linkedList));
        System.out.println(" read arrayList spend "+ testReadSpendTime(arrayList));

    }

    public static long testInsertSpendTime(List list, int count) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            list.add(i);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static long testReadSpendTime(List list) {
        long start = System.currentTimeMillis();
        for (int i = 0 ;i<list.size() ;i++) {
            list.get(i);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

}
