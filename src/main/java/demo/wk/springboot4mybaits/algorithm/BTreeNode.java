package demo.wk.springboot4mybaits.algorithm;

/**
 * @ClassName BTreeNode
 * @Description 二叉树节点信息
 * @Author wangkai60
 * @Date 2019/1/16 15:11
 * @Version 1.0
 **/
//二叉树节点
public class BTreeNode {
    private int data;
    private BTreeNode left;
    private BTreeNode right;

    public BTreeNode() {}

    public BTreeNode(int data, BTreeNode left, BTreeNode right) {
        super();
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public BTreeNode getLeft() {
        return left;
    }

    public void setLeft(BTreeNode left) {
        this.left = left;
    }

    public BTreeNode getRight() {
        return right;
    }

    public void setRight(BTreeNode right) {
        this.right = right;
    }
}