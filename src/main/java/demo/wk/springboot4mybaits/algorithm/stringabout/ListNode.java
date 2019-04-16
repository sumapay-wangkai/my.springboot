package demo.wk.springboot4mybaits.algorithm.stringabout;

import com.alibaba.fastjson.JSON;

/**
 * @ClassName ListNodeAdd
 * @Description 单链表相加
 * @Author wangkai60
 * @Date 2019/4/3 14:45
 * @Version 1.0
 **/
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Solution {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int flag = 0;
        ListNode c1 = l1;
        ListNode c2 = l2;

        while (l1 != null || l2 != null) {
            System.out.println("****************");
            if (l1 == null) {
                if(flag == 0){
                    return c2;
                }
                if (flag == 1 && l2.next == null) {
                    ListNode listNode = new ListNode(1);
                    l2.next = listNode;
                    return c2;
                } else {
                    l2.next.val = l2.next.val + flag;
                    return c2;
                }
            }
            if (l2 == null) {
                if(flag == 0){
                    return c1;
                }
                if (flag == 1 && l1.next == null) {
                    ListNode listNode = new ListNode(1);
                    l1.next = listNode;
                    return c1;
                } else {
                    l1.next.val = l1.next.val + flag;
                    return c1;
                }
            }
            int temp = l1.val + l2.val + flag;
            System.out.println("temp="+temp);
            System.out.println(temp % 10);
            l1.val = temp % 10;
            l2.val = temp % 10;
            flag = temp / 10;
            l1 = l1.next;
            l2 = l2.next;
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(4);
        ListNode listNode2 = new ListNode(5);
        ListNode listNode3 = new ListNode(6);

        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
        listNode1.next = listNode2;
        listNode2.next = listNode3;

        listNode4.next = listNode5;
        listNode5.next = listNode6;


        System.out.println(JSON.toJSONString(addTwoNumbers(listNode1,listNode4)));
        System.out.println(JSON.toJSONString(listNode1));
        System.out.println(JSON.toJSONString(listNode4));
    }


}
