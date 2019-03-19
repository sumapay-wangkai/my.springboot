package demo.wk.springboot4mybaits.algorithm;


import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.math.RandomUtils;

/**
 * @ClassName MyQuickSort
 * @Description 我的快速排序
 * @Author wangkai60
 * @Date 2019/3/10 21:00
 * @Version 1.0
 **/
public class MyQuickSort {
    public static void main(String[] args) {
        int size = 10;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = RandomUtils.nextInt(50);
//            System.out.println(arr[i]);
        }
        System.out.println("排序前 arr[]:" + JSON.toJSONString(arr));
        mysort(arr, 0, size - 1);
        System.out.println("排序后 arr[]:" + JSON.toJSONString(arr));
    }

    public static void mysort(int[] arr, int low, int high) {
        System.out.println(JSON.toJSONString(arr) + ": " + low + " - " + high);
        int start = low;
        int end = high;
        int key = arr[low];

        while (end > start) {
            while (end > start && arr[end] >= key) {
                end--;
            }
            if (arr[end] <= key) {
                int temp = arr[end];
                arr[end] = arr[start];
                arr[start] = temp;
            }

            while (end > start && arr[start] <= key) {
                start++;
            }
            if (arr[start] >= key) {
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
            }

            if (start > low) {
                mysort(arr, low, start - 1);
            }
            if (high > end) {
                mysort(arr, end + 1, high);
            }
        }
    }


    public static void sort(int[] a, int low, int high) {
        int start = low;
        int end = high;
        int key = a[low];


        while (end > start) {
            //从后往前比较
            while (end > start && a[end] >= key)  //如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
                end--;
            if (a[end] <= key) {
                int temp = a[end];
                a[end] = a[start];
                a[start] = temp;
            }
            //从前往后比较
            while (end > start && a[start] <= key)//如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
                start++;
            if (a[start] >= key) {
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }
            //此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
        }
        //递归
        if (start > low) sort(a, low, start - 1);//左边序列。第一个索引位置到关键值索引-1
        if (end < high) sort(a, end + 1, high);//右边序列。从关键值索引+1到最后一个
    }


}
