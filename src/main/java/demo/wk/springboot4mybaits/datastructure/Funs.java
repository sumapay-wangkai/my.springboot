package demo.wk.springboot4mybaits.datastructure;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Funs
 * @Description 趣味算法
 * @Author wangkai60
 * @Date 2019/1/18 14:14
 * @Version 1.0
 **/
public class Funs {


    public static void main(String[] args) {
            getLastWinner(100,0,2);
    }


    /**
     * 场景一
     * n个人排队成环状，从1,2,3……n
     * 现在从 第 m位开始数，每次数到k个人淘汰
     * 最后留下的人为赢家，那么，最后留下的是原来第几位
     */
    public static int getLastWinner(int totalNo, int startNo, int interval) {
        List<Integer> queue = new ArrayList<Integer>();
        for (int i = 1; i <= totalNo; i++) {
            queue.add(i);
        }
        System.out.println("当前队列为:" + JSON.toJSONString(queue));

        int i = startNo;

        while (queue.size() > 1) {
            i = (i + interval - 1) % queue.size();
            System.out.print("删除：" + queue.remove(i) + " ,");
            System.out.println(JSON.toJSONString(queue));

        }

        System.out.println("最后一个:" + queue.get(0));
        return queue.get(0);
    }


}
