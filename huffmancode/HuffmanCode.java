package huffmancode;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	byte[] huffmanCodeBytes=huffmanZip(contentBytes);
	System.out.println("ѹ����Ľ����"+Arrays.toString(huffmanCodeBytes)+" ����= "+huffmanCodeBytes.length);
	
	//��ν����ݽ��н�ѹ�����룩
	
	//�ֲ�����
	
	/*
	
	List<Node> nodes=getNodes(contentBytes);
	System.out.println("nodes="+nodes);
	
	//����һ�Ѵ����ĺշ�����
	System.out.println("�շ�����");
	Node huffmanTreeRoot=createHuffmanTree(nodes);
	System.out.println("ǰ�����");
	huffmanTreeRoot.preOrder();
	
	//����һ���Ƿ�ɳ��˶�Ӧ�ĺշ�������
	Map<Byte, String> huffmanCodes=getCodes(huffmanTreeRoot);
	
	System.out.println("���ɵĺշ��������="+huffmanCodes);
	
	//����
	byte[] huffmanCodeBytes=zip(contentBytes, huffmanCodes);
	System.out.println("huffmanCodeBytes="+Arrays.toString(huffmanCodeBytes));
	
	//����huffmanCodeBytes����
	 
	*/
    }
    
    //ʹ��һ����������ǰ��ķ�����װ�������������ǵĵ���
    /**
     * 
     * @param bytes ԭʼ���ַ�����Ӧ���ֽ�����
     * @return �Ǿ����շ������봦�����ֽ����飨ѹ��������飩
     */
    private static byte[] huffmanZip(byte[] bytes) {
	List<Node> nodes=getNodes(bytes);
	
	//����nodes�����ĺշ�����
	Node huffmanTreeRoot=createHuffmanTree(nodes);
	
	//��Ӧ�ĺշ������루���ݺշ�������
	Map<Byte, String> huffmanCodes=getCodes(huffmanTreeRoot);
	
	//�������ɵĺշ������룬ѹ����ĺշ��������ֽ�����
	byte[] huffmanCodeBytes=zip(bytes, huffmanCodes);
	
	return huffmanCodeBytes;
    }
    
    //��дһ�����������ַ�����Ӧ��byte[]���飬ͨ�����ɵĺշ������������һ���շ�������ѹ�����byte[]
    /**
     * 
     * @param bytes ����ԭʼ���ַ�����Ӧ��byte[]
     * @param huffmanCodes ���ɵĺշ�������map
     * @return ���غշ������봦����byte[]
     * ������String content="i like like like java do you like a java";=>byte[] contentBytes=content.getBytes();
     * ���ص����ַ�����Ӧ�Ķ���������=>��Ӧ��byte[] huffmanCodeBytes����8λ��Ӧһ��byte�����뵽huffmanCodeBytes
     * huffmanCodeBytes[0]=10101000�����룩=>byte[�Ƶ� 10101000=>10101000-1=>10100111=>11011000=-88]
     * huffmanCodeBytes[1]=-88
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
	//1.����huffmanCodes��bytesת�ɺշ��������Ӧ���ַ���
	StringBuilder stringBuilder=new StringBuilder();
	
	//����bytes����
	for (byte b : bytes) {
	    stringBuilder.append(huffmanCodes.get(b));
	}
	
	//System.out.println("���� stringBuilder="+stringBuilder.toString());
	
	//��"10101000101111111100������"ת��byte[]
	//ͳ�Ʒ��ص�byte[] huffmanCodeBytes ����
	//һ�仰int len=(stringBuilder+7)/8;
	int len;
	if (stringBuilder.length()%8==0) {
	    len=stringBuilder.length()/8;
	} else {
	    len=stringBuilder.length()/8+1;
	}
	
	//�����洢ѹ�����byte[]
	byte[] huffmanCodeBytes=new byte[len];
	
	int index=0;//��¼�ǵڼ���byte
	for (int i = 0; i < stringBuilder.length(); i+=8) {//��Ϊ��ÿ8λ��Ӧһ��byte�����Բ���+8
	    String strByte;
	    
	    if (i+8>stringBuilder.length()) {//����8λ
		strByte=stringBuilder.substring(i);
	    } else {
		strByte=stringBuilder.substring(i, i+8);
	    }
	    
	    //��strByteת��һ��byte�����뵽huffmanCodeBytes
	    huffmanCodeBytes[index]=(byte)Integer.parseInt(strByte, 2);
	    index++;
	}
	
	return huffmanCodeBytes;
    }
    
    //���ɺշ�������Ӧ�ĺշ�������
    //˼·��
    //1.���շ������������Map<Byte, String> ��ʽ '�ַ�' -> �������루�շ������룩
    //���ɵĺշ��������{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
    static Map<Byte, String> huffmanCodes=new HashMap<Byte, String>();
    
    //2.�����ɺշ��������ʱ����Ҫȥƴ��·��������һ��StringBuilder �洢ĳ��Ҷ�ӽڵ��·��
    static StringBuilder stringBuilder=new StringBuilder();
    
    //Ϊ�˵��÷�������getCodes
    private static Map<Byte, String> getCodes(Node root) {
	if (root==null) {
	    return null;
	}
	
	//����root��������
	getCodes(root.left, "0", stringBuilder);
	
	//����root��������
	getCodes(root.right, "1", stringBuilder);
	
	return huffmanCodes;
    }
    
    /**
     * ���ܣ��������node��������Ҷ�ӽ��ĺշ�������õ��������뵽huffmanCodes����
     * @param node ����Ľ�㣬
     * @param code ·�������ӽ����0�����ӽ����1
     * @param stringBuilder ����ƴ��·��
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
	StringBuilder stringBuilder2=new StringBuilder(stringBuilder);
	
	//��code ���뵽stringBuilder2
	stringBuilder2.append(code);
	
	if (node!=null) {//���node==null
	    //�жϵ�ǰnode����Ҷ�ӽ�㻹�Ƿ�Ҷ�ӽ��
	    if (node.data==null) {//��Ҷ�ӽ��
		//�ݹ鴦��
		
		//����
		getCodes(node.left, "0", stringBuilder2);
		
		//���ҵݹ�
		getCodes(node.right, "1", stringBuilder2);
	    } else {//˵����һ��Ҷ�ӽ��
		//�ͱ�ʾ�ҵ�ĳ��Ҷ�ӽ������
		huffmanCodes.put(node.data, stringBuilder2.toString());
	    }
	}
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