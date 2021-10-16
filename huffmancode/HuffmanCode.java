package huffmancode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
 * 数据压缩
 * 1.生成赫夫曼树对应的赫夫曼编码
 * 2.使用赫夫曼编码来生成赫夫曼编码数据，即按照上面的赫夫曼编码将字符串生成对应的01比特串
 * 
 * 数据解压--压缩的逆向过程
 * 1.得到赫夫曼编码和对应的byte数组
 * 2.用赫夫曼编码进行解压，得到原来的字符串
 * 
 * 文件压缩
 * 1.读取文件
 * 2.得到赫夫曼编码表
 * 3.完成压缩
 * 
 * 文件压缩
 * 1.如果文件本身就是经过压缩处理的，那么使用赫夫曼编码再压缩效率就不会有明显的变化，比如视频，ppt等等文件
 * 2.赫夫曼编码是按字节来处理的，因此可以处理所有的二进制文件（二进制文本、文本文件）
 * 3.如果一个文件中的内容，重复的数据不多，压缩效果也不会很明显
 * 
 * 读取压缩文件（数据和赫夫曼编码表）->完成解压（文件恢复）
 */

public class HuffmanCode {

    public static void main(String[] args) {
	
	/*
	
	//测试压缩文件
	String srcFile="d://src.bmp";
	String dstFile="d://dst.zip";
	
	zipFile(srcFile, dstFile);
	
	System.out.println("压缩文件ok");
	
	*/
	
	//测试解压文件
	String zipFile="d://dst.zip";
	String dstFile="d://src2.bmp";
	unZipFile(zipFile, dstFile);
	System.out.println("解压成功");
	
	/*
	
	String content="i like like like java do you like a java";
	byte[] contentBytes=content.getBytes();
	System.out.println(contentBytes.length);//40
	
	byte[] huffmanCodeBytes=huffmanZip(contentBytes);
	System.out.println("压缩后的结果："+Arrays.toString(huffmanCodeBytes)+" 长度= "+huffmanCodeBytes.length);
	
	//测试一把byteToBitString方法
	//System.out.println(byteToBitString((byte)1));
	byte[] sourceBytes=decode(huffmanCodes, huffmanCodeBytes);
	System.out.println("原来的字符串="+new String(sourceBytes));//"i like like like java do you like a java"
	
	*/
	
	//如何将数据进行解压（解码）
	
	//分布过程
	
	/*
	
	List<Node> nodes=getNodes(contentBytes);
	System.out.println("nodes="+nodes);
	
	//测试一把创建的赫夫曼树
	System.out.println("赫夫曼树");
	Node huffmanTreeRoot=createHuffmanTree(nodes);
	System.out.println("前序遍历");
	huffmanTreeRoot.preOrder();
	
	//测试一把是否成成了对应的赫夫曼编码
	Map<Byte, String> huffmanCodes=getCodes(huffmanTreeRoot);
	
	System.out.println("生成的赫夫曼编码表="+huffmanCodes);
	
	//测试
	byte[] huffmanCodeBytes=zip(contentBytes, huffmanCodes);
	System.out.println("huffmanCodeBytes="+Arrays.toString(huffmanCodeBytes));
	
	//发送huffmanCodeBytes数组
	 
	*/
    }
    
    //编写一个方法，完成对压缩文件的解压
    /**
     * 
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件解压到哪个路径
     */
    public static void unZipFile(String zipFile, String dstFile) {
	//定义文件输入流
	InputStream is=null;
	
	//定义一个对象输入流
	ObjectInputStream ois=null;
	
	//定义文件的输出流
	OutputStream os=null;
	
	try {
	    //创建文件输入流
	    is=new FileInputStream(zipFile);
	    
	    //创建一个和is关联的对象输入流
	    ois=new ObjectInputStream(is);
	    
	    //读取byte数组 huffmanBytes
	    byte[] huffmanBytes=(byte[])ois.readObject();
	    
	    //读取赫夫曼编码表
	    Map<Byte, String> huffmanCodes=(Map<Byte, String>)ois.readObject();
	    
	    //解码
	    byte[] bytes=decode(huffmanCodes, huffmanBytes);
	    
	    //将bytes数组写入到目标文件
	    os=new FileOutputStream(dstFile);
	    
	    //写数据到dstFile文件中
	    os.write(bytes);
	} catch (Exception e) {
	    // TODO: handle exception
	    System.out.println(e.getMessage());
	} finally {
	    try {
		os.close();
		    ois.close();
		is.close();
	    } catch (Exception e) {
		// TODO 自动生成的 catch 块
		System.out.println(e.getMessage());
	    }
	}
    }
    
