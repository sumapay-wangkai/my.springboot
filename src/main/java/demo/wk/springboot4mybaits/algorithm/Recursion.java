package demo.wk.springboot4mybaits.algorithm;

/**
 * @ClassName Recursion
 * @Description 递归
 * @Author wangkai60
 * @Date 2019/2/12 11:59
 * @Version 1.0
 **/
public class Recursion {


    public static int add(int i){
        if(i <0){
            throw  new IllegalArgumentException("参数异常");
        }else if(i ==0 ||i==1){
            return i;
        }else{
            return add(i-1)+ add(i-2);
        }
    };

    public static void main(String[] args) {
       int result = add(6);
        System.out.println("result="+result);
    }
}
