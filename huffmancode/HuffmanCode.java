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
 * ����ѹ��
 * 1.���ɺշ�������Ӧ�ĺշ�������
 * 2.ʹ�úշ������������ɺշ����������ݣ�����������ĺշ������뽫�ַ������ɶ�Ӧ��01���ش�
 * 
 * ���ݽ�ѹ--ѹ�����������
 * 1.�õ��շ�������Ͷ�Ӧ��byte����
 * 2.�úշ���������н�ѹ���õ�ԭ�����ַ���
 * 
 * �ļ�ѹ��
 * 1.��ȡ�ļ�
 * 2.�õ��շ��������
 * 3.���ѹ��
 * 
 * �ļ�ѹ��
 * 1.����ļ�������Ǿ���ѹ������ģ���ôʹ�úշ���������ѹ��Ч�ʾͲ��������Եı仯��������Ƶ��ppt�ȵ��ļ�
 * 2.�շ��������ǰ��ֽ�������ģ���˿��Դ������еĶ������ļ����������ı����ı��ļ���
 * 3.���һ���ļ��е����ݣ��ظ������ݲ��࣬ѹ��Ч��Ҳ���������
 * 
 * ��ȡѹ���ļ������ݺͺշ��������->��ɽ�ѹ���ļ��ָ���
 */

public class HuffmanCode {

    public static void main(String[] args) {
	
	/*
	
	//����ѹ���ļ�
	String srcFile="d://src.bmp";
	String dstFile="d://dst.zip";
	
	zipFile(srcFile, dstFile);
	
	System.out.println("ѹ���ļ�ok");
	
	*/
	
	//���Խ�ѹ�ļ�
	String zipFile="d://dst.zip";
	String dstFile="d://src2.bmp";
	unZipFile(zipFile, dstFile);
	System.out.println("��ѹ�ɹ�");
	
	/*
	
	String content="i like like like java do you like a java";
	byte[] contentBytes=content.getBytes();
	System.out.println(contentBytes.length);//40
	
	byte[] huffmanCodeBytes=huffmanZip(contentBytes);
	System.out.println("ѹ����Ľ����"+Arrays.toString(huffmanCodeBytes)+" ����= "+huffmanCodeBytes.length);
	
	//����һ��byteToBitString����
	//System.out.println(byteToBitString((byte)1));
	byte[] sourceBytes=decode(huffmanCodes, huffmanCodeBytes);
	System.out.println("ԭ�����ַ���="+new String(sourceBytes));//"i like like like java do you like a java"
	
	*/
	
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
    
    //��дһ����������ɶ�ѹ���ļ��Ľ�ѹ
    /**
     * 
     * @param zipFile ׼����ѹ���ļ�
     * @param dstFile ���ļ���ѹ���ĸ�·��
     */
    public static void unZipFile(String zipFile, String dstFile) {
	//�����ļ�������
	InputStream is=null;
	
	//����һ������������
	ObjectInputStream ois=null;
	
	//�����ļ��������
	OutputStream os=null;
	
	try {
	    //�����ļ�������
	    is=new FileInputStream(zipFile);
	    
	    //����һ����is�����Ķ���������
	    ois=new ObjectInputStream(is);
	    
	    //��ȡbyte���� huffmanBytes
	    byte[] huffmanBytes=(byte[])ois.readObject();
	    
	    //��ȡ�շ��������
	    Map<Byte, String> huffmanCodes=(Map<Byte, String>)ois.readObject();
	    
	    //����
	    byte[] bytes=decode(huffmanCodes, huffmanBytes);
	    
	    //��bytes����д�뵽Ŀ���ļ�
	    os=new FileOutputStream(dstFile);
	    
	    //д���ݵ�dstFile�ļ���
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
		// TODO �Զ����ɵ� catch ��
		System.out.println(e.getMessage());
	    }
	}
    }
    
