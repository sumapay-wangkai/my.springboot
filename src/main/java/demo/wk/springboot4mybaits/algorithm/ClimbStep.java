package demo.wk.springboot4mybaits.algorithm;

/**
 * @ClassName ClimbStep
 * @Description 爬楼梯
 * @Author wangkai60
 * @Date 2019/3/19 11:28
 * @Version 1.0
 **/
public class ClimbStep {


    private static int climbStairWays(int n) {
        if(n < 1) {
            return 0;
        }
        if(1 == n)
            return 1;

        if(2 == n)
            return 2;

        return climbStairWays(n-1) + climbStairWays(n-2);

    }

    public static void main(String[] args) {
        System.out.println(climbStairWays(10));
    }

}
