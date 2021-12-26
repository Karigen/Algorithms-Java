package floyd;

import java.util.Arrays;

/*
 * ���������㷨����
 * 1.���������㷨Ҳ��һ������Ѱ�Ҹ����ļ�Ȩͼ�ж�������·�����㷨
 * 2.���������㷨����ͼ�и�������֮������·��
 * 3.�Ͻ�˹�����㷨���ڼ���ͼ��ĳһ�����㵽������������·��
 * 4.���������㷨VS�Ͻ�˹�����㷨:
 * 	�Ͻ�˹�����㷨ͨ��ѡ���ı����ʶ���,����ӳ������㵽������������·��;
 * 	���������㷨�е�ÿһ�����㶼�ǳ�������,������Ҫ��ÿһ�����㿴�������ʶ���,�����ÿһ�����㵽������������·��.
 * 	�൱�ڽ�ͼ��ÿһ����������Ϊ��ʼ���㿪ʼ�Ͻ�˹�����㷨.
 * 
 * ���������㷨����
 * 1.���ö���vi������vk�����·����֪ΪLik,����vk��vj�����·����֪ΪLkj,����vi��vj��·��ΪLij,��vi��vj�����·��Ϊ:min((Lik+Lkj), Lij),vk��ȡֵΪͼ�����е��,��ɻ��vi��vj�����·��
 * 2.����vi��vk�����·��Lik����vk��vj�����·��Lkj,����ͬ���ķ�ʽ���(���˸о���Ȼ���ǵݹ鵫���е�ݹ��˼��������,�м俴��ֻ��һ������k,ʵ�����кܶඥ��)
 */

public class FloydAlgorithm {

    public static void main(String[] args) {
	// TODO �Զ����ɵķ������
	// ���Կ���ͼ�Ƿ񴴽��ɹ�
	char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
	// �����ڽӾ���
	int[][] matrix = new int[vertex.length][vertex.length];
	final int N = 65535;

	matrix[0] = new int[] { 0, 5, 7, N, N, N, 2 };
	matrix[1] = new int[] { 5, 0, N, 9, N, N, 3 };
	matrix[2] = new int[] { 7, N, 0, N, 8, N, N };
	matrix[3] = new int[] { N, 9, N, 0, N, 4, N };
	matrix[4] = new int[] { N, N, 8, N, 0, 5, 4 };
	matrix[5] = new int[] { N, N, N, 4, 5, 0, 6 };
	matrix[6] = new int[] { 2, 3, N, N, 4, 6, 0 };

	// ����Graph����
	Graph graph = new Graph(vertex.length, matrix, vertex);
	
	//���ø��������㷨
	graph.floyd();
	
	graph.show();
    }

}

//����ͼ
class Graph {
    private char[] vertex;// ��Ŷ��������
    private int[][] dis;// ���棬�Ӹ��������������������ľ��룬���Ľ����Ҳ�Ǳ����ڸ�����
    private int[][] pre;// ���浽��Ŀ�궥���ǰ������

    // ������
    /**
     * 
     * @param length ��С
     * @param matrix �ڽӾ���
     * @param vertex ��������
     */
    public Graph(int length, int[][] matrix, char[] vertex) {
	// TODO �Զ����ɵĹ��캯�����
	this.vertex = vertex;
	this.dis = matrix;
	this.pre = new int[length][length];

	// ��pre�����ʼ����ע���ŵ���ǰ��������±�
	for (int i = 0; i < vertex.length; i++) {
	    Arrays.fill(pre[i], i);
	}
    }

    // ��ʾpre�����dis����
    public void show() {
	// Ϊ����ʾ�����Ķ��������Ż�һ�����
	char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
	for (int k = 0; k < dis.length; k++) {
	    // �Ƚ�pre���������һ��
	    for (int i = 0; i < dis.length; i++) {
		System.out.print(vertex[pre[k][i]] + " ");
	    }

	    System.out.println();

	    // ���dis�����һ������
	    for (int i = 0; i < dis.length; i++) {
		System.out.print("(" + vertex[k] + "��" + vertex[i] + "�����·����" + dis[k][i] + "�� ");
	    }

	    System.out.println();
	    System.out.println();
	}
    }
    
    //���������㷨
    public void floyd() {
	int len=0;//�����������
	
	//���м䶥��ı�����k�����м䶥����±�[ 'A', 'B', 'C', 'D', 'E', 'F', 'G' ]
	for (int k = 0; k < dis.length; k++) {//
	    //��i���㿪ʼ����[ 'A', 'B', 'C', 'D', 'E', 'F', 'G' ]
	    for (int i = 0; i < dis.length; i++) {
		//����j����//[ 'A', 'B', 'C', 'D', 'E', 'F', 'G' ]
		for (int j = 0; j < dis.length; j++) {
		    len=dis[i][k]+dis[k][j];//=>�����i�������������k�м䶥�㣬����j����
		    
		    if (len<dis[i][j]) {//���lenС��dis[i][j]
			dis[i][j]=len;//���¾���
			pre[i][j]=pre[k][j];//����ǰ������
		    }
		}
	    }
	}
    }
}