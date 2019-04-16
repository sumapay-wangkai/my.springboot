package demo.wk.springboot4mybaits.algorithm.stringabout;

/**
 * @ClassName StringFlip
 * @Description 字符串翻转
 * @Author wangkai60
 * @Date 2019/4/3 9:31
 * @Version 1.0
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *
 * 示例 1：
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * 示例 2：
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * 说明：
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 **/
public class StringFlip {
    public static String reverseWords(String s) {
       String[] arr = s.split(" ");
       StringBuffer sb = new StringBuffer();
       for(int i = arr.length-1;i>-1;i--){
           if(!"".equals(arr[i])){
               sb.append(arr[i]);
               sb.append(" ");
           }
       }
       return  sb.toString().trim();
    }



    public static void main(String[] args) {
        String a = "";
        System.out.println("a="+a);
        System.out.println("翻转后a="+reverseWords(a));
        String b = "";
        String c = "";
    }
}
