package huffmancode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * �շ�������--�䳤�����һ��
 * 
 * ��Ϣ����ʽ��
 * 1.��������
 * 2.�䳤����--��ǰ׺�룬��ƥ�������
 * 3.�շ�������--ǰ׺�룬�ɺշ�����Ҷ�ӽڵ㵼��
 * 
 * ���裺
 * 1.�����ַ���
 *  1.1��ȡ�ַ���
 *  1.2�����ַ����ֵĴ���ͳ�Ƴ�����ΪȨֵ���ַ�Ϊֵ�����ڵ�
 *  1.3�����ַ����ֵĴ�������һ�úշ�����������ΪȨֵ
 * 2.�����շ�����--�ַ��ڵ�ȫ��Ҷ�ӽڵ�
 *  2.1��С����������򣬽�ÿһ�����ݣ�ÿ�����ݶ���һ���ڵ㣬ÿ���ڵ㿴����һ����򵥵Ķ�����
 *  2.2ȡ�����ڵ�Ȩֵ��С�����ö�����
 *  2.3���һ���µĶ����������µĶ������ĸ��ڵ��Ȩֵ��ǰ�����ö��������ڵ�Ȩֵ�ĺ�
 *  2.4�ٽ���ζ��������Ը��ڵ��Ȩֵ��С�ٴ����򣬲����ظ�1-2-3-4�Ĳ��裬ֱ�������У����е����ݶ��������͵õ�һ�úշ�����
 * 3.���ݺշ��������������ַ����涨���루ǰ׺���룩�������·��Ϊ0�����ҵ�·��Ϊ1--·����ͬ���������ȶ����ºշ����������룩Ҳ��ͬ������wpl����ͬ�ģ�������С�ģ�������ɺշ�������ĳ�����һ����
 * 4.���պշ������룬���ַ�����Ӧ�ı���Ϊ�����ƴ���ʹ�õ�������ѹ����
 * 5.�˱�������ǰ׺���룬���ַ��ı��붼�����������ַ������ǰ׺���������ƥ��Ķ����ԣ��շ�����������������
 * 
 * ����˼·��
 * 1.Node {data (�������--�ַ�) weight (Ȩֵ) left��right}
 * 2.�õ��ַ�����Ӧ��byte[]����
 * 3.��дһ����������׼�������շ�������Node�ڵ�ŵ�List����ʽ[Node[data='a',weight=5], Node[data=' ',weight=9]������������]���ָ��ڵ��Ӧ��Ȩֵ
 * 4.����ͨ��List������Ӧ�ĺշ�����
 * 
 */

public class HuffmanCode {

    public static void main(String[] args) {
	String content="i like like like java do you like a java";
	byte[] contentBytes=content.getBytes();
	System.out.println(contentBytes.length);//40
	
	List<Node> nodes=getNodes(contentBytes);
	System.out.println("nodes="+nodes);
	
	//����һ�Ѵ����Ķ�����
	System.out.println("�շ�����");
	Node huffmanTreeRoot=createHuffmanTree(nodes);
	System.out.println("ǰ�����");
	huffmanTreeRoot.preOrder();
    }
    
    //ǰ������ķ���
    private static void preOrder(Node root) {
	if (root!=null) {
	    root.preOrder();
	} else {
	    System.out.println("�շ�����Ϊ��");
	}
    }
    
    /**
     * 
     * @param bytes �����ֽ�����
     * @return ���صľ���List ��ʽ [Node[data='a',weight=5], Node[data=' ',weight=9]������������]
     */
    private static List<Node> getNodes(byte[] bytes) {
	//1.����һ��ArrayList
	ArrayList<Node> nodes=new ArrayList<Node>();
	
	//2.����bytes��ͳ��ÿһ��byte���ֵĴ���->map[key, value]
	Map<Byte, Integer> counts=new HashMap<>();
	for (byte b : bytes) {
	    Integer count=counts.get(b);
	    if (count==null) {//Map��û������ַ�������
		counts.put(b, 1);
	    } else {
		counts.put(b, count+1);
	    }
	}
	
	//��ÿһ����ֵ��ת���� һ��Node���󣬲����뵽nodes����
	//����map
	for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
	    nodes.add(new Node(entry.getKey(), entry.getValue()));
	}
	
	return nodes;
    }
    
    //����ͨ��List������Ӧ�ĺշ�����
    private static Node createHuffmanTree(List<Node> nodes) {
	while (nodes.size()>1) {
	    //����,��С����
	    Collections.sort(nodes);
	    
	    //ȡ����һ����С�Ķ�����
	    Node leftNode=nodes.get(0);
	    
	    //ȡ���ڶ�����С�Ķ�����
	    Node rightNode=nodes.get(1);
	    
	    //����һ���µĶ����������ĸ��ڵ�û��data��ֻ��Ȩֵ
	    Node parent=new Node(null, leftNode.weight+rightNode.weight);
	    parent.left=leftNode;
	    parent.right=rightNode;
	    
	    //���Ѿ���������ö�������nodesɾ��
	    nodes.remove(leftNode);
	    nodes.remove(rightNode);
	    
	    //���µĶ��������뵽nodes
	    nodes.add(parent);
	}
	
	//nodes���Ľڵ㣬���Ǻշ������ĸ��ڵ�
	return nodes.get(0);
    }
}

//����Node�������ݺ�Ȩֵ
class Node implements Comparable<Node>{
    Byte data;//������ݱ���--����ַ�
    int weight;//Ȩֵ����ʾ�ַ����ֵĴ���
    Node left;
    Node right;
    
    public Node(Byte data, int weight) {
	super();
	this.data = data;
	this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
	//��С��������
	return this.weight-o.weight;
    }
    
    @Override
    public String toString() {
        return "Node [data = "+data+" weight = "+weight+"]";
    }
    
    //ǰ�����
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