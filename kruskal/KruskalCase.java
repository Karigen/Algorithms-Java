package kruskal;

import java.util.Arrays;

/*
 * ��³˹�����㷨
 * 1.��³˹������Kruskal���㷨�����������Ȩ��ͨͼ����С���������㷨
 * 2.����˼�룺����Ȩֵ��С�����˳��ѡ��n-1���ߣ�����֤��n-1���߲����ɻ�·
 * 3.�������������ȹ���һ��ֻ��n�������ɭ�֣�Ȼ����Ȩֵ��С�������ͨ����ѡ��߼��뵽ɭ���У���ʹɭ���в�������·��ֱ��ɭ�ֱ��һ����Ϊֹ
 */

public class KruskalCase {

    private int edgeNum;// �ߵĸ���
    private char[] vertexs;// ��������
    private int[][] matrix;// �ڽӾ���

    // ʹ��INF��ʾ�������㲻����ͨ
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
	// TODO �Զ����ɵķ������
	char[] vertexs = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
	int[][] matrix = { { 0, 12, INF, INF, INF, 16, 14 }, { 12, 0, 10, INF, INF, 7, INF },
		{ INF, 10, 0, 3, 5, 6, INF }, { INF, INF, 3, 0, 4, INF, INF }, { INF, INF, 5, 4, 0, 2, 8 },
		{ 16, 7, 6, INF, 2, 0, 9 }, { 14, INF, INF, INF, 8, 9, 0 }, };

	// ����KruskalCase����ʵ��
	KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);

	// ����������Ƿ���ȷ
	kruskalCase.print();

	kruskalCase.kruskal();
    }

    // ������
    public KruskalCase(char[] vertexs, int[][] matrix) {
	// ��ʼ���������ͱߵĸ���
	int vLen = vertexs.length;

	// ��ʼ������,���ƿ����ķ�ʽ
	this.vertexs = new char[vLen];
	for (int i = 0; i < vertexs.length; i++) {
	    this.vertexs[i] = vertexs[i];
	}

//	this.vertexs=vertexs;

	// ��ʼ���ߣ�ʹ�õ��Ǹ��ƿ����ķ�ʽ
	this.matrix = new int[vLen][vLen];
	for (int i = 0; i < vLen; i++) {
	    for (int j = 0; j < vLen; j++) {
		this.matrix[i][j] = matrix[i][j];
	    }
	}

	// ͳ�Ʊߵ�����
	for (int i = 0; i < vLen; i++) {
	    for (int j = i + 1; j < vLen; j++) {
		if (this.matrix[i][j] != INF) {
		    edgeNum++;
		}
	    }
	}
    }

    public void kruskal() {
	int index = 0;// ��ʾ��������������
	int[] ends = new int[edgeNum];// ���ڱ��桰������С���������е�ÿ����������С�������е��յ�

	// ����������飬����������С������
	EData[] results = new EData[edgeNum];

	// ��ȡͼ�����еıߵļ��ϣ�һ����12����
	EData[] edges = getEdges();
	System.out.println("��ȡͼ�ıߵļ���=" + Arrays.toString(edges) + " ��" + edges.length);// 12

	// ���ձߵ�Ȩֵ��С�������򣨴�С����
	sortEdges(edges);

	// ����edges���飬������ӵ���С��������ʱ���ж�׼������ı��Ƿ��γ��˻�·�����û�У��ͼ���results�������ܼ���
	for (int i = 0; i < edgeNum; i++) {
	    // ��ȡ����i���ߵĵ�һ�����㣨��㣩
	    int p1 = getPosition(edges[i].start);// p1=4
	    // ��ȡ����i���ߵĵ�2������
	    int p2 = getPosition(edges[i].end);// p2=5

	    // ��ȡp1����������������е���С�������е��յ�
	    int m = getEnd(ends, p1);// m=4
	    // ��ȡp2����������������е���С�������е��յ�
	    int n = getEnd(ends, p2);// n=5

	    // �Ƿ񹹳ɻ�·
	    if (m != n) {// û�й��ɻ�·
		ends[m] = n;// ����m�ڡ�������С���������е��յ�<E, F> [0,0,0,0,5,0,0,0,0,0,0,0]
		results[index++] = edges[i];// ��һ���߼��뵽results����
	    }
	}

	// <E,F> <C,D> <D,E> <B,F> <E,G> <A,B>
	// ͳ�ƴ�ӡ����С�������������results
	System.out.println("��С������Ϊ");
	for (int i = 0; i < index; i++) {
	    System.out.println(results[i]);
	}

    }

    // ��ӡ�ڽӾ���
    public void print() {
	System.out.println("�ڽӾ���Ϊ��");

	for (int i = 0; i < vertexs.length; i++) {
	    for (int j = 0; j < vertexs.length; j++) {
		System.out.printf("%-12d", matrix[i][j]);
	    }

	    System.out.println();// ����
	}
    }

    // �Ա߽���������ð������
    /**
     * ���ܣ��Ա߽���������ð������
     * 
     * @param edgs
     */
    private void sortEdges(EData[] edgs) {
	for (int i = 0; i < edgs.length - 1; i++) {
	    for (int j = 0; j < edgs.length - 1 - i; j++) {
		if (edgs[j].weight > edgs[j + 1].weight) {
		    EData temp = edgs[j];
		    edgs[j] = edgs[j + 1];
		    edgs[j + 1] = temp;
		}
	    }
	}
    }

    /**
     * 
     * @param ch �����ֵ������'A'��'B'
     * @return ����ch�����Ӧ��Ӧ���±꣬����Ҳ���������-1
     */
    private int getPosition(char ch) {
	for (int i = 0; i < vertexs.length; i++) {
	    if (vertexs[i] == ch) {
		return i;
	    }
	}

	// �Ҳ���������-1
	return -1;
    }

    /**
     * ���ܣ���ȡͼ�еıߣ��ŵ�EData[] �����У�����������Ҫ���������� ��ͨ��matrix�ڽӾ�������ȡ EData[] ��ʽ [['A', 'B',
     * 12], ['B', 'F', 7], ������]
     * 
     * @return
     */
    private EData[] getEdges() {
	int index = 0;
	EData[] edges = new EData[edgeNum];

	for (int i = 0; i < vertexs.length; i++) {
	    for (int j = i + 1; j < vertexs.length; j++) {
		if (matrix[i][j] != INF) {
		    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
		}
	    }
	}

	return edges;
    }

    /**
     * ���ܣ���ȡ�±�Ϊi�Ķ�����յ㣬���ں����ж�����������յ��Ƿ���ͬ
     * 
     * @param ends ��������Ǽ�¼�˸��������Ӧ���յ����ĸ���ends����ʵ�ڱ��������У����γ�
     * @param i    ����ʾ����Ķ����Ӧ���±�
     * @return ���صľ����±�Ϊi�������i����Ӧ���յ���±�
     */
    private int getEnd(int[] ends, int i) {// i=5 [0,0,0,0,5,0,0,0,0,0,0,0]
	while (ends[i] != 0) {
	    i = ends[i];
	}

	return i;
    }

}

//����һ����EData�����Ķ���ʵ���ͱ�ʾһ����
class EData {
    char start;// �ߵ�һ����
    char end;// �ߵ�����һ����
    int weight;// �ߵ�Ȩֵ

    public EData(char start, char end, int weight) {
	// TODO �Զ����ɵĹ��캯�����
	this.start = start;
	this.end = end;
	this.weight = weight;
    }

    // ��дtoString�������������Ϣ
    @Override
    public String toString() {
	return "EData [<=" + start + ", " + end + ">= " + weight + "]";
    }
}