    //编写方法，将一个文件进行压缩
    /**
     * 
     * @param srcFile 你传入的希望压缩的文件的绝对路径
     * @param dstFile 我们压缩后经压缩文件放到哪个目录
     * @throws FileNotFoundException 
     */
    public static void zipFile(String srcFile, String dstFile) {
	//创建输出流
	OutputStream os=null;
	ObjectOutputStream oos=null;
	
	//创建文件的输入流
	FileInputStream is=null;
	
	try {
	    //创建文件的输入流
	    is=new FileInputStream(srcFile);
	    
	    //创建一个和源文件大小一样的byte[]
	    byte[] b=new byte[is.available()];
	    
	    //读取文件
	    is.read(b);
	    
	    //直接对源文件压缩
	    byte[] huffmanBytes=huffmanZip(b);
	    
	    //创建文件的输出流，存放压缩文件
	    os=new FileOutputStream(dstFile);
	    
	    //创建一个和文件输出流关联的ObjectOutPutStream
	    oos=new ObjectOutputStream(os);
	    
	    //把赫夫曼编码后的字节数组写入压缩文件
	    oos.writeObject(huffmanBytes);//我们是把
	    
	    //这里我们以对象流的方式写入 赫夫曼编码，是为了以后我们恢复源文件时使用
	    //注意一定要把赫夫曼编码写入压缩文件
	    oos.writeObject(huffmanCodes);
	} catch (Exception e) {
	    // TODO 自动生成的 catch 块
	    System.out.println(e.getMessage());
	}finally {
	    try {
		is.close();
		oos.close();
		os.close();
	    } catch (Exception e) {
		// TODO 自动生成的 catch 块
		System.out.println(e.getMessage());
	    }
	}
    }
    
    //完成数据的解压
    //思路
    //1.将huffmanCodeBytes：[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //  重现先转成"10101000101111111100···"
    //2."10101000101111111100···"=>对照赫夫曼编码=>"i like like like java do you like a java"
    
    //编写一个方法，完成对压缩数据的解码
    /**
     * 
     * @param huffmanCodes 赫夫曼编码map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 就是原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
	
	//1.先得到huffmanBytes对应的二进制字符串，形式如：10101000101111111100···
	StringBuilder stringBuilder=new StringBuilder();
	
	//将byte数组转成二进制的字符串
	for (int i = 0; i < huffmanBytes.length; i++) {
	    
	    byte b=huffmanBytes[i];
	    
	    //判断是不是最后一个字节
	    boolean flag=(i==huffmanBytes.length-1);
	    stringBuilder.append(byteToBitString(!flag, b));
	}
	
	//把字符串按照指定的赫夫曼编码进行解码
	//把赫夫曼编码表进行调换，因为反省查询97->100 100->a
	Map<String, Byte> map=new HashMap<String, Byte>();
	for (Map.Entry<Byte, String> entry: huffmanCodes.entrySet()) {
	    map.put(entry.getValue(), entry.getKey());
	}
	
	//创建一个集合，存放byte
	List<Byte> list=new ArrayList<>();
	
	//i可以理解成就是索引，扫描stringBuilder
	for (int i = 0; i < stringBuilder.length(); ) {
	    
	    int count=1;//小的计数器
	    boolean flag=true;
	    Byte b=null;
	    
	    while (flag) {
		
		//10101000101111111100···
		//递增的取出key 1
		String key=stringBuilder.substring(i, i+count);//i 不动，让count移动，直到匹配到一个字符
		
		b=map.get(key);
		
		if (b==null) {//说明没有匹配到
		    count++;
		} else {
		    
		    //匹配到
		    flag=false;
		    
		}
		
	    }
	    
	    list.add(b);
	    
	    i+=count;//i直接移动到count
	    
	}
	
	//当for循环结束后，我们list中就存放了所有的字符"i like like like java do you like a java"
	//把list中的数据放入到byte[] 并返回
	byte[] b=new byte[list.size()];
	for (int i = 0; i < b.length; i++) {
	    b[i]=list.get(i);
	}
	
	return b;
	
    }
    
    /**
     * 将一个byte转成二进制字符串
     * @param b 传入的byte
     * @param flag 标识是否需要补高位，如果是true，表示需要补高位，如果是false表示不补,如果是最后一个字节，无需补高位
     * @return 是该b对应的二进制字符串，（注意是按补码返回）
     */
    private static String byteToBitString(boolean flag, byte b) {
	
	//使用变量保存b
	int temp=b;//将b转成int
	
	//如果是正数我们还存在补高位
	if (flag) {
	    temp|=256;//按位与 256 1 0000 0000 | 0000 0001=>1 0000 0001	    
	}
	
	String str=Integer.toBinaryString(temp);//返回的是temp对应的二进制的补码
	
	if (flag) {
	    return str.substring(str.length()-8);	    
	} else {
	    return str;
	}
	
    }
    