    //��д��������һ���ļ�����ѹ��
    /**
     * 
     * @param srcFile �㴫���ϣ��ѹ�����ļ��ľ���·��
     * @param dstFile ����ѹ����ѹ���ļ��ŵ��ĸ�Ŀ¼
     * @throws FileNotFoundException 
     */
    public static void zipFile(String srcFile, String dstFile) {
	//���������
	OutputStream os=null;
	ObjectOutputStream oos=null;
	
	//�����ļ���������
	FileInputStream is=null;
	
	try {
	    //�����ļ���������
	    is=new FileInputStream(srcFile);
	    
	    //����һ����Դ�ļ���Сһ����byte[]
	    byte[] b=new byte[is.available()];
	    
	    //��ȡ�ļ�
	    is.read(b);
	    
	    //ֱ�Ӷ�Դ�ļ�ѹ��
	    byte[] huffmanBytes=huffmanZip(b);
	    
	    //�����ļ�������������ѹ���ļ�
	    os=new FileOutputStream(dstFile);
	    
	    //����һ�����ļ������������ObjectOutPutStream
	    oos=new ObjectOutputStream(os);
	    
	    //�Ѻշ����������ֽ�����д��ѹ���ļ�
	    oos.writeObject(huffmanBytes);//�����ǰ�
	    
	    //���������Զ������ķ�ʽд�� �շ������룬��Ϊ���Ժ����ǻָ�Դ�ļ�ʱʹ��
	    //ע��һ��Ҫ�Ѻշ�������д��ѹ���ļ�
	    oos.writeObject(huffmanCodes);
	} catch (Exception e) {
	    // TODO �Զ����ɵ� catch ��
	    System.out.println(e.getMessage());
	}finally {
	    try {
		is.close();
		oos.close();
		os.close();
	    } catch (Exception e) {
		// TODO �Զ����ɵ� catch ��
		System.out.println(e.getMessage());
	    }
	}
    }
    
    //������ݵĽ�ѹ
    //˼·
    //1.��huffmanCodeBytes��[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //  ������ת��"10101000101111111100������"
    //2."10101000101111111100������"=>���պշ�������=>"i like like like java do you like a java"
    
    //��дһ����������ɶ�ѹ�����ݵĽ���
    /**
     * 
     * @param huffmanCodes �շ�������map
     * @param huffmanBytes �շ�������õ����ֽ�����
     * @return ����ԭ�����ַ�����Ӧ������
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
	
	//1.�ȵõ�huffmanBytes��Ӧ�Ķ������ַ�������ʽ�磺10101000101111111100������
	StringBuilder stringBuilder=new StringBuilder();
	
	//��byte����ת�ɶ����Ƶ��ַ���
	for (int i = 0; i < huffmanBytes.length; i++) {
	    
	    byte b=huffmanBytes[i];
	    
	    //�ж��ǲ������һ���ֽ�
	    boolean flag=(i==huffmanBytes.length-1);
	    stringBuilder.append(byteToBitString(!flag, b));
	}
	
	//���ַ�������ָ���ĺշ���������н���
	//�Ѻշ����������е�������Ϊ��ʡ��ѯ97->100 100->a
	Map<String, Byte> map=new HashMap<String, Byte>();
	for (Map.Entry<Byte, String> entry: huffmanCodes.entrySet()) {
	    map.put(entry.getValue(), entry.getKey());
	}
	
	//����һ�����ϣ����byte
	List<Byte> list=new ArrayList<>();
	
	//i�������ɾ���������ɨ��stringBuilder
	for (int i = 0; i < stringBuilder.length(); ) {
	    
	    int count=1;//С�ļ�����
	    boolean flag=true;
	    Byte b=null;
	    
	    while (flag) {
		
		//10101000101111111100������
		//������ȡ��key 1
		String key=stringBuilder.substring(i, i+count);//i ��������count�ƶ���ֱ��ƥ�䵽һ���ַ�
		
		b=map.get(key);
		
		if (b==null) {//˵��û��ƥ�䵽
		    count++;
		} else {
		    
		    //ƥ�䵽
		    flag=false;
		    
		}
		
	    }
	    
	    list.add(b);
	    
	    i+=count;//iֱ���ƶ���count
	    
	}
	
	//��forѭ������������list�оʹ�������е��ַ�"i like like like java do you like a java"
	//��list�е����ݷ��뵽byte[] ������
	byte[] b=new byte[list.size()];
	for (int i = 0; i < b.length; i++) {
	    b[i]=list.get(i);
	}
	
	return b;
	
    }
    
    /**
     * ��һ��byteת�ɶ������ַ���
     * @param b �����byte
     * @param flag ��ʶ�Ƿ���Ҫ����λ�������true����ʾ��Ҫ����λ�������false��ʾ����,��������һ���ֽڣ����貹��λ
     * @return �Ǹ�b��Ӧ�Ķ������ַ�������ע���ǰ����뷵�أ�
     */
    private static String byteToBitString(boolean flag, byte b) {
	
	//ʹ�ñ�������b
	int temp=b;//��bת��int
	
	//������������ǻ����ڲ���λ
	if (flag) {
	    temp|=256;//��λ�� 256 1 0000 0000 | 0000 0001=>1 0000 0001	    
	}
	
	String str=Integer.toBinaryString(temp);//���ص���temp��Ӧ�Ķ����ƵĲ���
	
	if (flag) {
	    return str.substring(str.length()-8);	    
	} else {
	    return str;
	}
	
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