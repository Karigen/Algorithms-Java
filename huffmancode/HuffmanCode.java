package huffmancode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 赫夫曼编码--变长编码的一种
 * 
 * 信息处理方式：
 * 1.定长编码
 * 2.变长编码--非前缀码，有匹配多义性
 * 3.赫夫曼编码--前缀码，由赫夫曼树叶子节点导出
 * 
 * 步骤：
 * 1.处理字符串
 *  1.1获取字符串
 *  1.2将各字符出现的次数统计出来作为权值，字符为值构建节点
 *  1.3按照字符出现的次数构建一棵赫夫曼树，次数为权值
 * 2.构建赫夫曼树--字符节点全是叶子节点
 *  2.1从小到大进行排序，将每一个数据，每个数据都是一个节点，每个节点看成是一棵最简单的二叉树
 *  2.2取出根节点权值最小的两棵二叉树
 *  2.3组成一棵新的二叉树，该新的二叉树的根节点的权值是前面两棵二叉树根节点权值的和
 *  2.4再将这课二叉树，以根节点的权值大小再次排序，不断重复1-2-3-4的步骤，直到数列中，所有的数据都被处理，就得到一棵赫夫曼树
 * 3.根据赫夫曼树，给各个字符，规定编码（前缀编码），向左的路径为0，向右的路径为1--路径不同或者排序不稳定导致赫夫曼树（编码）也不同，但是wpl是相同的，都是最小的，最后生成赫夫曼编码的长度是一样的
 * 4.按照赫夫曼编码，将字符串对应的编码为二进制串（使用的是无损压缩）
 * 5.此编码满足前缀编码，即字符的编码都不能是其他字符编码的前缀。不会造成匹配的多义性，赫夫曼编码是无损处理方案
 * 
 * 代码思路：
 * 1.Node {data (存放数据--字符) weight (权值) left和right}
 * 2.得到字符串对应的byte[]数组
 * 3.编写一个方法，将准备构建赫夫曼树的Node节点放到List，形式[Node[data='a',weight=5], Node[data=' ',weight=9]······]体现各节点对应的权值
 * 4.可以通过List创建对应的赫夫曼树
 * 
 */

public class HuffmanCode {

    public static void main(String[] args) {
	String content="i like like like java do you like a java";
	byte[] contentBytes=content.getBytes();
	System.out.println(contentBytes.length);//40
	
	List<Node> nodes=getNodes(contentBytes);
	System.out.println("nodes="+nodes);
	
	//测试一把创建的二叉树
	System.out.println("赫夫曼树");
	Node huffmanTreeRoot=createHuffmanTree(nodes);
	System.out.println("前序遍历");
	huffmanTreeRoot.preOrder();
	
	//测试一把是否成成了对应的赫夫曼编码
	Map<Byte, String> huffmanCodes=getCodes(huffmanTreeRoot);
	
	System.out.println("生成的赫夫曼编码表="+huffmanCodes);
    }
    
    //生成赫夫曼树对应的赫夫曼编码
    //思路：
    //1.将赫夫曼编码表存放在Map<Byte, String> 形式 '字符' -> 二进制码（赫夫曼编码）
    //生成的赫夫曼编码表{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
    static Map<Byte, String> huffmanCodes=new HashMap<Byte, String>();
    
    //2.在生成赫夫曼编码表时，需要去拼接路径，定义一个StringBuilder 存储某个叶子节点的路径
    static StringBuilder stringBuilder=new StringBuilder();
    
    //为了调用方便重载getCodes
    private static Map<Byte, String> getCodes(Node root) {
	if (root==null) {
	    return null;
	}
	
	//处理root的左子树
	getCodes(root.left, "0", stringBuilder);
	
	//处理root的右子树
	getCodes(root.right, "1", stringBuilder);
	
	return huffmanCodes;
    }
    
    /**
     * 功能：将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合
     * @param node 传入的结点，
     * @param code 路径：左子结点是0，右子结点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
	StringBuilder stringBuilder2=new StringBuilder(stringBuilder);
	
	//将code 加入到stringBuilder2
	stringBuilder2.append(code);
	
	if (node!=null) {//如果node==null
	    //判断当前node，是叶子结点还是非叶子结点
	    if (node.data==null) {//非叶子结点
		//递归处理
		
		//向左
		getCodes(node.left, "0", stringBuilder2);
		
		//向右递归
		getCodes(node.right, "1", stringBuilder2);
	    } else {//说明是一个叶子结点
		//就表示找到某个叶子结点的最后
		huffmanCodes.put(node.data, stringBuilder2.toString());
	    }
	}
    }
    
    //前序遍历的方法
    private static void preOrder(Node root) {
	if (root!=null) {
	    root.preOrder();
	} else {
	    System.out.println("赫夫曼树为空");
	}
    }
    
    /**
     * 
     * @param bytes 接收字节数组
     * @return 返回的就是List 形式 [Node[data='a',weight=5], Node[data=' ',weight=9]······]
     */
    private static List<Node> getNodes(byte[] bytes) {
	//1.创建一个ArrayList
	ArrayList<Node> nodes=new ArrayList<Node>();
	
	//2.遍历bytes，统计每一个byte出现的次数->map[key, value]
	Map<Byte, Integer> counts=new HashMap<>();
	for (byte b : bytes) {
	    Integer count=counts.get(b);
	    if (count==null) {//Map还没有这个字符的数据
		counts.put(b, 1);
	    } else {
		counts.put(b, count+1);
	    }
	}
	
	//把每一个键值对转换成 一个Node对象，并加入到nodes集合
	//遍历map
	for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
	    nodes.add(new Node(entry.getKey(), entry.getValue()));
	}
	
	return nodes;
    }
    
    //可以通过List创建对应的赫夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {
	while (nodes.size()>1) {
	    //排序,从小到大
	    Collections.sort(nodes);
	    
	    //取出第一棵最小的二叉树
	    Node leftNode=nodes.get(0);
	    
	    //取出第二棵最小的二叉树
	    Node rightNode=nodes.get(1);
	    
	    //创建一棵新的二叉树，它的根节点没有data，只有权值
	    Node parent=new Node(null, leftNode.weight+rightNode.weight);
	    parent.left=leftNode;
	    parent.right=rightNode;
	    
	    //将已经处理的两棵二叉树从nodes删除
	    nodes.remove(leftNode);
	    nodes.remove(rightNode);
	    
	    //将新的二叉树加入到nodes
	    nodes.add(parent);
	}
	
	//nodes最后的节点，就是赫夫曼树的根节点
	return nodes.get(0);
    }
}

//创建Node，带数据和权值
class Node implements Comparable<Node>{
    Byte data;//存放数据本身--存放字符
    int weight;//权值，表示字符出现的次数
    Node left;
    Node right;
    
    public Node(Byte data, int weight) {
	super();
	this.data = data;
	this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
	//从小到大排序
	return this.weight-o.weight;
    }
    
    @Override
    public String toString() {
        return "Node [data = "+data+" weight = "+weight+"]";
    }
    
    //前序遍历
    public void preOrder() {
	System.out.println(this);
	
	if (this.left!=null) {
	    this.left.preOrder();
	}
	
	if (this.right!=null) {
	    this.right.preOrder();
	}
    }
}