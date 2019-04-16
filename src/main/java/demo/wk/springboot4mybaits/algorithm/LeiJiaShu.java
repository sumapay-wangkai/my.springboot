package demo.wk.springboot4mybaits.algorithm;

import com.alibaba.fastjson.JSON;

/**
 * @ClassName LeiJiaShu
 * @Description 累加数
 * @Author wangkai60
 * @Date 2019/3/29 18:22
 * @Version 1.0
 **/
public class LeiJiaShu {
    /**
     * 题目要求
     * <p>
     * 累加数是一个字符串，组成它的数字可以形成累加序列。
     * <p>
     * 一个有效的累加序列必须至少包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
     * <p>
     * 给定一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是累加数。
     * <p>
     * 说明: 累加序列里的数不会以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "112358"
     * 输出: true
     * 解释: 累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
     * 示例 2:
     * <p>
     * 输入: "199100199"
     * 输出: true
     * 解释: 累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199
     *
     * @param num
     * @return
     */

    public static boolean isAdditiveNumber(String num) {

        return false;
    }


    public static void main(String[] args) {
        int[] arr1 = new int[]{2, 3, 4, 5, 8, 11, 12};
        System.out.println(JSON.toJSONString(twoSumInOrderArr(arr1, 100)));
    }

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                System.out.println("**************");
                System.out.println("i=" + i + ",nums[i]=" + nums[i]);
                System.out.println("j=" + j + ",nums[j]=" + nums[j]);
                if (nums[i] + nums[j] == target) {
                    int[] result = new int[]{i, j};
                    return result;
                }
            }
        }
        return null;
    }

    public static int[] twoSumInOrderArr(int[] numbers, int target) {
        for (int i = 0; i < numbers.length - 1; i++) {
            int addNum = numbers[i];
            int addedNum = target - addNum;
            if (numbers[0] > numbers[1]) {
                int j = getDescIndex(numbers, i + 1, numbers.length - 1, addedNum);
                if (j > i) {
                    int[] result = new int[]{i, j};
                    return result;
                }
            } else {
                int j = getAscIndex(numbers, i + 1, numbers.length - 1, addedNum);
                if (j > i) {
                    int[] result = new int[]{i, j};
                    return result;
                }
            }
        }
        return null;
    }

    public static int getDescIndex(int[] numbers, int left, int right, int target) {
        if (left + 1 == right) {
            return -1;
        }
        if (numbers[left] == target) {
            return left;
        } else if (numbers[right] == target) {
            return right;
        } else {
            int mid = (left + right) / 2;
            if (numbers[mid] == target) {
                return mid;
            } else if (numbers[mid] > target) {
                return getDescIndex(numbers, mid, right, target);
            } else {
                return getDescIndex(numbers, left, mid, target);
            }
        }
    }

    public static int getAscIndex(int[] numbers, int left, int right, int target) {
//        System.out.println("*********************************");
//        System.out.println(JSON.toJSONString(numbers));
//        System.out.println(JSON.toJSONString(left));
//        System.out.println(JSON.toJSONString(right));
//        System.out.println(JSON.toJSONString(target));

        if (numbers[left] == target) {
            return left;
        } else if (numbers[right] == target) {
            return right;
        }
        if (left + 1 == right  || left == right) {
            return -1;
        } else {
            int mid = (left + right) / 2;
            if (numbers[mid] == target) {
                return mid;
            } else if (numbers[mid] > target) {
                return getAscIndex(numbers, left, mid, target);
            } else {
                return getAscIndex(numbers, mid, right, target);
            }
        }
    }

    public static int[] twoSumyouhua(int[] numbers, int target) {
        int head = 0;
        int tail = numbers.length - 1;
        while (head < tail) {
            if (numbers[head] + numbers[tail] < target) {
                head++;
            } else if (numbers[head] + numbers[tail] > target) {
                tail--;
            } else {
                break;
            }
        }
        return new int[] { head + 1, tail +1 };

    }

}