    //使用一个方法，将前面的方法封装起来，便于我们的调用
    /**
     * 
     * @param bytes 原始的字符串对应的字节数组
     * @return 是经过赫夫曼编码处理后的字节数组（压缩后的数组）
     */
    private static byte[] huffmanZip(byte[] bytes) {
	List<Node> nodes=getNodes(bytes);
	
	//根据nodes创建的赫夫曼树
	Node huffmanTreeRoot=createHuffmanTree(nodes);
	
	//对应的赫夫曼编码（根据赫夫曼树）
	Map<Byte, String> huffmanCodes=getCodes(huffmanTreeRoot);
	
	//根据生成的赫夫曼编码，压缩后的赫夫曼编码字节数组
	byte[] huffmanCodeBytes=zip(bytes, huffmanCodes);
	
	return huffmanCodeBytes;
    }
    
    //编写一个方法，将字符串对应的byte[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码压缩后的byte[]
    /**
     * 
     * @param bytes 这是原始的字符串对应的byte[]
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的byte[]
     * 举例：String content="i like like like java do you like a java";=>byte[] contentBytes=content.getBytes();
     * 返回的是字符串对应的二进制序列=>对应的byte[] huffmanCodeBytes，即8位对应一个byte，放入到huffmanCodeBytes
     * huffmanCodeBytes[0]=10101000（补码）=>byte[推导 10101000=>10101000-1=>10100111=>11011000=-88]
     * huffmanCodeBytes[1]=-88
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
	//1.利用huffmanCodes将bytes转成赫夫曼编码对应的字符串
	StringBuilder stringBuilder=new StringBuilder();
	
	//遍历bytes数组
	for (byte b : bytes) {
	    stringBuilder.append(huffmanCodes.get(b));
	}
	
	//System.out.println("测试 stringBuilder="+stringBuilder.toString());
	
	//将"10101000101111111100···"转成byte[]
	//统计返回的byte[] huffmanCodeBytes 长度
	//一句话int len=(stringBuilder+7)/8;
	int len;
	if (stringBuilder.length()%8==0) {
	    len=stringBuilder.length()/8;
	} else {
	    len=stringBuilder.length()/8+1;
	}
	
	//创建存储压缩后的byte[]
	byte[] huffmanCodeBytes=new byte[len];
	
	int index=0;//记录是第几个byte
	for (int i = 0; i < stringBuilder.length(); i+=8) {//因为是每8位对应一个byte，所以步长+8
	    String strByte;
	    
	    if (i+8>stringBuilder.length()) {//不够8位
		strByte=stringBuilder.substring(i);
	    } else {
		strByte=stringBuilder.substring(i, i+8);
	    }
	    
	    //将strByte转成一个byte，放入到huffmanCodeBytes
	    huffmanCodeBytes[index]=(byte)Integer.parseInt(strByte, 2);
	    index++;
	}
	
	return huffmanCodeBytes;
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