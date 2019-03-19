package demo.wk.springboot4mybaits.algorithm;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.math.RandomUtils;

/**
 * @ClassName MyMergeSort
 * @Description 我的归并排序
 * @Author wangkai60
 * @Date 2019/3/13 11:04
 * @Version 1.0
 **/
public class MyMergeSort {

    //两路归并算法，两个排好序的子序列合并为一个子序列
    public static void merge(int[] a, int left, int mid, int right) {
        int[] tmp = new int[a.length];//辅助数组
        int p1 = left, p2 = mid + 1, k = left;//p1、p2是检测指针，k是存放指针

        while (p1 <= mid && p2 <= right) {
            if (a[p1] <= a[p2])
                tmp[k++] = a[p1++];
            else
                tmp[k++] = a[p2++];
        }

        while (p1 <= mid) tmp[k++] = a[p1++];//如果第一个序列未检测完，直接将后面所有元素加到合并的序列中
        while (p2 <= right) tmp[k++] = a[p2++];//同上
        System.out.println("temp:"+JSON.toJSONString(tmp));
        //复制回原素组
        for (int i = left; i <= right; i++)
            a[i] = tmp[i];

        System.out.println("a:"+JSON.toJSONString(a));
    }

    public static void mergeSort(int[] a, int start, int end) {
        if (start < end) {//当子序列中只有一个元素时结束递归
            int mid = (start + end) / 2;//划分子序列
            mergeSort(a, start, mid);//对左侧子序列进行递归排序
            mergeSort(a, mid + 1, end);//对右侧子序列进行递归排序
            merge(a, start, mid, end);//合并
        }
    }


    public static void main(String[] args) {
        int size = 10;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = RandomUtils.nextInt(50);
//            System.out.println(arr[i]);
        }
        System.out.println("排序前 arr[]:" + JSON.toJSONString(arr));
        mySort(arr, 0, size - 1);
        System.out.println("排序后 arr[]:" + JSON.toJSONString(arr));
    }

    public static int[] mySort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mySort(arr, left, mid);
            mySort(arr, mid + 1, right);
            mySort(arr, left, mid, right);
        }
        return arr;
    }

    public static void mySort(int[] arr, int left, int mid, int right) {
        System.out.println("arr before:"+JSON.toJSONString(arr));
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        System.out.println("temp:" + JSON.toJSONString(temp));
        for (int x = 0; x < temp.length; x++) {
            arr[left + x] = temp[x];
        }
        System.out.println("arr after:"+JSON.toJSONString(arr));
        System.out.println("******************************");
    }

}
